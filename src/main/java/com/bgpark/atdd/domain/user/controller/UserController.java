package com.bgpark.atdd.domain.user.controller;

import com.bgpark.atdd.domain.user.dto.UserFindResponse;
import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import com.bgpark.atdd.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity save(@Valid @RequestBody UserSaveRequest request) {
        userService.save(request);
        return ResponseEntity.created(URI.create("/users")).build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity find(@PathVariable Long id) {
        return ResponseEntity.ok(new UserFindResponse(1L, "박병길", "123456"));
    }
}