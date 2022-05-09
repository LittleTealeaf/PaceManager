package test.org.tealeaf.pacemanager.database.dataobjects;

import org.junit.jupiter.api.io.TempDir;
import org.tealeaf.pacemanager.database.dataobjects.Division;
import org.tealeaf.pacemanager.database.dataobjects.Pace;
import org.tealeaf.pacemanager.database.dataobjects.Team;
import org.tealeaf.pacemanager.events.EventCoordinator;
import org.tealeaf.pacemanager.events.EventManager;
import org.tealeaf.pacemanager.system.GsonWrapper;
import org.tealeaf.pacemanager.threads.ThreadManager;
import test.org.tealeaf.pacemanager.RandomValues;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface RandomDataObjects extends RandomValues {

    default Division randomDivision() {
        Division division = new Division();
        division.setName(randomName());
        return division;
    }

    default Pace randomPace(EventCoordinator eventCoordinator) {
        Pace pace = new Pace(eventCoordinator);

        IntStream.iterate(0,i -> i + 1).limit(RANDOM.nextInt(20,200)).parallel().mapToObj(RandomDataObjects.this::randomTeam).forEach(pace::addTeam);
        Stream.generate(RandomDataObjects.this::randomDivision).limit(RANDOM.nextInt(1,20)).parallel().forEach(pace::addDivision);
        pace.getTeams().parallelStream().forEach(team -> team.setDivision(randomItem(pace.getDivisions())));
        return pace;
    }

    default File randomPaceFile(Path tempDir) throws IOException {
        File file = tempDir.resolve("a.json").toFile();
        GsonWrapper.write(randomPace(new EventManager()),file);
        return file;
    }

    default Team randomTeam() {
        return randomTeam(RANDOM.nextInt(0,10000000));
    }

    default Team randomTeam(int number) {
        Team team = new Team();
        team.setTeamNumber("#%d".formatted(number));
        Stream.generate(() -> randomName() + " " + randomName()).limit(RANDOM.nextInt(1, 10)).forEach(team::addRider);
        return team;
    }
}
