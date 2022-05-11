package test;

import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.data.EventTime;
import org.tealeaf.pacemanager.data.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public interface Randomizer {

    Random RANDOM = new Random();
    String[] NAMES = loadNames();

    /**
     * Loads the names list into a string array
     *
     * @return Array of names
     */
    static String[] loadNames() {
        InputStream stream = Objects.requireNonNull(Randomizer.class.getResourceAsStream("/names.txt"));
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        List<String> names_list = reader.lines().toList();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names_list.toArray(new String[0]);
    }

    default Team randomTeam() {
        Team team = new Team();
        team.setName(randomName());
        Stream.generate(this::randomFullName).limit(RANDOM.nextInt(1,5)).forEach(team::addRider);
        if (RANDOM.nextBoolean()) {
            long time = Math.abs(RANDOM.nextLong() / 2);
            team.setStartTime(new EventTime(time));
            if (RANDOM.nextBoolean()) {
                team.setEndTime( new EventTime(RANDOM.nextLong(time, Long.MAX_VALUE)));
            }
        }
        return team;
    }

    default <T> T randomItem(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    default String randomFullName() {
        return randomName() + " " + randomName();
    }

    default String randomName() {
        return new String(NAMES[RANDOM.nextInt(NAMES.length)].toCharArray());
    }
}
