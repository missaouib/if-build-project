package com.bananayong.project.login.web;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
class LoginRequest {
    @NotEmpty
    String username;
    @NotEmpty
    String password;
}
