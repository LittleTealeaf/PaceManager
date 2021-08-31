package data;

import com.google.gson.*;

import java.lang.reflect.Type;

public class Time {

    private final long time;
    private final boolean worldTime;

    public Time() {
        time = 0;
        worldTime = false;
    }

    public Time(long time) {
        worldTime = false;
        this.time = time;
    }

    public Time(boolean worldTime) {
        time = 0;
        this.worldTime = worldTime;
    }

    public Time(long time, boolean worldTime) {
        this.time = time;
        this.worldTime = worldTime;
    }

    public static Time difference(Time start, Time end) {
        return new Time(end.getTime() - start.getTime(), false);
    }

    /**
     * Wraps negative time values to positive
     *
     * @return New time object where the value is a positive integer. Preserves {@code worldTime} value
     */
    public Time absolute() {
        return new Time(Math.abs(time), worldTime);
    }

    public long getTime() {
        return time;
    }

    public boolean isWorldTime() {
        return worldTime;
    }

    public int compareTo(Time other) {
        return Long.valueOf(getTime()).compareTo(other.getTime());
    }

    /**
     * @author Thomas Kwashnak
     * @version 1.0.0
     * @see Serializer
     * Provides custom serialization instructions for {@link Gson}
     * @since 1.0.0
     */
    public static class TimeSerializer implements JsonSerializer<Time> {
        public JsonElement serialize(Time time, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(time.getTime());
        }
    }

    /**
     * @author Thomas Kwashnak
     * @version 1.0.0
     * @see Serializer
     * Provides custom deserialization instructions for {@link Gson}
     * @since 1.0.0
     */
    public static class TimeDeserializer implements JsonDeserializer<Time> {
        public Time deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return new Time(jsonElement.getAsLong());
        }
    }

}
