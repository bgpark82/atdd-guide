package com.bgpark.atdd.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity save() {
        return ResponseEntity.created(URI.create("/users")).build();
    }
}