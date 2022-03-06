package data.interfaces;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Provides tests used to test a class that extends the {@link Identifiable} class.
 *
 * @author Thomas Kwashnak
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IdentifiableTest {

    /**
     * Runs tests on the Identifiable object.
     *
     * Tests are performed in {@link #doIdentifiableTests(Builder)}.
     *
     * @see #doIdentifiableTests(Builder)
     */
    @Test
    void testIdentifiable();

    /**
     * Runs tests on instances of the identifiable builder
     *
     * <ul><li>Creating two instances does not make them innately equal to each other</li>
     * <li>Creating an instance does not result in a null uuid</li><li>UUIDs between two objects are not the same</li><li>The UUID of a given
     * object is persistent between fetches</li></ul>
     *
     * @param builder The factory builder for the identifiable object
     */
    default void doIdentifiableTests(Builder builder) {
        Identifiable a = builder.create(), b = builder.create();
        assertNotEquals(a, b);
        assertNotNull(a.getUUID());
        assertNotNull(b.getUUID());
        assertNotEquals(a.getUUID(), b.getUUID());
        UUID uuid = a.getUUID();
        assertEquals(uuid, a.getUUID());
    }

    /**
     * A simple factory that creates an instance of the Identifiable class
     */
    interface Builder {

        /**
         * Builds the Identifiable object
         *
         * @return A new Identifiable object instance
         */
        Identifiable create();
    }
}
