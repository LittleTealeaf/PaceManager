package test.resources;

import data.interfaces.Identifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public interface TestIdentifiable {

    @Test
    void testIdentifiable();

    /**
     * Runs tests on instances of the identifiable factory.
     *
     * Checks the following
     * <ul><li>Creating two instances does not make them innately equal to each other</li>
     * <li>Creating an instance does not result in a null uuid</li><li>UUIDs between two objects are not the same</li></ul>
     *
     * @param factory
     */
    default void doIdentifiableTests(IdentifiableFactory factory) {
        Identifiable a = factory.create(), b = factory.create();
        assertNotEquals(a, b);
        assertNotNull(a.getUUID());
        assertNotNull(b.getUUID());
        assertNotEquals(a.getUUID(), b.getUUID());
    }

    interface IdentifiableFactory {

        Identifiable create();
    }
}
