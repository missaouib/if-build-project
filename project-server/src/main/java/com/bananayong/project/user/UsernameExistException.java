package com.bananayong.project.user;

public class UsernameExistException extends RuntimeException {
    private final String username;

    UsernameExistException(String username, Exception e) {
        super(e);
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}
