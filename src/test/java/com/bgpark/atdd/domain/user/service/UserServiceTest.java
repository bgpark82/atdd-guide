package com.bgpark.atdd.domain.user.service;

import com.bgpark.atdd.domain.exception.UserNotFoundException;
import com.bgpark.atdd.domain.user.domain.User;
import com.bgpark.atdd.domain.user.domain.UserRepository;
import com.bgpark.atdd.domain.user.dto.UserFindResponse;
import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.bgpark.atdd.domain.user.step.UserSteps.createSaveRequest;
import static com.bgpark.atdd.domain.user.step.UserSteps.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    private UserSaveRequest request;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userRepository);
        request = createSaveRequest("박병길","123456");
    }

    @Test
    public void save_사용자를_저장한다() {
        // when
        userService.save(request);

        // then
        verify(userRepository).save(any());
    }

    @Test
    public void findById_사용자를_조회한다() {
        // given
        Long id = 1L;
        User user = createUser(id, "박병길", "123456");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // when
        UserFindResponse response = userService.findById(id);

        // then
        verify(userRepository).findById(1L);
        assertFindResponse(id, response);
    }

    @Test(expected = UserNotFoundException.class)
    public void findById_사용자_미존재시_에러발생한다() {
        // given
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // when
        userService.findById(id);
    }

    private void assertFindResponse(Long id, UserFindResponse response) {
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getUsername()).isEqualTo("개발자_박병길");
        assertThat(response.getPassword()).isEqualTo("123456");
    }
}
