package app.database;

import org.junit.jupiter.api.Test;

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

    }

    @Test
    void constructorFirstLast() {

    }

    @Test
    void getFirstNameEmpty() {

    }

    @Test
    void getFirstNameFull() {

    }

    @Test
    void getLastNameEmpty() {

    }

    @Test
    void getLastNameFull() {

    }

    @Test
    void getFullNameComplete() {

    }

    @Test
    void getFullNameFirstOnly() {

    }

    @Test
    void getFullNameLastOnly() {

    }

    @Test
    void getFullNameEmpty() {

    }
//
//    @Test
//    void getFirstName() {
//        String name = "test";
//        Rider rider = new Rider(name);
//        assertSame(name, rider.getFirstName());
//        rider = new Rider();
//        assertNotSame(name, rider.getFirstName());
//    }
//
//    @Test
//    void getLastName() {
//        String name = "test";
//        Rider rider = new Rider("", name);
//        assertSame(name, rider.getLastName());
//    }
//
//    @Test
//    void getFullName() {
//        String first = "a", last = "b", full = "a b";
//        Rider rider = new Rider(first, last);
//        assertEquals(full, rider.getFullName());
//    }
//
//    @Test
//    void setFirstName() {
//        Rider rider = new Rider();
//    }
//
//    @Test
//    void setLastName() {
//    }
}