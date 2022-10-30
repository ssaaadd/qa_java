package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineParTest {

    private final int kittensCount;

    public FelineParTest(int kittensCount) {
        this.kittensCount = kittensCount;
    }

    @Parameterized.Parameters
    public static Object[][] getKittensData() {
        return new Object[][]{
                {0},
                {7},
        };
    }

    @Test
    public void getKittens_ParamKitten_True() {
        Feline feline = new Feline();
        int actualGetKittens = feline.getKittens(kittensCount);
        Assert.assertEquals(kittensCount, actualGetKittens);
    }
}
