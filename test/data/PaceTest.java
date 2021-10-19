package data;

import app.App;
import app.Resources;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;
import test.Config;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

public class PaceTest {

    @Test
    public void fromFile() {

    }

    @Test
    public void fromJson() throws Exception {
        assertNotNull(Pace.fromJson(new JsonReader(new InputStreamReader(Resources.getResource("/pace.json")))));
    }

    @Test
    public void getDivisions() {
        Pace pace = Pace.newPace();
        assertNotNull(pace.getDivisions());
        assertEquals(1,pace.getDivisions().size());
        Division division = new Division();
        pace.addDivision(division);
        assertEquals(2,pace.getDivisions().size());
        assertEquals(division,pace.getDivisions().get(1));
    }

    @Test
    public void getTeams() {
        Pace pace = Pace.newPace();
        Team[] teams = new Team[Config.ARRAY_SIZE];
        for(int i = 0; i < Config.ARRAY_SIZE; i++) {
            teams[i] = pace.newTeam();
        }
        for (Team team : teams) {
            assertTrue(pace.getTeams().contains(team));
        }
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
    public void update() {
    }

    @Test
    public void removeTeam() {
        Pace pace = Pace.newPace();
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
        Pace pace = Pace.newPace();
        Team team = pace.newTeam();
        assertTrue(pace.getTeams().contains(team));
        assertEquals(team.getDivision(),pace.getDefaultDivision());
    }

    @Test
    public void testDefaultDivision() {
        Pace pace = Pace.newPace();
        assertNotNull(pace.getDefaultDivision());
        Division division = new Division();
        pace.addDivision(division);
        assertNotEquals(pace.getDefaultDivision(),division);
        pace.setDefaultDivision(division);
        assertEquals(division,pace.getDefaultDivision());
    }
}