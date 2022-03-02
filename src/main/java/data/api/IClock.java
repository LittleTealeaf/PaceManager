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
     * @param time time value, in seconds
     */
    void setTime(int time);

    /**
     * Provides a human-readable form of the time
     * @return Time represented as a String object
     */
    String asString();
}
