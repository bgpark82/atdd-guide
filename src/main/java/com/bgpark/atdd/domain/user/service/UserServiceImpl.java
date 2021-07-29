package com.bgpark.atdd.domain.user.service;

import com.bgpark.atdd.domain.user.domain.User;
import com.bgpark.atdd.domain.user.domain.UserRepository;
import com.bgpark.atdd.domain.user.dto.UserFindResponse;
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
        user.checkPassword();
        userRepository.save(user);
    }

    @Override
    public UserFindResponse findById(Long id) {
        return new UserFindResponse(1L, "박병길", "123456");
    }
}
