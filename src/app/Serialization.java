package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Time;

import java.io.File;

/**
 * Contains functions related to Serializing / Deserializing data and file management/storage
 * <ul><li>
 *     {@link #getGson()} - Provides a {@link Gson} object for serializing and deserializing data
 * </li><li>
 *     {@link #getWorkingDirectory()} - Provides the path of the working directory for configuration and additional files
 * </li></ul>
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Serialization {
    /**
     * Stores the generated {@code Gson} object used for serialization and deserialization of objects.
     * This value is generated at application launch using the {@link #createGson()} method.
     * @see #createGson()
     * @see #getGson()
     * @see Gson
     */
    private static final Gson gson = createGson();

    /**
     * Provides the {@code Gson} object for serialization and deserialization.
     * @see #gson
     * @see #createGson()
     * @return Gson object, specified in {@link #createGson()}
     * @since 1.0.0
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
     * @see Gson
     * @see GsonBuilder
     * @since 1.0.0
     */
    private static Gson createGson() {
        return new GsonBuilder().excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT)
                                .registerTypeAdapter(Time.class, new Time.TimeSerializer())
                                .registerTypeAdapter(Time.class, new Time.TimeDeserializer()).create();
    }

    /**
     * Provides a working directory for use of data, settings, or other files within the project.
     * <p>Uses {@link System#getProperty(String) System.getProperty("user.home")} to obtain the base directory, and then includes a
     * sub directory for paceManager. This folder is set as hidden on all systems except windows by naming the directory ".paceManager".
     * <p>
     * Future versions may move this folder to {@code %APPDATA%} on windows machines, or similarly change the location on other
     * platforms as well.
     *
     * @return String directory path of the working directory, such as {@code C:\Users\Tealeaf\.paceManager\}
     * @see File#separatorChar
     * @since 1.0.0
     * @version 1.0.0
     */
    //TODO perhaps move this to a java file like "System Resources"
    public static String getWorkingDirectory() {
        return System.getProperty("user.home") + File.separatorChar + ".paceManager" + File.separatorChar;
    }
}
