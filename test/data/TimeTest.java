package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    private static final int NUM_TRIALS = 1024;

    public static Time randomTime() {
        return new Time((long) (86400000L * Math.random()));
    }

    /**
     * Generates a random time value between 00:00 - 23:59
     * @return A value within the bounds {@code [0,86400000)}
     * @see Time
     */
    public static long randomTimeValue() {
        return (long) (86400000L * Math.random());
    }

    /**
     * Tests the {@link Time#Time(long)} constructor. Creates random values using {@link #randomTimeValue()}, creates
     * a {@link Time} object using the {@code Time(long)} constructor, and checks if {@link Time#getValue()} is equal
     * to the original value
     */
    @Test
    public void timeFromMS() {
        for(int i = 0; i < NUM_TRIALS; i++) {
            long val = randomTimeValue();
            assertEquals(new Time(val).getValue(),val);
        }
    }

    @Test
    public void timeFromString() {
       for(int i = 0; i < NUM_TRIALS; i++) {
           long val = ((new Time(randomTimeValue()).getValue())/1000) * 1000;
           Time testTime = new Time(val);
           assertEquals(val,new Time(testTime.toString()).getValue());
       }
    }

    @Test
    public void differenceNull() {
        assertNull(Time.difference(null,null));
        assertNull(Time.difference(randomTime(),null));
        assertNull(Time.difference(null,randomTime()));
    }

    @Test
    public void difference() {
        for(int i = 0; i < NUM_TRIALS; i++) {
            long a = randomTimeValue(), b = randomTimeValue();
            long difference = b - a;
            assertEquals(Time.difference(new Time(a),new Time(b)).getValue(),difference);
        }
    }

    @Test
    public void absolute() {
        for(int i = 0; i < NUM_TRIALS; i++) {
            long val = randomTimeValue();
            assertEquals(new Time(val * (i%2==0 ? -1 : 1)).absolute().getValue(),val);
        }
    }

    @Test
    public void getValue() {
        for(int i = 0; i < NUM_TRIALS; i++) {
            long val = randomTimeValue();
            Time time = new Time(val);
            assertEquals(time.getValue(),val);
        }
    }

    @Test
    public void add() {
        for(int i = 0; i < NUM_TRIALS; i++) {
            long a = randomTimeValue(), b = randomTimeValue();
            long sum = a + b;
            assertEquals(new Time(a).add(new Time(b)).getValue(),sum);
        }
    }

    @Test
    public void subtract() {
        for(int i = 0; i < NUM_TRIALS; i++) {
            long a = randomTimeValue(), b = randomTimeValue();
            long difference = a - b;
            assertEquals(new Time(a).subtract(new Time(b)).getValue(),difference);
        }
    }

    @Test
    public void compareTo() {
       for(int i = 0; i < NUM_TRIALS; i++) {
           long a = randomTimeValue(), b = randomTimeValue();
           assertEquals(new Time(a).compareTo(new Time(b)),a < b ? -1 : 1);
       }
    }

    @Test
    public void testEquals() {
        assertFalse(new Time(randomTimeValue()).equals(""));
        for(int i = 0; i < NUM_TRIALS; i++) {
            long val = randomTimeValue();
            assertTrue(new Time(val).equals(new Time(val)));
            assertFalse(new Time(val).equals(new Time(val + 1)));
        }
    }
}