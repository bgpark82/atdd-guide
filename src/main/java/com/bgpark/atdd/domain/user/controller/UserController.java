package com.bgpark.atdd.domain.user.controller;

import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity save(@Valid @RequestBody UserSaveRequest request) {
        return ResponseEntity.created(URI.create("/users")).build();
    }
}