package com.datadog.ci.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class CalculatorTest {

    @ClassRule
    public static final TestRule CLASS_RULE = (base, description) -> {
        System.out.println("Applying class rule " + CalculatorTest.class);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return base;
    };

    @Rule
    public final TestRule testCaseRule = (base, description) -> {
        System.out.println("Applying test case rule " + this);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return base;
    };

    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        System.out.println("Setting up " + CalculatorTest.class);
        Thread.sleep(50);
    }

    @Before
    public void setUp() throws InterruptedException {
        System.out.println("Setting up test case " + this);
        Thread.sleep(50);
    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        System.out.println("Tearing down " + CalculatorTest.class);
        Thread.sleep(50);
    }

    @After
    public void tearDown() throws InterruptedException {
        System.out.println("Tearing down test case " + this);
        Thread.sleep(50);
    }

    @Test
    public void testAddition() {
        Calculator c = new Calculator();
        assertEquals(4, c.add(2, 2));
    }

    @Ignore
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

//    public static final class InnerCalculatorTest {
//        // TODO this test is not executed by JUnit's runner
//        @Test
//        public void testAdditionInnerSuite() {
//            Calculator c = new Calculator();
//            assertEquals(42, c.add(21, 21));
//        }
//    }

}