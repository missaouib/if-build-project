package com.bananayong.project.login.web;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@RestController
public class LoginController {

    private Map<String, String> userRepository = new ConcurrentHashMap<>();

    @PostMapping(path = "/sign-up")
    public SignUpResponse signUp(@RequestBody SignUpRequest request) {

        userRepository.put(request.getUsername(), request.getPassword());

        return SignUpResponse.of(request.getUsername(), Instant.now());
    }

    @PostMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        var username = request.getUsername();
        var credential = userRepository.get(username);

        if (request.getPassword().equals(credential)) {
            httpServletRequest.getSession(true);
            var token = new UsernamePasswordAuthenticationToken(username, "", createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(token);

            return LoginResponse.of(username, Instant.now());
        }

        throw new BadCredentialsException("Bad credential");
    }

    @GetMapping("/users/{username}")
    public String getUser(@NotNull @PathVariable String username) {
        if (userRepository.containsKey(username)) {
            return username;
        }

        throw new IllegalArgumentException("Bad username");
    }

}
