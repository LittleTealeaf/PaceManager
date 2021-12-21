package data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import serialization.Serializer;
import testutility.RandomFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RiderTest {

    @Test
    public void serialization(@TempDir Path tempDir) throws IOException, ClassNotFoundException {
        File file = tempDir.resolve("rider.ser").toFile();
        Serializer<Rider> serializer = new Serializer<>();
        Rider before = RandomFactory.randomRider();
        serializer.serialize(before, file);
        Rider after = serializer.deserialize(file);
        assertEquals(before, after);
    }



    @Test
    public void firstName() {
        Rider rider = new Rider();
        assertEquals("", rider.getFirstName());
        String name = RandomFactory.randomName();
        rider.setFirstName(name);
        Assertions.assertSame(name, rider.getFirstName());
    }

    @Test
    public void lastName() {
        Rider rider = new Rider();
        assertEquals("", rider.getLastName());
        String name = RandomFactory.randomName();
        rider.setLastName(name);
        Assertions.assertSame(name, rider.getLastName());
    }

    /**
     * Tests the following methods: {@link Rider#getName()}, {@link Rider#toString()}
     */
    @Test
    public void nameFull() {
        Rider rider = new Rider();
        String first = RandomFactory.randomName(), last = RandomFactory.randomName();
        rider.setFirstName(first);
        rider.setLastName(last);
        assertEquals(String.format("%s %s", first, last), rider.getName());
        assertEquals(String.format("%s %s", first, last), rider.toString());
    }

    /**
     * @see #constructorString()
     */
    @Test
    public void nameFirstOnly() {
        String name = RandomFactory.randomName();
        Rider rider = new Rider(name);
        assertEquals(name, rider.getName());
        assertEquals(name, rider.toString());
    }

    /**
     * @see #lastName()
     */
    @Test
    public void nameLastOnly() {
        String name = RandomFactory.randomName();
        Rider rider = new Rider();
        rider.setLastName(name);
        assertEquals(name, rider.getName());
        assertEquals(name, rider.toString());
    }

    @Test
    public void equals() {
        Rider a = new Rider();
        Rider b = new Rider();
        String first = RandomFactory.randomName(), last = RandomFactory.randomName();
        a.setFirstName(first);
        a.setLastName(last);
        assertEquals(a, a);
        Assertions.assertNotEquals(a, b);
        b.setFirstName(first);
        b.setLastName(last);
        assertEquals(a, b);
    }

    @Test
    public void constructorEmpty() {
        Rider rider = new Rider();
        assertEquals("", rider.getFirstName());
        assertEquals("", rider.getLastName());
    }

    @Test
    public void constructorString() {
        String name = RandomFactory.randomName();
        Rider rider = new Rider(name);
        assertEquals(name, rider.getFirstName());
        assertEquals("", rider.getLastName());
    }

    @Test
    public void constructorStringString() {
        String first = RandomFactory.randomName(), last = RandomFactory.randomName();
        Rider rider = new Rider(first, last);
        assertEquals(first, rider.getFirstName());
        assertEquals(last, rider.getLastName());
    }
}