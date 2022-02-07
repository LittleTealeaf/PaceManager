package api.database;

/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
public interface IClock {

    /**
     * The current time, in seconds since 00:00:00 (Midnight)
     * @return The time represented by this clock
     */
    int getTime();

    /**
     * Converts the Clock time into a human-readable format
     * @return String representation of the clock time
     */
    String asString();

    /**
     * Calculates the addition of two times together
     * @param other Clock time to add to this clock time
     * @return A new clock instance with a value of the sum of this clock and the provided clock
     */
    IClock getAdd(IClock other);
    /**
     * Calculates the subtraction of two times together
     * @param other Clock time to subtract to this clock time
     * @return A new clock instance with a value of the difference of this clock and the provided clock
     */
    IClock getSubtract(IClock other);

    /**
     * Converts this clock into its negative counterpart
     * @return A clock instance with the negative value of this clock
     */
    IClock getNegative();
}
