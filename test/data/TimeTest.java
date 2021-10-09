package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static test.RandGen.randomTimeValue;
import test.RandGen;

public class TimeTest {

    /**
     * The number of times each "repeating" test case should execute for
     */
    private static final int NUM_TRIALS = 1024;



    /**
     * Tests the {@link Time#Time(long)} constructor. Executes {@link #NUM_TRIALS} times.
     * <br>Creates random values using {@link RandGen#randomTimeValue()}, creates
     * a {@link Time} object using the {@code Time(long)} constructor, and checks if {@link Time#getValue()} is equal
     * to the original value
     */
    @Test
    public void timeFromMS() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            long val = randomTimeValue();
            assertEquals(new Time(val).getValue(), val);
        }
    }

    /**
     * Tests the {@link Time#Time(String)} constructor. Executes {@link #NUM_TRIALS} times.
     * <ul><li>Creates a new time value using the {@link RandGen#randomTimeValue()} constructor, pruning off the last 3 digits,
     * which are not represented in {@link Time#toString()}</li>
     * <li>Creates a new time object using the random value. </li>
     * <li>Checks if the original value is equal to the value from creating a time object using the previous time
     * object's {@link Time#toString()} method</li></ul>
     */
    @Test
    public void timeFromString() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            long val = ((new Time(randomTimeValue()).getValue()) / 1000) * 1000;
            Time testTime = new Time(val);
            assertEquals(val, new Time(testTime.toString()).getValue());
        }
    }

    /**
     * Tests the {@link Time#difference(Time, Time)} constructor in cases where one or more of the parameters is {@code null}.
     * Tests when the first, second, and both parameters are null.
     */
    @Test
    public void differenceNull() {
        assertNull(Time.difference(null, null));
        assertNull(Time.difference(RandGen.randomTime(), null));
        assertNull(Time.difference(null, RandGen.randomTime()));
    }


    @Test
    public void difference() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            long a = randomTimeValue(), b = randomTimeValue();
            long difference = b - a;
            assertEquals(Time.difference(new Time(a), new Time(b)).getValue(), difference);
        }
    }

    @Test
    public void absolute() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            long val = randomTimeValue();
            assertEquals(new Time(val * (i % 2 == 0 ? -1 : 1)).absolute().getValue(), val);
        }
    }

    @Test
    public void getValue() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            long val = randomTimeValue();
            Time time = new Time(val);
            assertEquals(time.getValue(), val);
        }
    }

    @Test
    public void add() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            long a = randomTimeValue(), b = randomTimeValue();
            long sum = a + b;
            assertEquals(new Time(a).add(new Time(b)).getValue(), sum);
        }
    }

    @Test
    public void subtract() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            long a = randomTimeValue(), b = randomTimeValue();
            long difference = a - b;
            assertEquals(new Time(a).subtract(new Time(b)).getValue(), difference);
        }
    }

    @Test
    public void compareTo() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            long a = randomTimeValue(), b = randomTimeValue();
            assertEquals(new Time(a).compareTo(new Time(b)), a < b ? -1 : 1);
        }
    }

    @Test
    public void testEquals() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            long val = randomTimeValue();
            assertEquals(new Time(val), new Time(val));
            assertNotEquals(new Time(val), new Time(val + 1));
        }
    }
}