package data.structure;


import static org.junit.jupiter.api.Assertions.*;

import javax.swing.LayoutStyle;

import org.junit.jupiter.api.Test;

import test.resources.Randomizable;


public class RiderTest implements Randomizable {
    
    @Test
    public void testConstructor() {
        Rider rider = new Rider();
        assertEquals(null, rider.getFirstName());
        assertEquals(null, rider.getLastName());
    }

    @Test
    public void testConstructorString() {
        String name = randomName();
        Rider rider = new Rider(name);
        assertEquals(name, rider.getFirstName());
        assertEquals(null, rider.getLastName());
    }

    @Test
    public void testConstructorStringString() {
        String firstName = randomName();
        String lastName = randomName();
        Rider rider = new Rider(firstName,lastName);
        assertEquals(firstName, rider.getFirstName());
        assertEquals(lastName, rider.getLastName());
    }


    @Test
    public void testGetFirstName() {
        Rider rider = new Rider();
        assertEquals(null, rider.getFirstName());
        String name = rider.getFirstName();
        rider.setFirstName(name);
        assertEquals(name, rider.getFirstName());
        rider = new Rider(name);
        assertEquals(name, rider.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        Rider rider = new Rider();
        String name = randomName();
        rider.setFirstName(name);
        assertEquals(name, rider.getFirstName());
        name = randomName();
        assertNotEquals(name, rider.getFirstName());
        rider.setFirstName(null);
        assertEquals(null, rider.getFirstName());
        rider.setFirstName(name);
        assertEquals(name, rider.getFirstName());
    }
    
    @Test
    public void testGetLastName() {
        Rider rider = new Rider();
        assertEquals(null, rider.getLastName());
        String name = randomName();
        rider.setLastName(name);
        assertEquals(name, rider.getLastName());
        rider = new Rider(randomName(),name);
        assertEquals(name, rider.getLastName());
    }

    @Test
    public void testSetLastName() {
        Rider rider =  new Rider();
        String name = randomName();
        rider.setLastName(name);
        assertEquals(name, rider.getLastName());
        name = randomName();
        assertNotEquals(name, rider.getLastName());
        rider.setLastName(null);
        assertEquals(null, rider.getLastName());
        rider.setLastName(name);
        assertEquals(name, rider.getLastName());
    }

    @Test
    public void testToString() {
        Rider rider = new Rider();
        assertEquals(rider.getFullName(), rider.toString());
        rider = new Rider(randomName());
        assertEquals(rider.getFullName(), rider.toString());
        rider = new Rider(randomName(),randomName());
        assertEquals(rider.getFullName(), rider.toString());
    }

    @Test
    public void testGetFullName() {
        Rider rider = new Rider();
        assertEquals("", rider.getFullName());
        String firstName = randomName();
        rider = new Rider(firstName);
        assertEquals(firstName, rider.getFullName());
        firstName = randomName();
        String lastName = randomName();
        rider = new Rider(firstName,lastName);
        assertEquals(firstName + " " + lastName, rider.getFullName());
        lastName = randomName();
        rider = new Rider(null,lastName);
        assertEquals(lastName, rider.getFullName());
    }
}
