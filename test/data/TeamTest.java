package data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import serialization.Serializer;
import testutility.RandomFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TeamTest {

    /**
     * Tests the serialization of the {@link Rider} class
     *
     * @param tempDir Temporary Directory to serialize into
     *
     * @throws IOException            If there was an exception in serialization or deserialization.
     * @throws ClassNotFoundException If there was an exception in deserialization.
     */
    @Test
    public void serialization(@TempDir Path tempDir) throws IOException, ClassNotFoundException {
        File file = tempDir.resolve("team.ser").toFile();
        Serializer<Team> serializer = new Serializer<>();
        Team before = new Team();
        serializer.serialize(before, file);
        Team after = serializer.deserialize(file);
        Assertions.assertEquals(before, after);
    }

    @Test
    public void constructor() {
        Team team = new Team();
        assertEquals("",team.getName());
        assertEquals("",team.getNotes());
        assertEquals(new ArrayList<Rider>(), team.getRiders());
        assertFalse(team.isExcluded());
        assertNull(team.getDivision());
        assertNull(team.getStartTime());
        assertNull(team.getEndTime());
        assertNull(team.getElapsedTime());
    }

    @Test
    public void getDivision() {

        Team team = new Team();
        Division division = new Division();

        assertNull(team.getDivision());

        team.setDivision(division);
        assertEquals(division,team.getDivision());

        team.setDivision(division.asDivisionPointer());
        assertEquals(division,team.getDivision());
    }

    @Test
    public void lookupDivision() {
        Team team = new Team();

        Division targetDivision = new Division();
        DivisionPointer pointer = new DivisionPointer(targetDivision.getUUID());
        team.setDivision(pointer);

        List<Division> divisions = new ArrayList<>();


        team.lookupDivision(divisions);
        assertNull(team.getDivision());

        for(int i = 0; i < 10; i++) {
            divisions.add(new Division());
        }

        team.lookupDivision(divisions);
        assertNull(team.getDivision());

        divisions.add(targetDivision);
        team.lookupDivision(divisions);
        assertSame(targetDivision,team.getDivision());
    }

    @Test
    public void getElapsedTime() {
        Team team = new Team();
        assertNull(team.getElapsedTime());

        team.setStartTime(new Time());
        assertNull(team.getElapsedTime());

        team.setStartTime(null);
        team.setEndTime(new Time());
        assertNull(team.getElapsedTime());

        team.setStartTime(new Time());
        assertEquals(new Time(0),team.getElapsedTime());

        long start = RandomFactory.randomLong(0, Long.MAX_VALUE / 2);
        long end = RandomFactory.randomLong(start, Long.MAX_VALUE);

        team.setStartTime(new Time(start));
        team.setEndTime(new Time(end));

        assertEquals(new Time(end - start), team.getElapsedTime());
    }

    @Test
    public void startTime() {
        Team team = new Team();
        assertNull(team.getStartTime());

        Time time = RandomFactory.randomTime();
        team.setStartTime(time);
        assertSame(time,team.getStartTime());
        assertNull(team.getEndTime());
    }

    @Test
    public void endTime() {
        Team team = new Team();
        assertNull(team.getEndTime());

        Time time = RandomFactory.randomTime();
        team.setEndTime(time);
        assertSame(time,team.getEndTime());
        assertNull(team.getStartTime());
    }

    @Test
    public void notes() {
        Team team = new Team();
        assertEquals("",team.getNotes());

        team.setNotes(null);
        assertEquals("",team.getNotes());

        String test = "test";
        team.setNotes(test);
        assertEquals(test,team.getNotes());
    }

    @Test
    public void excluded() {
        Team team = new Team();
        assertFalse(team.isExcluded());

        team.setExcluded(true);
        assertTrue(team.isExcluded());
    }

    @Test
    public void name() {
        Team team = new Team();
        assertEquals("",team.getName());

        String testName = "123";
        team.setName(testName);
        assertEquals(testName,team.getName());
    }

    @Test
    public void equals() {
        Team a = new Team(), b = new Team();
        assertEquals(a,b);

        boolean isExcluded = false;
        Rider rider = RandomFactory.randomRider();
        Division division = new Division();
        String notes = "abcdefg";
        String name = "team abc";
        Time startTime = RandomFactory.randomTime(Long.MAX_VALUE / 2);
        Time endTime = RandomFactory.randomTime(startTime.getTime(),Long.MAX_VALUE);

        for(Team team : new Team[] {a,b}) {
            team.setExcluded(isExcluded);
            team.setDivision(division);
            team.setNotes(notes);
            team.setName(name);
            team.setStartTime(startTime);
            team.setEndTime(endTime);
            team.getRiders().add(rider);
        }

        assertEquals(a,b);

    }
}