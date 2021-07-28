package com.bgpark.atdd.domain.user.service;

import com.bgpark.atdd.domain.user.domain.UserRepository;
import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.bgpark.atdd.domain.user.step.UserSteps.createSaveRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void save_사용자를_저장한다() {
        // given
        UserService userService = new UserServiceImpl();
        UserSaveRequest request = createSaveRequest("박병길","123");

        // when
        userService.save(request);

        // then
        verify(userRepository).save(any());
    }
}
