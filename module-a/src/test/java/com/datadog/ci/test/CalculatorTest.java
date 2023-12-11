package com.datadog.ci.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @BeforeAll
    public static void setUpClass() throws InterruptedException {
        System.out.println("Setting up " + CalculatorTest.class);
        Thread.sleep(50);
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        System.out.println("Setting up test case " + this);
        Thread.sleep(50);
    }

    @AfterAll
    public static void tearDownClass() throws InterruptedException {
        System.out.println("Tearing down " + CalculatorTest.class);
        Thread.sleep(50);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        System.out.println("Tearing down test case " + this);
        Thread.sleep(50);
    }

    @Test
    public void testAddition() {
        Calculator c = new Calculator();
        assertEquals(4, c.add(2, 2));
    }

    @Test
    public void testSubtraction() {
        Calculator c = new Calculator();
        assertEquals(0, c.add(2, -2));
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