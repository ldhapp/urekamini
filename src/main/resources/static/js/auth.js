// auth.js
export function getAuthHeaders() {
    const accessToken = localStorage.getItem("accessToken");

    if (!accessToken) {
        throw new Error("No access token found");
    }

    return {
        "Authorization": "Bearer " + accessToken
    };
}

export function isLoggedIn() {
    return !!localStorage.getItem('accessToken');
}

export async function logout() {
    try {
        let response = await fetch('/logout', {
            method: 'POST',
            credentials: 'include', // 요청에 쿠키 포함
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            localStorage.removeItem("accessToken");
            alert("로그아웃 되었습니다.");
            window.location.href = "/login.html"; // 로그인 페이지로 리디렉션
        } else {
            alert("로그아웃에 실패했습니다.");
        }
    } catch (error) {
        console.error('로그아웃 중 오류 발생:', error);
        alert("로그아웃 과정에서 오류가 발생했습니다.");
    }
}


// 전역 범위에서 호출 가능하게 하기 위해 추가
window.logout = logout;


// 토큰 처리 로직을 분리
function getAccessToken() {
    return localStorage.getItem('accessToken');
}
window.getAccessToken = getAccessToken;

function saveAccessToken(token) {
    localStorage.setItem('accessToken', token);
}

// 액세스 토큰을 사용해 요청을 보내는 함수
async function sendRequestWithToken(url, method, headers, body) {
    const token = getAccessToken();  // getAccessToken() 함수에서 토큰 가져오기
    if (!headers) {
        // headers가 없으면 새 Headers 객체를 생성
        headers = new Headers({
            'Authorization': `Bearer ${token}`,
        });
    } else if (typeof headers.append === 'function') {
        // headers가 Headers 객체인 경우
        headers.append('Authorization', `Bearer ${token}`);
    } else {
        // headers가 일반 객체인 경우
        headers['Authorization'] = `Bearer ${token}`;
    }
    try {
        let response = await fetch(url, {
            method: method,
            headers: headers,
            body:body
        });
        // 응답 상태가 401인 경우 리프레시 토큰이 만료되었을 수 있음
        if (response.status === 401) {
            // 로그아웃 요청
            const logoutResponse = await fetch('/logout', {
                method: 'POST',
                credentials: 'include', // 요청에 쿠키 포함
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            if (logoutResponse.ok) {
                console.log('401입니다!')
                localStorage.removeItem("accessToken");
                alert('로그인이 필요합니다.');
                window.location.href = '/login.html'; // 리프레시 토큰이 만료되었을 때 로그인 페이지로 리다이렉트
            }
        } else if (response.status === 403) {
            console.log('403 입니다!')
            alert('로그인이 필요합니다.');
            window.location.href = '/login.html'; // 권한이 없을 때 로그인 페이지로 리다이렉트
        }

        // 응답에서 Authorization 헤더가 존재할 때만 처리
        let authorizationHeader = response.headers.get("Authorization");
        if (authorizationHeader && authorizationHeader.startsWith("Bearer ")) {
            let accessToken = authorizationHeader.replace("Bearer ", "");
            localStorage.setItem("accessToken", accessToken);  // 새로운 액세스 토큰 저장
            console.log("새로운 액세스 토큰이 생성되었습니다.");
        } else {
            console.log("새로운 액세스 토큰을 생성하지 않습니다.");
        }

        return response; // 응답 반환
    } catch (error) {
        console.error('Request error:', error); // 네트워크 에러 처리
        throw error;
    }
}

// 전역에서 사용 가능하도록 함수 내보내기
window.sendRequestWithToken = sendRequestWithToken;
