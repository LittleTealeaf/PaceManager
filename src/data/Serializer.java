package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializer {
    private static Gson builder;

    public static Gson getGson() {
        return builder != null ? builder : (builder = createGson());
    }

    private static Gson createGson() {
        return new GsonBuilder().excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT)
                                .registerTypeAdapter(Time.class, new Time.TimeSerializer())
                                .registerTypeAdapter(Time.class, new Time.TimeDeserializer()).create();
    }
}
