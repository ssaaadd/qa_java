package com.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FelineTest {


    @Test
    public void eatMeat_Feline_True() throws Exception {
        Feline feline = new Feline();
        List<String> expectedGetFood = Arrays.asList("Животные", "Птицы", "Рыба");
        List<String> actualGetFood = feline.eatMeat();
        assertEquals("Список еды не соответствует Кошачьим=Хищники", expectedGetFood, actualGetFood);
    }

    @Test
    public void getFamily_Feline_True() {
        Feline feline = new Feline();
        String expectedGetFamily = "Кошачьи";
        String actualGetFamily = feline.getFamily();
        assertEquals("Семейство должно быть Кошачьи", expectedGetFamily, actualGetFamily);
    }

    @Test
    public void getKittens_OneKitten_True() {
        Feline feline = new Feline();
        int expectedGetKittens = 1;
        int actualGetKittens = feline.getKittens();
        assertEquals("Кошачьи должны приносить котят", expectedGetKittens, actualGetKittens);
    }


}