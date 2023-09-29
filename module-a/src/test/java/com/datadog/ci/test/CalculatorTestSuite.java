package com.datadog.ci.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CalculatorTest.class, ParameterizedCalculatorTest.class})
public class CalculatorTestSuite {

    @ClassRule
    public static final TestRule TEST_SUITE_RULE = (base, description) -> {
        System.out.println("Applying test-suite rule " + CalculatorTestSuite.class);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return base;
    };

    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        System.out.println("Setting up test suite " + CalculatorTestSuite.class);
        Thread.sleep(50);
    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        System.out.println("Tearing down test suite " + CalculatorTestSuite.class);
        Thread.sleep(50);
    }

}
