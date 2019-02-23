package com.bananayong.project.login.web;

import com.bananayong.project.login.LoginService;
import com.bananayong.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping(path = "/sign-up")
    public SignUpResponse signUp(@RequestBody SignUpRequest request) {
        var user = userService.createUser(request.getUsername(), request.getPassword());
        return SignUpResponse.of(user.getUsername(), Instant.now());
    }

    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        var username = request.getUsername();
        var password = request.getPassword();

        loginService.login(username, password);
        httpServletRequest.getSession(true);

        return LoginResponse.of(username, Instant.now());
    }
}