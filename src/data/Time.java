package data;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

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
     * Number of milliseconds in a second
     */
    private static final long MILLISECONDS_PER_SECOND = 1000;
    /**
     * Number of milliseconds in a minute
     */
    private static final long MILLISECONDS_PER_MINUTE = 60 * MILLISECONDS_PER_SECOND;
    /**
     * Number of milliseconds in an hour
     */
    private static final long MILLISECONDS_PER_HOUR = 60 * MILLISECONDS_PER_MINUTE;
    /**
     * Number of milliseconds in a day
     */
    private static final long MILLISECONDS_PER_DAY = 24 * MILLISECONDS_PER_HOUR;

    /**
     * Value of Time stored as ms. The only way to specify this value is by creating a new Time using either
     * {@link #Time()} (sets {@code value = 0}) or {@link #Time(long)} (allows for specification of {@code value})
     *
     * @see #getValue()
     */
    private final long value;

    /**
     * Generated string representation of the value. Is generated upon the first call of {@link #toString()} and is
     * referenced every time after
     */
    private String string;

    /**
     * Creates a new {@code Time} object with {@code value} set to {@code 0}
     *
     * @see #Time(long)
     */
    public Time() {
        value = 0;
    }

    /**
     * Creates a new {@code Time} object with the specified {@code value}
     *
     * @param value Time as number of milliseconds
     *
     * @see #Time()
     * @see #value
     */
    public Time(long value) {
        this.value = value;
    }

    public Time(String string) {
        //TODO optimize this using character pointer and an intermediate value that gets increased in potency
        long value = 0;
        String cleanedString = string.replace(" ", "");

        if (cleanedString.contains("PM")) {
            value += MILLISECONDS_PER_HOUR * 12;
        }
        String[] intVals = cleanedString.split(":");
        value += MILLISECONDS_PER_HOUR * Integer.parseInt(intVals[0]);
        value += MILLISECONDS_PER_MINUTE * Integer.parseInt(intVals[1]);
        if (intVals.length > 2) {
            value += MILLISECONDS_PER_SECOND * Integer.parseInt(intVals[2]);
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
     *
     * @return A new {@code Time} object representing the difference between {@code start} and {@code end}. If either
     * or both start and end are null, returns null
     *
     * @see #value
     * @see #absolute()
     */
    public static Time difference(Time start, Time end) {
        return start == null || end == null ? null : new Time(end.getValue() - start.getValue());
    }

    /**
     *
     */
    public long getValue() {
        return value;
    }

    /**
     * Returns a {@code Time} object with the positive value of {@code value}.
     * <p>For example, if {@code value = -100}, then will return a {@code Time} object with {@code value = 100}.
     * Similarly, if {@code value = 100}, then will also return a {@code Time} object with {@code value = 100}.
     *
     * @return A new {@code Time} object with a positive {@code value}
     */
    public Time absolute() {
        return new Time(Math.abs(value));
    }

    /**
     * @param other Time to add
     *
     * @return Sum of this time and the other time's values
     */
    public Time add(Time other) {
        return new Time(getValue() + other.getValue());
    }

    /**
     * @param other Time to subtract
     *
     * @return Difference of this time and other time's values
     */
    public Time subtract(Time other) {
        return other != null ? new Time(getValue() - other.getValue()) : null;
    }

    /**
     * Compares the time to another {@code Time} object's time
     *
     * @param other {@code Time} object to compare to
     *
     * @return the value 0 if this Time is equal to the other Time; a value less than 0 if this Time is numerically less than
     * the other Time; and a value greater than 0 if this Time is numerically greater than the other Time (signed comparison).
     */
    public int compareTo(Time other) {
        return Long.compare(getValue(), other.getValue());
    }

    public boolean equals(Object other) {
        return other instanceof Time && ((Time) other).getValue() == this.getValue();
    }

    public String toString() {
        if (string != null) {
            return string;
        } else {
            boolean negative = value < 0;
            long val = value;
            if (negative) {
                val *= -1;
            }
            while (val >= MILLISECONDS_PER_DAY) {
                val -= MILLISECONDS_PER_DAY;
            }
            int h = 0, m = 0, s = 0;
            while (val >= MILLISECONDS_PER_HOUR) {
                h++;
                val -= MILLISECONDS_PER_HOUR;
            }
            while (val >= MILLISECONDS_PER_MINUTE) {
                m++;
                val -= MILLISECONDS_PER_MINUTE;
            }
            while (val >= MILLISECONDS_PER_SECOND) {
                s++;
                val -= MILLISECONDS_PER_SECOND;
            }

            DecimalFormat formatter = new DecimalFormat("00");
            return string = (negative ? "-" : "") + formatter.format(h) + ":" + formatter.format(m) + ":" + formatter.format(s);
        }
    }

    /**
     * Provides the Serialization instructions of a {@link Time} object, which only needs to be stored as a {@code long} instead
     * of a whole object
     *
     * @author Thomas Kwashnak
     * @version 1.0.0
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
     */
    public static class TimeDeserializer implements JsonDeserializer<Time> {

        public Time deserialize(
                JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext
                               ) throws JsonParseException {
            return new Time(jsonElement.getAsLong());
        }
    }
}
