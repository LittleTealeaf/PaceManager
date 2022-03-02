package test.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Test Utilities that randomize a test in some way
 */
public class RandomUtils implements RandomUtil {

    /**
     * List of dummy names used for randomizing names
     */
    private static final String[] NAMES;

    static {
        List<String> names_list;
        InputStream stream = Objects.requireNonNull(RandomUtils.class.getResourceAsStream("/names.txt"));
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        names_list = reader.lines().collect(Collectors.toList());
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NAMES = names_list.toArray(new String[0]);
    }

    /**
     * Provides a randomized name from the list of names
     *
     * @return A random single name
     */
    public static String randomName() {
        return new String(NAMES[RANDOM.nextInt(NAMES.length)].toCharArray());
    }
}
