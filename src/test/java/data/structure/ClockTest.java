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

    /**
     * Tests the {@link Clock#add(IClock)} method.
     *
     * Checks that adding two clocks together results in a clock whose value is the sum of the two clock values
     */
    @Test
    void add() {
        int a = RANDOM.nextInt(), b = RANDOM.nextInt();
        Clock clockA = new Clock(a), clockB = new Clock(b);
        assertEquals(a + b, clockA.add(clockB).getTime());
    }

    /**
     * Tests that {@link Clock#subtract(IClock)} method.
     *
     * Checks that subtracting a clock B from A returns a new Clock object with the proper difference
     */
    @Test
    void subtract() {
        int a = RANDOM.nextInt(), b = RANDOM.nextInt();
        Clock clockA = new Clock(a), clockB = new Clock(b);
        assertEquals(a - b, clockA.subtract(clockB).getTime());
    }

    /**
     * Tests the {@link Clock#difference(IClock)} method.
     *
     * Checks that taking the absolute difference of two clocks with set values returns the absolute difference
     */
    @Test
    void difference() {
        int a = RANDOM.nextInt(), b = RANDOM.nextInt();
        Clock clockA = new Clock(a), clockB = new Clock(b);
        assertEquals(Math.abs(a - b), clockA.difference(clockB).getTime());
    }
}