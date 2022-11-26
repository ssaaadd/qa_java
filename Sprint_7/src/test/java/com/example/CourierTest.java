package com.example;

import io.restassured.response.ValidatableResponse;
import org.example.client.CourierClient;
import org.example.generators.CourierGenerator;
import org.example.model.Courier;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;


public class CourierTest extends BaseTest {


    private CourierClient courierClient;
    private Courier courierDefault;
    private int id;
    private Courier courierWithoutPass;
    private String message;
    private Courier courierNotExist;
    private Courier courierWithoutLogin;

    @Before
    public void setUp() {
//        Создаем шаблоны курьеров
        courierClient = new CourierClient();
        courierDefault = CourierGenerator.getDefault();
        courierWithoutPass = CourierGenerator.getWithoutPassField();
        courierWithoutLogin = CourierGenerator.getWithoutLoginField();
        courierNotExist = CourierGenerator.getNotExist();
    }

    @After
    public void cleanUp() {
//        Удаляем созданного курьера
        courierClient.deleteCourier(id);
    }

    /**
     * Тесты создания курьера
     */

    @Test
    public void courierCreate_Default_CanBeCreated() {

        ValidatableResponse responseCreate = courierClient.createCourier(courierDefault);
        ValidatableResponse responseLogin = courierClient.loginCourier(courierDefault);

        id = idExtract(responseLogin);
        int statusCodeActual = getStatusCodeActual(responseCreate);

        boolean isCourierCreatedActual = responseCreate.extract().path("ok");

        Assert.assertTrue("Курьер не создан", isCourierCreatedActual);
        Assert.assertEquals("Статус код не 201", SC_CREATED, statusCodeActual);


    }

    @Test
    public void createCourier_WithoutPass_NotBeCreated() {

        ValidatableResponse responseCreate = courierClient.createCourier(courierWithoutPass);

        int statusCodeActual = getStatusCodeActual(responseCreate);
        message = responseCreate.extract().path("message");
        String actualMessage = "Недостаточно данных для создания учетной записи";


        Assert.assertEquals("Запрещено Создание курьера без обязательных полей", actualMessage, message);
        Assert.assertEquals("Статус код не 400", statusCodeActual, SC_BAD_REQUEST);


    }

    @Test
    public void createCourier_WithoutLogin_NotBeCreated() {

        ValidatableResponse responseCreate = courierClient.createCourier(courierWithoutLogin);

        int statusCodeActual = getStatusCodeActual(responseCreate);
        message = responseCreate.extract().path("message");
        String actualMessage = "Недостаточно данных для создания учетной записи";


        Assert.assertEquals("Запрещено Создание курьера без обязательных полей", actualMessage, message);
        Assert.assertEquals("Статус код не 400", statusCodeActual, SC_BAD_REQUEST);


    }

    @Test
    public void createCourier_DoubleCourier_NotBeCreated() {

        ValidatableResponse responseCreate = courierClient.createCourier(courierDefault);
        ValidatableResponse responseLogin = courierClient.loginCourier(courierDefault);
        id = idExtract(responseLogin);

        ValidatableResponse responseDoubleCreate = courierClient.createCourier(courierDefault);

        int statusCodeActual = getStatusCodeActual(responseDoubleCreate);
        message = responseDoubleCreate.extract().path("message");
        String actualMessage = "Этот логин уже используется. Попробуйте другой.";

        Assert.assertEquals("Запрещено Создание курьеров с одинаковыми Login/Pass", actualMessage, message);
        Assert.assertEquals("Статус код не 409", statusCodeActual, SC_CONFLICT);


    }


}
