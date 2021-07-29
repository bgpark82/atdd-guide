package com.bgpark.atdd.domain.user.acceptance;

import com.bgpark.atdd.AcceptanceTest;
import com.bgpark.atdd.domain.user.domain.User;
import com.bgpark.atdd.domain.user.domain.UserRepository;
import com.bgpark.atdd.domain.user.dto.UserFindResponse;
import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void find_사용자를_조회한다() {
        // given
        Long id = 1L;

        UserSaveRequest saveRequest = createSaveRequest("박병길", "123456");
        sendSaveRequest(saveRequest);


        // when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .when().get("/users/" + id)
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.as(UserFindResponse.class).getUsername()).isEqualTo("개발자_박병길");
        assertThat(response.as(UserFindResponse.class).getPassword()).isEqualTo("123456");
    }

    private void assertSaveResponse(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
