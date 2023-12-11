package com.datadog.ci.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringJoinerTest {

    @Test
    public void join() {
        StringJoiner stringJoiner = new StringJoiner();
        String joined = stringJoiner.join(",", "a", "b", "c");
        assertEquals("a,b,c", joined);
    }
}