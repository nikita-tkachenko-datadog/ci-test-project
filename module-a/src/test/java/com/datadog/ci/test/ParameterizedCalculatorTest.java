
package com.datadog.ci.test;

import static org.junit.Assert.assertEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

final class ParameterizedCalculatorTests {}

@Category(ParameterizedCalculatorTests.class)
@RunWith(JUnitParamsRunner.class)
public class ParameterizedCalculatorTest {

    @ClassRule
    public static final TestRule CLASS_RULE = (base, description) -> {
        System.out.println("Applying class rule " + ParameterizedCalculatorTest.class);
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
        System.out.println("Setting up " + ParameterizedCalculatorTest.class);
        Thread.sleep(50);
    }

    @Before
    public void setUp() throws InterruptedException {
        System.out.println("Setting up test case " + this);
        Thread.sleep(50);
    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        System.out.println("Tearing down " + ParameterizedCalculatorTest.class);
        Thread.sleep(50);
    }

    @After
    public void tearDown() throws InterruptedException {
        System.out.println("Tearing down test case " + this);
        Thread.sleep(50);
    }

    @Test
    @Parameters({
            "0, 0, 0",
            "1, 1, 2"
    })
    public void shouldReturnCorrectSum(int first,
                                       int second,
                                       int expectedSum) {
        Calculator calculator = new Calculator();
        assertEquals(expectedSum, calculator.add(first, second));
    }

    @Test
    @Parameters({
            "0, 0, 0",
            "1, 1, 1",
            "3, 4, 12",
    })
    public void shouldReturnCorrectMultiple(int first,
                                            int second,
                                            int expectedMultiple) {
        Calculator calculator = new Calculator();
        assertEquals(expectedMultiple, calculator.multiply(first, second));
    }

}