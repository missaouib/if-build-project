package com.bananayong.project.user;

import java.util.Optional;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Transactional
    public User createUser(String username, String password) {
        var encodedPassword = passwordEncoder.encode(password);
        return userRepository.save(new User(username, encodedPassword));
    }

    @Transactional(readOnly = true)
    public Optional<User> findUser(String username) {
        var user = userRepository.findByUsername(username);
        if (user != null) {
            return Optional.of(user);
        }

        return Optional.empty();
    }
}
