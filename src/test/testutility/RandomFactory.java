package testutility;

import app.Resources;
import data.Rider;
import data.Time;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomFactory {

    private static final Random random;
    private static final String[] names;

    static {
        random = new Random();

        List<String> names_list;
        try (InputStreamReader streamReader = new InputStreamReader(Resources.getResource("/names.txt"), StandardCharsets.UTF_8)) {
            names_list = new BufferedReader(streamReader).lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            names_list = List.of("");
        }
        names = names_list.toArray(new String[0]);
    }

    public static Rider randomRider() {
        return new Rider(RandomFactory.randomName(), RandomFactory.randomName());
    }

    public static String randomName() {
        return names[randomInt(0, names.length)];
    }

    public static int randomInt(int low, int high) {
        return random.nextInt(low, high);
    }

    public static int randomInt() {
        return randomInt(Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    public static int randomInt(int high) {
        return randomInt(0,high);
    }

    public static long randomLong() {
        return randomLong(Long.MIN_VALUE,Long.MAX_VALUE);
    }

    public static long randomLong(long low, long high) {
        return random.nextLong(low,high);
    }

    public static long randomLong(long high) {
        return randomLong(0,high);
    }

    @Contract(" -> new")
    public static @NotNull Time randomTime() {
        return randomTime(Long.MAX_VALUE);
    }

    @Contract("_ -> new")
    public static @NotNull Time randomTime(long high) {
        return randomTime(0,high);
    }

    @Contract("_, _ -> new")
    public static @NotNull Time randomTime(long low, long high) {
        return new Time(random.nextLong(low,high));
    }



}
