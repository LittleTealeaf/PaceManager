package data;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Stores the value of a given Time
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Time {

    /**
     * Time stored as number of ms
     *
     * @see #Time()
     * @see #Time(long)
     * @see #getTime()
     */
    private final long time;

    /**
     * Creates a Zero-Time object
     * <ul><li>{@code time = 0}</li></ul>
     */
    public Time() {
        time = 0;
    }

    /**
     * Creates a Time object with a given time
     * @param time Time in ms
     */
    public Time(long time) {
        this.time = time;
    }

    /**
     * Calculates the difference between two {@code Time} objects
     * @param start Starting Time
     * @param end Ending Time
     * @return Elapsed Time as a {@code Time} object
     */
    //TODO make this non-static?
    public static Time difference(Time start, Time end) {
        return new Time(end.getTime() - start.getTime());
    }

    /**
     * Wraps negative time values to positive
     *
     * @return Returns a Time object where the {@code time} value will always be positive
     */
    public Time absolute() {
        return new Time(Math.abs(time));
    }

    /**
     * Returns the time
     * @return Time in ms
     */
    public long getTime() {
        return time;
    }

    /**
     * Compares the time to another {@code Time} object's time
     * @param other {@code Time} object to compare to
     * @return the value 0 if this Time is equal to the other Time; a value less than 0 if this Time is numerically less than
    the other Time; and a value greater than 0 if this Time is numerically greater than the other Time (signed comparison).
     @see Long#compareTo(Long)
     */
    public int compareTo(Time other) {
        return Long.valueOf(getTime()).compareTo(other.getTime());
    }

    /**
     * Provides the Serialization instructions of a {@link Time} object, which only needs to be stored as a {@code long} instead
     * of a whole object
     * @author Thomas Kwashnak
     * @version 1.0.0
     * @since 1.0.0
     */
    public static class TimeSerializer implements JsonSerializer<Time> {
        public JsonElement serialize(Time time, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(time.getTime());
        }
    }

    /**
     * Provides the deserialization instructions of a {@link Time} object, which only needs to be stored as a
     * {@code long} instead of a whole object
     *
     * @author Thomas Kwashnak
     * @version 1.0.0
     * @since 1.0.0
     */
    public static class TimeDeserializer implements JsonDeserializer<Time> {
        public Time deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return new Time(jsonElement.getAsLong());
        }
    }

}
