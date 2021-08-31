package data;

import com.google.gson.*;

import java.lang.reflect.Type;

public class Time {

    private final long time;

    public Time() {
        time = 0;
    }

    public Time(long time) {
        this.time = time;
    }

    public static Time difference(Time start, Time end) {
        return new Time(end.getTime() - start.getTime());
    }

    public long getTime() {
        return time;
    }

    public static class TimeSerializer implements JsonSerializer<Time> {
        public JsonElement serialize(Time time, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(time.getTime());
        }
    }

    public static class TimeDeserializer implements JsonDeserializer<Time> {
        public Time deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return new Time(jsonElement.getAsLong());
        }
    }

}
