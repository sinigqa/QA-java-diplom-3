package org.example.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.config.Endpoints;

import static io.restassured.RestAssured.given;

public class UserApi {

    @Step("Создание пользователя")
    public ValidatableResponse createUser(UserRequest user) {
        return given()
                .baseUri(Endpoints.BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register")
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .baseUri(Endpoints.BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then();
    }

    @Step("Авторизация пользователем")
    public ValidatableResponse loginUser(UserRequest user) {
        return given()
                .baseUri(Endpoints.BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login")
                .then();
    }
}