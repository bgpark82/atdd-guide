package com.bgpark.atdd.domain.user.controller;

import com.bgpark.atdd.domain.ControllerTest;
import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import com.bgpark.atdd.domain.user.service.UserService;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static com.bgpark.atdd.domain.user.step.UserSteps.createSaveRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UserControllerTest extends ControllerTest{

    @MockBean
    private UserService userService;

    @Test
    public void save_사용자를_저장한다() throws Exception {
        // given
        UserSaveRequest saveRequest = createSaveRequest("박병길", "123");

        // when
        MockHttpServletResponse response = sendSaveRequest(saveRequest);

        // then
        verify(userService).save(any());
        assertSaveResponse(response);
    }

    private void assertSaveResponse(MockHttpServletResponse response) {
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    private MockHttpServletResponse sendSaveRequest(UserSaveRequest saveRequest) throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(saveRequest)))
                .andDo(print())
                .andReturn()
                .getResponse();
        return response;
    }
}


