package com.bananayong.project.login;

import com.bananayong.project.security.ApiAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringSecurityDelegateLoginService implements LoginService {

    private final AuthenticationManager authenticationManager;

    @Override
    public void login(String username, String password) {
        var authenticate = authenticationManager.authenticate(new ApiAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }
}
