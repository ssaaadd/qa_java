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
        String actualSound = cat.getSound();
        assertEquals("Мяу", actualSound);
    }

    @Test
    public void getFood_CatFood_True() throws Exception {
        Cat cat = new Cat(feline);
        List<String> mock = Arrays.asList("Животные", "Птицы", "Рыба");
        Mockito.when(cat.getFood()).thenReturn(mock);
        List<String> actualList = cat.getFood();
        List<String> expectedList = Arrays.asList("Животные", "Птицы", "Рыба");
        assertEquals(expectedList, actualList);
    }
}