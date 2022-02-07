package api.database;

/**
 * Custom representation of Times within the pace. A "Time", or a "Clock", is the current time in the day that instances occur. The time will range
 * between 00:00:00 (Midnight), and 23:59:59 (1 second before midnight).
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
public interface IClock {

    /**
     * The current time, in seconds since 00:00:00 (Midnight)
     * @return The time represented by this clock
     * @since 2.0.0
     */
    int getTime();

    /**
     * Converts the Clock time into a human-readable format
     * @return String representation of the clock time
     * @since 2.0.0
     */
    String asString();

    /**
     * Calculates the addition of two times together
     * @param other Clock time to add to this clock time
     * @return A new clock instance with a value of the sum of this clock and the provided clock
     * @since 2.0.0
     */
    IClock getAdd(IClock other);
    /**
     * Calculates the subtraction of two times together
     * @param other Clock time to subtract to this clock time
     * @return A new clock instance with a value of the difference of this clock and the provided clock
     * @since 2.0.0
     */
    IClock getSubtract(IClock other);

    /**
     * Converts this clock into its negative counterpart
     * @return A clock instance with the negative value of this clock
     * @since 2.0.0
     */
    IClock getNegative();
}
