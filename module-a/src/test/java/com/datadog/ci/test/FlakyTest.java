package com.datadog.ci.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;

public class FlakyTest {

    @Test
    public void testFlaky() {
        assertEquals(0, ThreadLocalRandom.current().nextInt(6));
    }
}
