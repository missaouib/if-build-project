package com.bananayong.project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class UserService {
    private final Map<String, User> userRepository = new ConcurrentHashMap<>();
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public void createUser(String username, String password) {
        var encodedPassword = passwordEncoder.encode(password);
        userRepository.put(username, new User(username, encodedPassword));
    }

    public Optional<User> findUser(String username) {
        var user = userRepository.get(username);
        if (user != null) {
            return Optional.of(user);
        }

        return Optional.empty();
    }
}
