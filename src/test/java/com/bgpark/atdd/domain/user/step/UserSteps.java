package com.bgpark.atdd.domain.user.step;

import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import org.springframework.test.util.ReflectionTestUtils;

public class UserSteps {

    public static UserSaveRequest createSaveRequest(String username, String password) {
        UserSaveRequest saveRequest = new UserSaveRequest();
        ReflectionTestUtils.setField(saveRequest, "username", username);
        ReflectionTestUtils.setField(saveRequest, "password", password);
        return saveRequest;
    }
}
