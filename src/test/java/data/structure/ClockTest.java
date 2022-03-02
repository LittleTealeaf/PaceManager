package data.structure;

import data.api.IClock;
import org.junit.jupiter.api.Test;
import test.resources.Randomizable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ClockTest implements Randomizable {

    /**
     * Tests the {@link Clock#Clock()} constructor.
     *
     * Checks that it properly sets the initial value to 0
     */
    @Test
    void constructor() {
        Clock clock = new Clock();
        assertEquals(0, clock.getTime());
    }

    /**
     * Tests the {@link Clock#Clock(int)} constructor.
     *
     * Checks that it properly sets the initial value
     */
    @Test
    void constructorInt() {
        int val = RANDOM.nextInt();
        Clock clock = new Clock(val);
        assertEquals(val, clock.getTime());
    }

    /**
     * Tests the {@link Clock#Clock(IClock)} constructor.
     *
     * Checks that it properly copies the value of another clock
     */
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
        assertEquals(val, copyClock.getTime());
    }

    /**
     * Tests the {@link Clock#getTime()} method.
     *
     * Checks that it properly gets the time, whether the time was set using {@link Clock#Clock()} or {@link Clock#setTime(int)}
     */
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

    /**
     * Tests the {@link Clock#setTime(int)} method.
     *
     * Checks that it properly sets the value of the {@link Clock}
     */
    @Test
    void setTime() {
        Clock clock = new Clock();
        int val = RANDOM.nextInt();
        assertNotEquals(val, clock.getTime());
        clock.setTime(val);
        assertEquals(val, clock.getTime());
    }
}