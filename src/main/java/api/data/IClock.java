package api.data;

/**
 * @author Thomas Kwashnak
 * @version 2.0.0
 */
public interface IClock {

    /**
     * Tag for the TIME variable
     */
    String KEY_TIME = IClock.class.getName() + ":TIME";

    /**
     * Returns the current time on the clock
     * @return Current time, in seconds.
     */
    int getTime();

    /**
     * Sets the current time to the provided variable
     * @param time The new time, in seconds, to set
     */
    void setTime(int time);

    /**
     * Provides a human-readable form of the time
     * @return Time represented as a String object
     */
    String asString();
}
