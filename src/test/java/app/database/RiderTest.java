package app.database;

import org.junit.jupiter.api.Test;
import test.resources.RandomNames;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiderTest {

    @Test
    void constructorEmpty() {
        Rider rider = new Rider();
        assertEquals("", rider.getFirstName());
        assertEquals("", rider.getLastName());
    }

    @Test
    void constructorFirst() {
        String first = RandomNames.randomName();
        Rider rider = new Rider(first);
        assertEquals(first, rider.getFirstName());
        assertEquals("", rider.getLastName());
    }

    @Test
    void constructorFirstLast() {
        String first = RandomNames.randomName(), last = RandomNames.randomName();
        Rider rider = new Rider(first, last);
        assertEquals(first, rider.getFirstName());
        assertEquals(last, rider.getLastName());
    }

    @Test
    void getFirstNameEmpty() {
        Rider rider = new Rider();
        assertEquals("", rider.getFirstName());
    }

    @Test
    void getFirstNameFull() {
        String first = RandomNames.randomName();
        Rider rider = new Rider(first);
        assertEquals(first, rider.getFirstName());
    }

    @Test
    void getLastNameEmpty() {
        Rider rider = new Rider();
        assertEquals("", rider.getLastName());
    }

    @Test
    void getLastNameFull() {
        String last = RandomNames.randomName();
        Rider rider = new Rider(null, last);
        assertEquals(last, rider.getLastName());
    }

    @Test
    void getFullNameComplete() {
        String first = RandomNames.randomName(), last = RandomNames.randomName();
        Rider rider = new Rider(first, last);
        String complete = first + " " + last;
        assertEquals(complete, rider.getFullName());
    }

    @Test
    void getFullNameFirstOnly() {
        String first = RandomNames.randomName();
        Rider rider = new Rider(first);
        assertEquals(first, rider.getFullName());
    }

    @Test
    void getFullNameLastOnly() {
        String last = RandomNames.randomName();
        Rider rider = new Rider(null, last);
        assertEquals(last, rider.getFullName());
    }

    @Test
    void getFullNameEmpty() {
        Rider rider = new Rider();
        assertEquals("", rider.getFullName());
    }

    @Test
    void setFirstName() {
        Rider rider = new Rider();
        String first = RandomNames.randomName();
        rider.setFirstName(first);
        assertEquals(first, rider.getFirstName());
    }

    @Test
    void setLastName() {
        Rider rider = new Rider();
        String last = RandomNames.randomName();
        rider.setLastName(last);
        assertEquals(last, rider.getLastName());
    }
}