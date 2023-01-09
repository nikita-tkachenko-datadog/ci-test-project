package com.datadog.ci.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

    @Test
    public void testAddition() {
        Calculator c = new Calculator();
        assertEquals(4, c.add(2, 2));
    }

    @Test
    public void testSubtraction() {
        Calculator c = new Calculator();
        assertEquals(4, c.add(2, -2));
    }

    @Test
    public void testMultiplication() {
        Calculator c = new Calculator();
        assertEquals(9, c.multiply(3, 3));
    }

    @Test
    public void testDivision() {
        Calculator c = new Calculator();
        assertEquals(12, c.divide(36, 3));
    }

}