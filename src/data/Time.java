package data;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//TODO update javadocs
/**
 * Stores the value of a given time. Value is stored as a long, specifying the time in ms.
 * <p> Uses of stored time includes specifying a specific time and date, specifying a specific time of day, or specifying a difference in time
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Time {
    /**
     * @since 1.0.0
     */
    private static final long MILLISECONDS_PER_HOUR = 3600000;
    /**
     * Value of Time stored as ms. The only way to specify this value is by creating a new Time using either
     * {@link #Time()} (sets {@code value = 0}) or {@link #Time(long)} (allows for specification of {@code value})
     *
     * @see #getValue()
     * @since 1.0.0
     */
    private final long value;

    /**
     * Creates a new {@code Time} object with {@code value} set to {@code 0}
     *
     * @see #Time(long)
     * @since 1.0.0
     */
    public Time() {
        value = 0;
    }

    /**
     * Creates a new {@code Time} object with the specified {@code value}
     *
     * @param value Time in number of ms
     * @see #Time()
     * @see #value
     * @since 1.0.0
     */
    public Time(long value) {
        this.value = value;
    }

    public Time(String string) {
        long value = 0;
        String cleanedString = string.replace(" ", "");
        SimpleDateFormat[] validFormats = new SimpleDateFormat[]{
                new SimpleDateFormat("HH:mm:ss"),
                new SimpleDateFormat("HH:mm")
        };
        for (SimpleDateFormat format : validFormats) {
            try {
                value = format.parse(cleanedString).getTime();
                break;
            } catch (Exception ignored) {}
        }

        if (string.toUpperCase().contains("PM") && value < 13 * MILLISECONDS_PER_HOUR) {
            value += 12 * MILLISECONDS_PER_HOUR;
        } else if(string.toUpperCase().contains("AM") && value > 12 * MILLISECONDS_PER_HOUR) {
            value -= 24 * MILLISECONDS_PER_HOUR;
        }

        this.value = value;
    }

    /**
     * Calculates the difference between two {@code Time} objects.<p>
     * Will result in a {@code Time} object with negative values if the value of {@code end} is greater
     * than the value of {@code start}
     *
     * @param start the first {@code Time}, typically less in value than {@code end}
     * @param end   the second {@code Time}, typically greater in value than {@code start}
     * @return A new {@code Time} object representing the difference between {@code start} and {@code end}
     * @see #value
     * @see #absolute()
     * @since 1.0.0
     */
    public static Time difference(Time start, Time end) {
        return new Time(end.getValue() - start.getValue());
    }

    /**
     * Returns a {@code Time} object with the positive value of {@code value}.
     * <p>For example, if {@code value = -100}, then will return a {@code Time} object with {@code value = 100}.
     * Similarly, if {@code value = 100}, then will also return a {@code Time} object with {@code value = 100}.
     *
     * @return A new {@code Time} object with a positive {@code value}
     * @since 1.0.0
     */
    public Time absolute() {
        return new Time(Math.abs(value));
    }

    /**
     * @since 1.0.0
     */
    public long getValue() {
        return value;
    }

    /**
     * @param other
     * @return
     * @since 1.0.0
     */
    public Time add(Time other) {
        return new Time(getValue() + other.getValue());
    }

    /**
     * @param other
     * @return
     * @since 1.0.0
     */
    public Time subtract(Time other) {
        return new Time(getValue() - other.getValue());
    }

    /**
     * Compares the time to another {@code Time} object's time
     *
     * @param other {@code Time} object to compare to
     * @return the value 0 if this Time is equal to the other Time; a value less than 0 if this Time is numerically less than
     * the other Time; and a value greater than 0 if this Time is numerically greater than the other Time (signed comparison).
     */
    public int compareTo(Time other) {
        return Long.compare(getValue(), other.getValue());
    }

    public String toString() {
        Date date = new Date(value);
        DateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
        dateFormatter.setTimeZone(TimeZone.getDefault());
        return dateFormatter.format(date);
    }

    /**
     * Provides the Serialization instructions of a {@link Time} object, which only needs to be stored as a {@code long} instead
     * of a whole object
     *
     * @author Thomas Kwashnak
     * @version 1.0.0
     * @since 1.0.0
     */
    public static class TimeSerializer implements JsonSerializer<Time> {
        public JsonElement serialize(Time time, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(time.getValue());
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
