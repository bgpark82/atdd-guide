package com.bgpark.atdd.domain.user.service;

import com.bgpark.atdd.domain.user.dto.UserFindResponse;
import com.bgpark.atdd.domain.user.dto.UserSaveRequest;

public interface UserService {

    void save(UserSaveRequest request);

    UserFindResponse findById(Long id);
}
