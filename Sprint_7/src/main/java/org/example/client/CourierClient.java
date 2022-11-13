package org.example.client;

import io.restassured.response.ValidatableResponse;
import org.example.model.Courier;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {

    public static final String CREATE_PATH = "/api/v1/courier";
    public static final String LOGIN_PATH = "/api/v1/courier/login";

    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(CREATE_PATH)
                .then();

    }

    public ValidatableResponse loginCourier(Courier courier){
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    public ValidatableResponse deleteCourier(int id) {
        return given()
                .spec(getSpec())
                .when()
                .delete(CREATE_PATH+"/"+id)
                .then();
    }
}
