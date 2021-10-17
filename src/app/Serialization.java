package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Time;

/**
 * Contains functions related to Serializing / Deserializing data
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Serialization {

    /**
     * Stores the generated {@code Gson} object used for serialization and deserialization of objects.
     * This value is generated at application launch using the {@link #createGson()} method.
     *
     * @see #createGson()
     * @see #getGson()
     */
    private static final Gson gson = createGson();

    /**
     * Provides the {@code Gson} object for serialization and deserialization.
     *
     * @return Gson object, specified in {@link #createGson()}
     *
     * @see #gson
     */
    public static Gson getGson() {
        return gson;
    }

    /**
     * Creates a {@link Gson} object with modifications required for use in the project.
     * <p>
     * Modifications:
     * <ul>
     *     <li>Excluding {@code transient} values. This allows for specification of values that should not be serialized,
     *     either to save space or to prevent excess connections.
     *     </li>
     *     <li>Use of custom Serializers and Deserializers:
     *     <ul><li>{@link Time} class ({@link data.Time.TimeSerializer Serializer}, {@link data.Time.TimeDeserializer Deserializer})</li></ul>
     *     </li>
     * </ul>
     *
     * @return Completed {@link Gson} object for use in the project
     *
     * @see #gson
     */
    private static Gson createGson() {
        //TODO remove pretty printing before release
        return new GsonBuilder().excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT).registerTypeAdapter(Time.class, new Time.TimeSerializer()).registerTypeAdapter(
                Time.class, new Time.TimeDeserializer()).setPrettyPrinting().create();
    }

}
