package data.structure;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ClockTest {



    @Test
    void emptyConstructor() {
        Clock clock = new Clock();
        assertEquals(0,clock.getTime());
    }

    @Test
    void getTime() {
        int val = new Random().nextInt();
        Clock clock = new Clock(val);
        assertEquals(val,clock.getTime());
        val = new Random().nextInt();
        assertNotEquals(val,clock.getTime());
        clock.setTime(val);
        assertEquals(val,clock.getTime());
    }

    @Test
    void setTime() {
        Clock clock = new Clock();
        int val = new Random().nextInt();
        assertNotEquals(val,clock.getTime());
        clock.setTime(val);
        assertEquals(val,clock.getTime());
    }
}