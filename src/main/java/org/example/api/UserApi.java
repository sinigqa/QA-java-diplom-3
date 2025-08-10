package org.example.api;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    public ValidatableResponse createUser(UserRequest user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/auth/register")
                .then();
    }

    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/auth/user")
                .then();
    }

    public ValidatableResponse loginUser(UserRequest user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/auth/login")
                .then();
    }
}