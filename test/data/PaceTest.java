package data;

import app.App;
import app.Resources;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;
import settings.Settings;

import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

class PaceTest {

    @Test
    void fromFile() {

    }

    @Test
    void fromJson() {
        assertNotNull(Pace.fromJson(new JsonReader(new InputStreamReader(Resources.getResource("/pace.json")))));
    }

    @Test
    void getDivisions() {
    }

    @Test
    void getTeams() {
    }

    @Test
    void updateDivisionLists() {
    }

    @Test
    void populateDivisions() {
    }

    @Test
    void save() {
    }

    @Test
    void serialize() {
    }

    @Test
    void getFile() {
    }

    @Test
    void getUUID() {
    }

    @Test
    void newDivision() {
    }

    @Test
    void addDivision() {
    }

    @Test
    void removeDivision() {
    }

    @Test
    void setDefaultDivision() {
    }

    @Test
    void update() {
    }

    @Test
    void removeTeam() {
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
    void newTeam() {
        Pace pace = new Pace();
        Team team = pace.newTeam();
        assertTrue(pace.getTeams().contains(team));
        assertEquals(team.getDivision(),pace.getDefaultDivision());
    }

    @Test
    void getDefaultDivision() {
        Pace pace = new Pace();
        assertNotNull(pace.getDefaultDivision());
        Division division = new Division();
        pace.addDivision(division);
        assertNotEquals(pace.getDefaultDivision(),division);
        pace.setDefaultDivision(division);
        assertEquals(division,pace.getDefaultDivision());
    }
}