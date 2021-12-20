package testutil;

import app.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TestUtilRandom {

    private static final Random random;
    private static final String[] names;

    static {
        random = new Random();

        List<String> names_list;
        try (InputStreamReader streamReader = new InputStreamReader(Resources.getResource("/names.txt"), StandardCharsets.UTF_8)) {
            names_list = new BufferedReader(streamReader).lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            names_list = Arrays.asList("");
        }
        names = names_list.toArray(new String[0]);
    }

    public static String randomName() {
        return names[randomInt(0, names.length)];
    }

    public static int randomInt(int low, int high) {
        return random.nextInt(low, high);
    }
}
