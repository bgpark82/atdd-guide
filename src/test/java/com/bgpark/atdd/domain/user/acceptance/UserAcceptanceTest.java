package com.bgpark.atdd.domain.user.acceptance;

import com.bgpark.atdd.AcceptanceTest;
import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.bgpark.atdd.domain.user.step.UserSteps.createSaveRequest;
import static com.bgpark.atdd.domain.user.step.UserSteps.sendSaveRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class UserAcceptanceTest extends AcceptanceTest {

    @Test
    public void save_사용자를_저장한다() {
        // given
        UserSaveRequest saveRequest = createSaveRequest("박병길", "123456");

        // when
        ExtractableResponse<Response> response = sendSaveRequest(saveRequest);

        // then
        assertSaveResponse(response);
    }

    private void assertSaveResponse(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
