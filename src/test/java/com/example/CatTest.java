package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)


public class CatTest {

    @Mock
    Feline feline;

    @Test
    public void getSound_Meow_True() {
        Cat cat = new Cat(feline);
        String expectedGetSound = "Мяу";
        String actualGetSound = cat.getSound();
        assertEquals("Кошки должны мяукать", expectedGetSound, actualGetSound);
    }

    @Test
    public void getFood_CatFood_True() throws Exception {
        Cat cat = new Cat(feline);
        Mockito.when(feline.eatMeat()).thenReturn(Arrays.asList("Животные", "Птицы", "Рыба"));
        List<String> expectedGetFood = Arrays.asList("Животные", "Птицы", "Рыба");
        List<String> actualGetFood = cat.getFood();
        assertEquals("Список еды не соответствует Кошке=Хищнику", expectedGetFood, actualGetFood);
    }
}