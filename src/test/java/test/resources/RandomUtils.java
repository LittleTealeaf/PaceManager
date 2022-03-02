package test.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomUtils {

    private static final String[] NAMES;
    private static final Random RANDOM;

    static {
        RANDOM = new Random();

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

    public static String randomNameCopy() {
        return new String(randomName().toCharArray());
    }

    public static String randomName() {
        return NAMES[RANDOM.nextInt(NAMES.length)];
    }
}
