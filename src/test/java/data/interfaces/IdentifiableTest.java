package data.interfaces;

import org.junit.jupiter.api.Test;
import test.resources.ClassTests;

class IdentifiableTest {

    @Test
    public void testConstants() throws IllegalAccessException {
        ClassTests.testConstants(Identifiable.class);
    }
}