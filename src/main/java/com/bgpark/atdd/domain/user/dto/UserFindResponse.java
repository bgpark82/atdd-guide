package com.bgpark.atdd.domain.user.dto;

import lombok.Getter;

@Getter
public class UserFindResponse {

    private Long id;
    private String username;
    private String password;

    public UserFindResponse(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
