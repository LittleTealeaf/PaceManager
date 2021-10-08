package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    private static final int NUM_TRIALS = 1024;

    private Time[] generateRandomSet() {
        Time[] times = new Time[NUM_TRIALS];
        for(int i = 0; i < times.length; i++) {
            times[i] = new Time((long) (100000000L * Math.random()));
        }
        return times;
    }

    @Test
    public void timeFromMS() {
        Time[] set = generateRandomSet();
        for(int i = 0; i < set.length; i++) {
            Time time = new Time(set[i].getValue());
            assertEquals(time.getValue(),set[i].getValue());
        }
    }

    @Test
    public void timeFromString() {
        Time[] set = generateRandomSet();
        for(int i = 0; i < set.length; i++) {
            String string = set[i].toString();
            Time time = new Time(string);
            assertEquals(new Time(time.toString()).getValue(),time.getValue());
        }
    }

    @Test
    public void difference() {

    }

    @Test
    public void absolute() {
    }

    @Test
    public void getValue() {
    }

    @Test
    public void add() {
    }

    @Test
    public void subtract() {
    }

    @Test
    public void compareTo() {
    }

    @Test
    public void testToString() {
    }
}