package com.bgpark.atdd.domain.user.service;

import com.bgpark.atdd.domain.user.domain.User;
import com.bgpark.atdd.domain.user.domain.UserRepository;
import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void save(UserSaveRequest request) {
        User user = new User(request.getUsername(), request.getPassword());
        userRepository.save(user);
    }
}
