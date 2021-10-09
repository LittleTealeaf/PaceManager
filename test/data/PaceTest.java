package data;

import app.App;
import app.Resources;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;
import settings.Settings;
import test.Util;

import java.io.*;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

public class PaceTest {

    @Test
    public void fromFile() throws IOException {

    }

    @Test
    public void fromJson() {
        assertNotNull(Pace.fromJson(new JsonReader(new InputStreamReader(Resources.getResource("/pace.json")))));
    }

    @Test
    public void getDivisions() {
    }

    @Test
    public void getTeams() {
    }

    @Test
    public void updateDivisionLists() {
    }

    @Test
    public void populateDivisions() {
    }

    @Test
    public void save() {
    }

    @Test
    public void serialize() {
    }

    @Test
    public void getFile() {
    }

    @Test
    public void getUUID() {
    }

    @Test
    public void newDivision() {
    }

    @Test
    public void addDivision() {
    }

    @Test
    public void removeDivision() {
    }

    @Test
    public void setDefaultDivision() {
    }

    @Test
    public void update() {
    }

    @Test
    public void removeTeam() {
        Pace pace = new Pace();
        App.openedPace = pace;
        Team team = pace.newTeam();
        assertTrue(pace.removeTeam(team));
        assertFalse(pace.removeTeam(team));
        assertFalse(pace.removeTeam(null));
        assertFalse(pace.getTeams().contains(team));
        App.openedPace = null;
    }

    @Test
    public void newTeam() {
        Pace pace = new Pace();
        Team team = pace.newTeam();
        assertTrue(pace.getTeams().contains(team));
        assertEquals(team.getDivision(),pace.getDefaultDivision());
    }

    @Test
    public void getDefaultDivision() {
        Pace pace = new Pace();
        assertNotNull(pace.getDefaultDivision());
        Division division = new Division();
        pace.addDivision(division);
        assertNotEquals(pace.getDefaultDivision(),division);
        pace.setDefaultDivision(division);
        assertEquals(division,pace.getDefaultDivision());
    }
}