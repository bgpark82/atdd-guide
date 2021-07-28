package com.bgpark.atdd.domain.user.step;

import com.bgpark.atdd.domain.user.dto.UserSaveRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;

public class UserSteps {

    public static UserSaveRequest createSaveRequest(String username, String password) {
        UserSaveRequest saveRequest = new UserSaveRequest();
        ReflectionTestUtils.setField(saveRequest, "username", username);
        ReflectionTestUtils.setField(saveRequest, "password", password);
        return saveRequest;
    }

    public static ExtractableResponse<Response> sendSaveRequest(UserSaveRequest saveRequest) {
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(saveRequest)
                .when().post("/users")
                .then().log().all()
                .extract();
        return response;
    }
}
