package com.datadog.ci.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PersonTest {

    @Test
    public void testNameGetter() {
        Person p = new Person("John");
        assertEquals("John", p.getName());
    }

}
