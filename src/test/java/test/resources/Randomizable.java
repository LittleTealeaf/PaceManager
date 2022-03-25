package test.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Provides a static random object to use during testing
 */
public interface Randomizable {

    /**
     * An instance of the {@link Random} object
     */
    Random RANDOM = new Random();

    String[] NAMES = loadNames();


    /**
     * Loads the names list into a string array
     * @return Array of names
     */
    static String[] loadNames() {
        InputStream stream = Objects.requireNonNull(Randomizable.class.getResourceAsStream("/names.txt"));
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        List<String> names_list = reader.lines().collect(Collectors.toList());
        try {
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return names_list.toArray(new String[0]);
    }

    /**
     * Provides a randomized name from the list of names
     *
     * @return A random single name
     */
    default String randomName() {
        return new String(NAMES[RANDOM.nextInt(NAMES.length)].toCharArray());
    }
}
