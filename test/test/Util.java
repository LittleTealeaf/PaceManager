package test;

import app.Resources;
import app.Serialization;
import com.google.gson.stream.JsonReader;
import data.Time;

import java.io.File;
import java.io.InputStreamReader;

public class Util {

    private static String[] names;
    private static File testFile;

    /**
     * Creates a random Time object using {@link #randomTimeValue()}
     *
     * @return A new {@code Time} object with a random value as stated in {@link #randomTimeValue()}
     */
    public static Time randomTime() {
        return new Time((long) (86400000L * Math.random()));
    }

    /**
     * Generates a random time value between 00:00 - 23:59
     *
     * @return A value within the bounds {@code [0,86400000)}
     * @see Time
     */
    public static long randomTimeValue() {
        return (long) (86400000L * Math.random());
    }

    private static void loadNames() {
        names = Serialization.getGson().fromJson(new JsonReader(new InputStreamReader(Resources.getResource("/names.json"))),String[].class);
    }

    public static String randomName() {
        if(names == null) {
            loadNames();
        }
        return names[(int) (Math.random() * names.length)];
    }

    public static String randomFullName() {
        return randomName() + " " + randomName();
    }
}