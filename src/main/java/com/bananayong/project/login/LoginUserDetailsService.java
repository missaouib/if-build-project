package com.bananayong.project.login;

import com.bananayong.project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user =
            userService.findUser(username)
                       .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(),
            AuthorityUtils.createAuthorityList("ROLE_USER")
        );
    }
}
