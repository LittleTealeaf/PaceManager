package data.api;

/**
 * A Clock interface that stores the number of seconds since 00:00, or midnight.
 * @author Thomas Kwashnak
 * @version 2.0.0
 */
public interface IClock {

    /**
     * Returns the clock time stored
     * @return Clock time, in seconds
     */
    int getTime();

    /**
     * Sets the clock time
     *
     * @param time time value, in seconds
     */
    void setTime(int time);

    /**
     * Adds the two clock object values together
     *
     * @param other The other clock value to add
     *
     * @return A new IClock instance that has a value equal to the sum of the two IClock's values
     */
    IClock add(IClock other);

    /**
     * Subtracts two clock objects from each other
     *
     * @param other The clock value to subtract
     *
     * @return A new IClock instance that takes the value of this object and subtracts the value of the other IClock
     */
    IClock subtract(IClock other);

    /**
     * Finds the absolute difference between two clock values
     *
     * @param other THe clock value to compare against
     *
     * @return A new IClock instance that contains the value of the absolute difference between this and the other IClock
     */
    IClock difference(IClock other);

    /**
     * Provides a human-readable form of the time
     *
     * @return Time represented as a String object
     */
    String asString();
}
