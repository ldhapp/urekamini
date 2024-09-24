package com.uplus.miniproject2.config;


import com.uplus.miniproject2.jwt.JWTFilter;
import com.uplus.miniproject2.jwt.JWTUtil;
import com.uplus.miniproject2.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CSRF 비활성화 (JWT 사용으로 인해 필요 없음)
        http.csrf((auth) -> auth.disable());

        // Form 로그인 및 HTTP 기본 인증 비활성화 (JWT 사용으로 인해 필요 없음)
        http.formLogin((auth) -> auth.disable());
        http.httpBasic((auth) -> auth.disable());

        // 경로별 인가 설정
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/api/map/regions/**").hasAnyRole("USER", "ADMIN") // 이 줄을 추가
                .requestMatchers("/api/map/profiles").permitAll() // 이 줄을 추가
                .requestMatchers(
                        "/*.html",         // 모든 HTML 파일
                        "/",
                        "/join",
                        "/api/join/**",
                        "/check",
                        "/api/users/**",
                        "/css/**", "/js/**", "/img/**",
                        "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**"
                ).permitAll()// 누구나 접근 가능
                .requestMatchers("/api/posts/**", "/api/map/**", "/api/statistics/**", "/api/profiles/**",
                        "/api/familiarity/**")
                .hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin").hasRole("ADMIN") // 어드민 권한
                .anyRequest().authenticated()); // 그 외 모든 요청은 인증 필요

        // 로그인 필터 등록
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
                UsernamePasswordAuthenticationFilter.class);

        // JWT 필터 등록
        http.addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // 세션 관리 설정 (JWT 사용으로 인해 세션 사용 안 함)
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 로그아웃 설정
        http.logout((logout) -> logout
                .logoutUrl("/logout") // 로그아웃 요청 URL
                .logoutSuccessHandler((request, response, authentication) -> {
                    // 로그아웃 성공 시의 동작 설정
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"message\": \"로그아웃 성공\"}");

                })
                .deleteCookies("Refresh-Token") // 쿠키 삭제
                .invalidateHttpSession(false)); // 세션 무효화

        return http.build();
    }
}
