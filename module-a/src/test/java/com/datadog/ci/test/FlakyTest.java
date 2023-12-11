package com.datadog.ci.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;

public class FlakyTest {

    @Test
    public void testFlaky() {
        assertEquals(0, ThreadLocalRandom.current().nextInt(7));
    }
}
