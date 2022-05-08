package test.org.tealeaf.pacemanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestRandomValues implements RandomValues {

    @Test
    public void testRandomNames() {
        Stream.generate(this::randomName).limit(NAMES.length * 2L).parallel().forEach(Assertions::assertNotNull);
    }

}
