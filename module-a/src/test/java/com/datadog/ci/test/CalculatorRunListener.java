package com.datadog.ci.test;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class CalculatorRunListener extends RunListener {
    @Override
    public void testRunStarted(Description description) throws Exception {
        System.err.println("MY_TEST_ENV_VAR: " + System.getenv("MY_TEST_ENV_VAR"));
        System.out.println("CalculatorRunListener.testRunStarted: " + description);
        Thread.sleep(50);
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        System.out.println("CalculatorRunListener.testRunFinished: " + result);
        Thread.sleep(50);
    }

    @Override
    public void testSuiteStarted(Description description) throws Exception {
        System.out.println("CalculatorRunListener.testSuiteStarted: " + description);
        Thread.sleep(50);
    }

    @Override
    public void testSuiteFinished(Description description) throws Exception {
        System.out.println("CalculatorRunListener.testSuiteFinished: " + description);
        Thread.sleep(50);
    }

    @Override
    public void testStarted(Description description) throws Exception {
        System.out.println("CalculatorRunListener.testStarted: " + description);
        Thread.sleep(50);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("CalculatorRunListener.testFinished: " + description);
        Thread.sleep(50);
    }
}
