package com.example;

import io.restassured.response.ValidatableResponse;

public class BaseTest {

    public int getStatusCodeActual(ValidatableResponse responseLogin) {
        int statusCodeActual = responseLogin.extract().statusCode();
        return statusCodeActual;
    }

    public int idExtract(ValidatableResponse responseLogin) {
        return responseLogin.extract().path("id");
    }

    public int trackExtract(ValidatableResponse responseCreate) {
        return responseCreate.extract().path("track");
    }
}
