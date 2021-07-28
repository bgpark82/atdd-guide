package com.bgpark.atdd.domain.user.domain;

import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
