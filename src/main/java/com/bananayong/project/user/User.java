package com.bananayong.project.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;

@Data
@ToString(exclude = "password")
@EqualsAndHashCode(of = "username")
public class User {
    private final String username;
    @JsonIgnore
    private final String password;
    private Instant createdAt = Instant.now();
}
