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
     * Stores the created {@link Gson} object
     *
     * @see #createGson()
     * @see #getGson()
     */
    private static Gson builder;

    /**
     * Grabs the project's {@link Gson} object. Creates a new one using {@link #createGson()} if one has not been already generated. Stores the newly created {@code Gson} object in {@link #builder}
     *
     * @return Modified {@link Gson} object. Modifications listed under {@code #createGson()}
     * @see #createGson()
     * @see Gson
     */
    public static Gson getGson() {
        return builder != null ? builder : (builder = createGson());
    }

    /**
     * Creates a {@link Gson} object with modifications required
     * <br>
     * Modifications include:
     * <ul>
     *     <li>Excluding {@code transient} values
     *     </li>
     *     <li>Custom {@link Time} Serialization / Deserialization</li>
     * </ul>
     *
     * @return Completed {@link Gson} object for use in the project
     * @see data.Time.TimeSerializer
     * @see data.Time.TimeDeserializer
     * @see Gson
     * @see GsonBuilder
     */
    private static Gson createGson() {
        return new GsonBuilder().excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT)
                                .registerTypeAdapter(Time.class, new Time.TimeSerializer())
                                .registerTypeAdapter(Time.class, new Time.TimeDeserializer()).create();
    }

    /**
     * Provides the working directory for Application Files
     *
     * @return String file path of the application working directory
     * @apiNote Does not point to the {@code %AppData%} folder on windows directories. This is due to the
     * {@code AppDirs}
     * dependency not working in its current or previous versions in this project
     */
    public static String getWorkingDirectory() {
        return System.getProperty("user.home") + File.separatorChar + ".paceManager" + File.separatorChar;
    }
}
