package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializer {

    public static Gson createGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        builder.registerTypeAdapterFactory(new GsonTypeAdapterFactory());
        return builder.create();
    }
}
