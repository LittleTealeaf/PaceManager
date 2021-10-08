package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    private static final int NUM_TRIALS = 1024;

    public static Time[] generateRandomSet() {
        Time[] times = new Time[NUM_TRIALS];
        for(int i = 0; i < times.length; i++) {
            times[i] = randomTime();
        }
        return times;
    }

    public static Time randomTime() {
        return new Time((long) (86400000L * Math.random()));
    }

    public static long randomTimeValue() {
        return (long) (86400000L * Math.random());
    }

    @Test
    public void timeFromMS() {
        Time[] set = generateRandomSet();
        for (Time value : set) {
            Time time = new Time(value.getValue());
            assertEquals(time.getValue(), value.getValue());
        }
    }

    @Test
    public void timeFromString() {
        Time[] set = generateRandomSet();
        for (Time value : set) {
            String string = value.toString();
            Time time = new Time(string);
            assertEquals(new Time(time.toString()).getValue(), time.getValue());
        }
    }

    @Test
    public void difference() {
        assertNull(Time.difference(null,null));
        assertNull(Time.difference(randomTime(),null));
        assertNull(Time.difference(null,randomTime()));
        for(int i = 0; i < NUM_TRIALS; i++) {
            long a = randomTimeValue(), b = randomTimeValue();
            long difference = b - a;
            Time timeA = new Time(a), timeB = new Time(b);
            assertEquals(Time.difference(timeA,timeB).getValue(),difference);
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
            Time timeA = new Time(a), timeB = new Time(b);
            assertEquals(timeA.add(timeB).getValue(),sum);
        }
    }

    @Test
    public void subtract() {
        for(int i = 0; i < NUM_TRIALS; i++) {
            long a = randomTimeValue(), b = randomTimeValue();
            long difference = a - b;
            Time timeA = new Time(a), timeB = new Time(b);
            assertEquals(timeA.subtract(timeB).getValue(),difference);
        }
    }

    @Test
    public void compareTo() {
    }

    @Test
    public void testToString() {
    }
}