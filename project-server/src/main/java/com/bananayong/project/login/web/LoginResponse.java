package com.bananayong.project.login.web;

import lombok.Value;

import java.time.Instant;

@Value(staticConstructor = "of")
class LoginResponse {
    String username;
    Instant loginAt;
}
