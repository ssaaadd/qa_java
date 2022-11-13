package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Feline feline;

    @Test(expected = Exception.class)
    public void lionConstructor_OtherSex_Exception() throws Exception {
        Lion lion = new Lion("Третий_Пол", feline);
        lion.doesHaveMane();
    }

    @Test(expected = AssertionError.class)
    public void lionConstructor_OtherSex_ExceptionText() throws AssertionError {
        try {
            Lion lion = new Lion("Третий_Пол", feline);
            Assert.fail("Expected AssertionError");
        } catch (Exception thrown) {
            Assert.assertNotEquals("Используйте допустимые значения пола животного - самец или самка", thrown.getMessage());
        }
    }

    @Test
    public void doesHaveMane_LionIsMale_True() throws Exception {
        Lion lion = new Lion("Самец", feline);
        boolean expectedHasMane = true;
        boolean actualHasMane = lion.doesHaveMane();
        assertEquals("У льва должна быть грива", expectedHasMane, actualHasMane);
    }


    @Test
    public void getKittens_LionIsMale_True() throws Exception {
        Lion lion = new Lion("Самец", feline);
        int expectedGetKittens = 0;
        int actualGetKittens = lion.getKittens();
        assertEquals("Лев не может приносить котят", expectedGetKittens, actualGetKittens);
    }

    @Test
    public void getFood_Lion_True() throws Exception {
        Lion lion = new Lion("Самец", feline);
        Mockito.when(feline.getFood("Хищник")).thenReturn(Arrays.asList("Животные", "Птицы", "Рыба"));
        List<String> expectedGetFood = Arrays.asList("Животные", "Птицы", "Рыба");
        List<String> actualGetFood = lion.getFood();
        assertEquals("Список еды не соответствует Львиному=Хищнику", expectedGetFood, actualGetFood);
    }


}