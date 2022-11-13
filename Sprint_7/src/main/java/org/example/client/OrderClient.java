package org.example.client;

import io.restassured.response.ValidatableResponse;
import org.example.model.Order;


import static io.restassured.RestAssured.given;

public class OrderClient extends Client {

    public static final String ORDERS_PATH = "/api/v1/orders";


    public ValidatableResponse getOrderList() {
        return given()
                .spec(getSpec())
                .when()
                .get(ORDERS_PATH)
                .then();
    }



    public ValidatableResponse createOrder(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then();

    }
}
