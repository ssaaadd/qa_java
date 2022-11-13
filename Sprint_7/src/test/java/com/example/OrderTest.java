package com.example;

import io.restassured.response.ValidatableResponse;

import org.example.client.OrderClient;

import org.example.generators.OrderGenerator;
import org.example.model.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final Order order;
    private final int statusCode;
    private OrderClient orderClient;
    private Object message;
    private Order orderDefault;
    private int track;


    public OrderTest(Order order, int statusCode) {
        this.order = order;
        this.statusCode = statusCode;
    }

    // test data
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {OrderGenerator.getOrderBlack(), SC_CREATED},
                {OrderGenerator.getDefault(), SC_CREATED},
                {OrderGenerator.getOrderBlackGrey(), SC_CREATED},

        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
//        orderDefault = OrderGenerator.getDefault();

    }

    @Test
    public void getOrderListTrue() {
        ValidatableResponse responseCreate = orderClient.getOrderList();

        message = responseCreate.extract().path("orders");
        int statusCodeActual = getStatusCodeActual(responseCreate);


        Assert.assertNotNull("Список заказов пуст", message);
        Assert.assertEquals("Статус код не 200", SC_OK, statusCodeActual);

    }

    @Test
    public void createOrder_Ok() {
        ValidatableResponse responseCreate = orderClient.createOrder(order);

        track = trackExtract(responseCreate);
        int statusCodeActual = getStatusCodeActual(responseCreate);


        Assert.assertTrue("Нет номера отслеживания", track > 0);
        Assert.assertEquals("Статус код не 201", statusCode, statusCodeActual);

    }


}
