package org.example.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    @Step("Создание пользователя")
    public ValidatableResponse createUser(UserRequest user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/auth/register")
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/auth/user")
                .then();
    }

    @Step("Авторизация пользователем")
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