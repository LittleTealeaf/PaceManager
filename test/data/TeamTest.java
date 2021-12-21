package data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import serialization.Serializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

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
}