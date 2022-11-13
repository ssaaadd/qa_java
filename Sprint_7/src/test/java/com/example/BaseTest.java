package com.example;

import io.restassured.response.ValidatableResponse;

public class BaseTest {

    private int id;
    private int track;

    public int getStatusCodeActual(ValidatableResponse responseLogin) {
        int statusCodeActual = responseLogin.extract().statusCode();
        return statusCodeActual;
    }

    public int idExtract(ValidatableResponse responseLogin) {
        return id = responseLogin.extract().path("id");
    }

    public int trackExtract(ValidatableResponse responseCreate) {
        return track = responseCreate.extract().path("track");
    }
}
