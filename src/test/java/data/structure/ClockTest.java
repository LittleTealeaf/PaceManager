package data.structure;

import data.api.IClock;
import org.junit.jupiter.api.Test;
import test.resources.Randomizable;

import static org.junit.jupiter.api.Assertions.*;

public class ClockTest implements Randomizable {

    @Test
    public void testConstructor() {
        Clock clock = new Clock();
        assertEquals(0, clock.getTime());
    }

    @Test
    public void testConstructorInt() {
        int time = RANDOM.nextInt();
        Clock clock = new Clock(time);
        assertEquals(time, clock.getTime());
    }

    @Test
    public void testConstructorIClock() {
        //Test proper
        Clock a = new Clock(RANDOM.nextInt());
        Clock b = new Clock(a);
        assertEquals(a.getTime(), b.getTime());

        //Test Null
        Clock c = new Clock(null);
        assertEquals(0, c.getTime());
    }

    @Test
    public void testGetTime() {
        int time = RANDOM.nextInt();
        Clock clock = new Clock(time);
        assertEquals(time, clock.getTime());
        time = RANDOM.nextInt();
        assertNotEquals(time, clock.getTime());
        clock.setTime(time);
        assertEquals(time, clock.getTime());
    }

    @Test
    public void testGetAddIClock() {
        //Test a proper addition
        int timeA = RANDOM.nextInt(), timeB = RANDOM.nextInt();
        Clock clockA = new Clock(timeA), clockB = new Clock(timeB);
        IClock result = clockA.getAdd(clockB);
        assertNotSame(clockA, result);
        assertNotSame(clockB, result);
        assertEquals(timeA + timeB, result.getTime());

        //Test if adding a null value
        result = clockA.getAdd(null);
        assertNull(result);
    }

    @Test
    public void testGetAddInt() {
        int timeA = RANDOM.nextInt(), timeB = RANDOM.nextInt();
        Clock clockA = new Clock(timeA);
        IClock result = clockA.getAdd(timeB);
        assertNotSame(clockA, result);
        assertEquals(timeA + timeB, result.getTime());
    }

    @Test
    public void testGetSubtractIClock() {
        //Test proper subtract
        int timeA = RANDOM.nextInt(), timeB = RANDOM.nextInt();
        Clock clockA = new Clock(timeA), clockB = new Clock(timeB);
        IClock result = clockA.getSubtract(clockB);
        assertNotSame(clockA, result);
        assertNotSame(clockB, result);
        assertEquals(timeA - timeB, result.getTime());

        //Test if subtracting a null value
        result = clockA.getSubtract(null);
        assertNull(result);
    }

    @Test
    public void testGetSubtractInt() {
        int timeA = RANDOM.nextInt(), timeB = RANDOM.nextInt();
        Clock clock = new Clock(timeA);
        IClock result = clock.getSubtract(timeB);
        assertNotSame(clock, result);
        assertEquals(timeA - timeB, result.getTime());
    }

    @Test
    public void testGetAbs() {
        int time = RANDOM.nextInt();
        Clock clock = new Clock(time);
        Clock negClock = new Clock(-time);
        IClock clockAbs = clock.getAbs();
        IClock negClockAbs = negClock.getAbs();

        assertEquals(Math.abs(time), clockAbs.getTime());
        assertEquals(Math.abs(time), negClockAbs.getTime());
    }

    @Test
    public void testGetElapsed() {
        //Test proper elapsed
        int timeA = RANDOM.nextInt(), timeB = RANDOM.nextInt();
        Clock clockA = new Clock(timeA), clockB = new Clock(timeB);
        int diff = Math.abs(timeA - timeB);
        IClock resultA = clockA.getElapsed(clockB);
        IClock resultB = clockB.getElapsed(clockA);
        assertNotSame(clockA, resultA);
        assertNotSame(clockB, resultA);
        assertNotSame(resultA, resultB);
        assertEquals(diff, resultA.getTime());
        assertEquals(diff, resultB.getTime());

        //Test null
        IClock result = clockA.getElapsed(null);
        assertNull(result);
    }

    @Test
    public void testAddIClock() {
        int timeA = RANDOM.nextInt(), timeB = RANDOM.nextInt();
        Clock clockA = new Clock(timeA), clockB = new Clock(timeB);
        assertSame(clockA, clockA.add(clockB));
        assertEquals(timeA + timeB, clockA.getTime());

        assertSame(clockA, clockA.add(null));
        assertEquals(timeA + timeB, clockA.getTime());
    }

    @Test
    public void testAddInt() {
        int timeA = RANDOM.nextInt(), timeB = RANDOM.nextInt();
        Clock clock = new Clock(timeA);
        assertSame(clock, clock.add(timeB));
        assertEquals(timeA + timeB, clock.getTime());
    }

    @Test
    public void testSubtractIClock() {
        int timeA = RANDOM.nextInt(), timeB = RANDOM.nextInt();
        Clock clockA = new Clock(timeA), clockB = new Clock(timeB);
        assertSame(clockA, clockA.subtract(clockB));
        assertEquals(timeA - timeB, clockA.getTime());

        assertSame(clockA, clockA.subtract(null));
        assertEquals(timeA - timeB, clockA.getTime());
    }

    @Test
    public void testSubtractInt() {
        int timeA = RANDOM.nextInt(), timeB = RANDOM.nextInt();
        Clock clock = new Clock(timeA);
        assertSame(clock, clock.subtract(timeB));
        assertEquals(timeA - timeB, clock.getTime());
    }

    @Test
    public void testAbs() {
        int time = RANDOM.nextInt();
        Clock clock = new Clock(time);
        assertSame(clock, clock.abs());
        assertEquals(Math.abs(time), clock.getTime());

        clock = new Clock(-time);
        assertSame(clock, clock.abs());
        assertEquals(Math.abs(time), clock.getTime());
    }
}