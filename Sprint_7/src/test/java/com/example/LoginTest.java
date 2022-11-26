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

public class LoginTest extends BaseTest {


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

    @Test
    public void courierLogin_Default_Logged() {
        ValidatableResponse responseCreate = courierClient.createCourier(courierDefault);
        ValidatableResponse responseLogin = courierClient.loginCourier(courierDefault);

        id = idExtract(responseLogin);
        int statusCodeActual = getStatusCodeActual(responseLogin);

        Assert.assertTrue("Курьер не может войти", id > 0);
        Assert.assertEquals("Статус код не 200", SC_OK, statusCodeActual);
    }

    @Test
    public void courierLogin_WithoutPass_NotLogged() {
        ValidatableResponse responseLogin = courierClient.loginCourier(courierWithoutPass);

        int statusCodeActual = getStatusCodeActual(responseLogin);
        String actualMessage = "Недостаточно данных для входа";
        message = responseLogin.extract().path("message");


        Assert.assertEquals("Запрещено Входить без обязательных полей", actualMessage, message);
        Assert.assertEquals("Статус код не 400", SC_BAD_REQUEST, statusCodeActual);
    }

    @Test
    public void courierLogin_WithoutLogin_NotLogged() {
        ValidatableResponse responseLogin = courierClient.loginCourier(courierWithoutLogin);

        int statusCodeActual = getStatusCodeActual(responseLogin);
        String actualMessage = "Недостаточно данных для входа";
        message = responseLogin.extract().path("message");


        Assert.assertEquals("Запрещено Входить без обязательных полей", actualMessage, message);
        Assert.assertEquals("Статус код не 400", SC_BAD_REQUEST, statusCodeActual);
    }

    @Test
    public void courierLogin_NotExist_NotLogged() {
        ValidatableResponse responseLogin = courierClient.loginCourier(courierNotExist);

        int statusCodeActual = getStatusCodeActual(responseLogin);
        String actualMessage = "Учетная запись не найдена";
        message = responseLogin.extract().path("message");


        Assert.assertEquals("Запрещено Входить без регистрации", actualMessage, message);
        Assert.assertEquals("Статус код не 404", SC_NOT_FOUND, statusCodeActual);
    }
}
