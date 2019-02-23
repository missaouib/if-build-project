package com.bananayong.project.user.web;

import com.bananayong.project.user.User;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Value
class UserDTO {
    Long id;
    String username;
    Instant createdAt;

    static UserDTO toDTO(@NotNull User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getCreatedAt()
        );
    }
}
