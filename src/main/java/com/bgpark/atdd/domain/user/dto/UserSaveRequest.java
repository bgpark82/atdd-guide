package com.bgpark.atdd.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserSaveRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
