package data.api;

import org.junit.jupiter.api.Test;
import test.resources.ClassTests;

class IClockTest {

    @Test
    public void testConstants() throws IllegalAccessException {
        ClassTests.testConstants(IClock.class);
    }
}