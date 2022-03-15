package data.api;

/**
 * Represents a measurement in time, displayed in seconds, minutes, and hours. This is created because we have no need of the added complexities
 * with the build in java time classes.
 *
 * @author Thomas Kwashnak
 * @since 2.0.0
 */
public interface IClock {

    /**
     * Returns the current time represented in the Clock
     *
     * @return The current time, in seconds
     */
    int getTime();

    /**
     * Sets the represented time in the clock
     *
     * @param time The new time to represent, in seconds.
     */
    void setTime(int time);

    /**
     * Adds another clock's time to this clock's value, but returns a new instance without modifying any clock's time value
     *
     * @param other The other clock to add to this clock
     *
     * @return A new IClock instance with a value equal to this clock's value plus the provided clock's value
     */
    IClock getAdd(IClock other);

    /**
     * Adds time to this clock's value, but returns a new instance without modifying any clock's time value
     *
     * @param time The number of seconds to add to this clock's value
     *
     * @return A new IClock instance with a value equal to this clock's value plus the provided time in seconds
     */
    IClock getAdd(int time);

    /**
     * Subtracts another clock's value from this clock's value, but returns a new instance without modifying either clock's value
     *
     * @param other The other clock to remove from this clock
     *
     * @return A new IClock instance with a value equal to this clock's value minus the provided clock's value
     */
    IClock getSubtract(IClock other);

    /**
     * Subtracts time to this clock's value, but returns a new instance without modifying any clock's time value
     *
     * @param time The number of seconds to subtract from this clock's value
     *
     * @return A new IClock instance with a value equal to this clock's value minus the provided time
     */
    IClock getSubtract(int time);

    /**
     * Gets the absolute value of this clock, but returns a new instance without modifying this clock's time
     *
     * @return A new IClock instance with a value equal to the absolute value of this clock's time
     */
    IClock getAbs();

    /**
     * Returns the absolute difference between this clock's time and another clock's time.
     *
     * @param other The other clock to measure the distance between
     *
     * @return A new IClock instance with a value equal to the absolute difference between this clock and the provided clock
     */
    IClock getElapsed(IClock other);

    /**
     * Adds another clock's value to this value
     *
     * @param other The other clock to add to this value
     *
     * @return A reference to this IClock instance
     */
    IClock add(IClock other);

    /**
     * Adds time to this clock's value
     *
     * @param time The time, in seconds, to add to this clock
     *
     * @return A reference to this IClock instance
     */
    IClock add(int time);

    /**
     * Subtracts another clock's value from this value
     *
     * @param other The other clock to subtract this value by
     *
     * @return A reference to this IClock instance
     */
    IClock subtract(IClock other);

    /**
     * Subtracts time from this clock
     *
     * @param time The time, in seconds, to subtract this clock by
     *
     * @return A reference to this IClock instance
     */
    IClock subtract(int time);

    /**
     * Calls the {@link Math#abs(int)} method on the time, essentially making it positive if it was negative
     *
     * @return A reference to this IClock instance
     */
    IClock abs();

    /**
     * Compiles the time into a human-readable format
     *
     * @return The human-readable string representing this time
     */
    String asString();
}
