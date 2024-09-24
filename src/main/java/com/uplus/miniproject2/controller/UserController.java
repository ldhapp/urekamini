package com.uplus.miniproject2.controller;

import com.uplus.miniproject2.dto.DetailPageUserDto;
import com.uplus.miniproject2.dto.MainPageUserDto;
import com.uplus.miniproject2.service.UserService;
import com.uplus.miniproject2.util.ApiUtil;
import com.uplus.miniproject2.util.ApiUtil.ApiSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiSuccess<?> getUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String mbti,
            @RequestParam(required = false) String major,
            @RequestParam(required = false) String gender,
            Pageable pageable
    ) {

//        Page<MainPageUserDto> searchResult = userService.searchUser(name, mbti, major, gender, pageable);
        Page<MainPageUserDto> searchResult = userService.searchUserByMyBatis(name, mbti, major, gender, pageable);
        return ApiUtil.success(searchResult);
    }

    @GetMapping("/{userId}")
    public ApiSuccess<?> getUserDetail(@PathVariable("userId") Long userId) {
        DetailPageUserDto userDetail = userService.getUserDetail(userId);

        return ApiUtil.success(userDetail);
    }
}