package com.bgpark.atdd.domain.user.controller;

import com.bgpark.atdd.domain.ControllerTest;
import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import com.bgpark.atdd.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.bgpark.atdd.domain.user.step.UserSteps.createSaveRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper mapper;

    @MockBean private UserService userService;

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


