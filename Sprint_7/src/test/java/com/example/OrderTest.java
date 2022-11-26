package com.example;

import io.restassured.response.ValidatableResponse;
import org.example.client.OrderClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class OrderTest extends BaseTest {


    private OrderClient orderClient;
    private Object message;


    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void getOrderListTrue() {
        ValidatableResponse responseCreate = orderClient.getOrderList();

        message = responseCreate.extract().path("orders");
        int statusCodeActual = getStatusCodeActual(responseCreate);


        Assert.assertNotNull("Список заказов пуст", message);
        Assert.assertEquals("Статус код не 200", SC_OK, statusCodeActual);

    }


}
