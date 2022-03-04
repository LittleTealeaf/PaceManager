package data.structure;

import org.junit.jupiter.api.Test;
import test.resources.RandomUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RiderTest {

    /**
     * Tests the {@link Rider#Rider()} constructor.
     *
     * Checks that both the firstName and lastName are null
     */
    @Test
    public void constructor() {
        Rider rider = new Rider();
        assertNull(rider.getFirstName());
        assertNull(rider.getLastName());
    }

    /**
     * Tests the {@link Rider#Rider(String)} constructor.
     *
     * Checks that the firstName is set properly, and the last name is null
     */
    @Test
    public void constructorString() {
        String firstName = RandomUtils.randomName();
        Rider rider = new Rider(firstName);
        assertEquals(firstName, rider.getFirstName());
        assertNull(rider.getLastName());
    }

    /**
     * Tests the {@link Rider#Rider(String, String)} constructor.
     *
     * Checks that both the firstName and lastName are set properly.
     */
    @Test
    public void constructorStringString() {
        String firstName = RandomUtils.randomName();
        String lastName = RandomUtils.randomName();
        Rider rider = new Rider(firstName, lastName);
        assertEquals(firstName, rider.getFirstName());
        assertEquals(lastName, rider.getLastName());
    }
}