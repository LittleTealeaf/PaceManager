package data.structure;

import data.api.IClock;
import org.junit.jupiter.api.Test;
import test.resources.Randomizable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ClockTest implements Randomizable {

    @Test
    void constructor() {
        Clock clock = new Clock();
        assertEquals(0, clock.getTime());
    }

    @Test
    void constructorInt() {
        int val = RANDOM.nextInt();
        Clock clock = new Clock(val);
        assertEquals(val,clock.getTime());
    }

    @Test
    void constructorIClock() {
        int val = RANDOM.nextInt();
        IClock clock = new Clock() {
            @Override
            public int getTime() {
                return val;
            }
        };
        Clock copyClock = new Clock(clock);
        assertEquals(val,copyClock.getTime());
    }

    @Test
    void getTime() {
        int val = RANDOM.nextInt();
        Clock clock = new Clock(val);
        assertEquals(val, clock.getTime());
        val = RANDOM.nextInt();
        assertNotEquals(val, clock.getTime());
        clock.setTime(val);
        assertEquals(val, clock.getTime());
    }

    @Test
    void setTime() {
        Clock clock = new Clock();
        int val = RANDOM.nextInt();
        assertNotEquals(val, clock.getTime());
        clock.setTime(val);
        assertEquals(val, clock.getTime());
    }
}