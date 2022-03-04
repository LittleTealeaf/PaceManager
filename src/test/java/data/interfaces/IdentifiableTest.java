package data.interfaces;

import data.interfaces.Identifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Provides tests used to test a class that extends the {@link Identifiable} class.
 *
 * @author Thomas Kwashnak
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IdentifiableTest {

    /**
     * Runs tests on instances of the identifiable factory.
     *
     * Checks the following
     * <ul><li>Creating two instances does not make them innately equal to each other</li>
     * <li>Creating an instance does not result in a null uuid</li><li>UUIDs between two objects are not the same</li></ul>
     *
     * @see #doIdentifiableTests(IdentifiableFactory)
     */
    @Test
    void testIdentifiable();

    /**
     * Performs the tests indicated in {@link #testIdentifiable()}
     *
     * @param factory The factory builder for the identifiable object
     */
    default void doIdentifiableTests(IdentifiableFactory factory) {
        Identifiable a = factory.create(), b = factory.create();
        assertNotEquals(a, b);
        assertNotNull(a.getUUID());
        assertNotNull(b.getUUID());
        assertNotEquals(a.getUUID(), b.getUUID());
    }

    /**
     * A simple factory that creates an instance of the Identifiable class
     */
    interface IdentifiableFactory {

        /**
         * Builds the Identifiable object
         *
         * @return A new Identifiable object instance
         */
        Identifiable create();
    }
}
