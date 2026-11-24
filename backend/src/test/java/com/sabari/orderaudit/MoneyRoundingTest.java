package com.sabari.orderaudit;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyRoundingTest {
    @Test
    void doubleDrifts() {
        double sum = 0.0;
        for (int i = 1; i <= 10; i++) {
            sum += 0.1;
        }

        assertNotEquals(1.0, sum);
    }
    //BigDecimal value and scale (decimal places) store pannum. 1.0 scale 1, 1.00 scale 2.
    //equals rendaiyum compare pannum, so 1.0.equals(1.00) is false.
    //compareTo value mattum compare pannum, so 0.
    @Test
    void bigDecimalSumOfTenthsEqualsOne() {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal tenth = new BigDecimal("0.1");
        for (int i = 1; i <= 10; i++) {
            sum = sum.add(tenth);
        }

        assertEquals(0, sum.compareTo(new BigDecimal("1.0")));
    }


}
