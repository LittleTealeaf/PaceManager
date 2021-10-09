package test;

import data.Time;

public class RandGen {
    /**
     * Creates a random Time object using {@link #randomTimeValue()}
     *
     * @return A new {@code Time} object with a random value as stated in {@link #randomTimeValue()}
     */
    public static Time randomTime() {
        return new Time((long) (86400000L * Math.random()));
    }

    /**
     * Generates a random time value between 00:00 - 23:59
     *
     * @return A value within the bounds {@code [0,86400000)}
     * @see Time
     */
    public static long randomTimeValue() {
        return (long) (86400000L * Math.random());
    }
}
