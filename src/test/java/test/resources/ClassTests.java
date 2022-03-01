package test.resources;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class ClassTests {
    public static void testConstants(Class type) throws IllegalAccessException {
        for(Field field : type.getFields()) {
            assertNotNull(field.get(null));
        }
    }
}
