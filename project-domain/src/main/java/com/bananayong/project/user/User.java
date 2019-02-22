package com.bananayong.project.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Data
@ToString(exclude = "password")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private Instant createdAt = Instant.now();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
