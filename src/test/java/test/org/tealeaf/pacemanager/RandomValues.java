package test.org.tealeaf.pacemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public interface RandomValues {

    Random RANDOM = new Random();
    String[] NAMES = loadNames();

    default String randomName() {
        return new String(NAMES[RANDOM.nextInt(NAMES.length)].toCharArray());
    }

    /**
     * Loads the names list into a string array
     * @return Array of names
     */
    static String[] loadNames() {
        InputStream stream = Objects.requireNonNull(RandomValues.class.getResourceAsStream("/names.txt"));
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        List<String> names_list = reader.lines().toList();
        try {
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return names_list.toArray(new String[0]);
    }
}
