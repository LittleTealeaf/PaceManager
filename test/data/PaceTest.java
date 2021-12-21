package data;

import app.App;
import app.Resources;
import app.Serialization;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;
import test.Config;

import java.io.File;
import java.io.InputStreamReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class PaceTest {

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
    public void serialize() {
        StringWriter writer = new StringWriter();
        Pace pace = Pace.newPace();
        pace.serialize(writer);
        String string = writer.toString();
        assertNotNull(string);
        assertNotNull(Serialization.getGson().fromJson(string, Pace.class));
    }

    @Test
    public void file() {
        Pace pace = Pace.newPace();
        File file = new File("");
        pace.setFile(file);
        assertEquals(file, pace.getFile());
    }

    @Test
    public void getUUID() {
        Pace pace = Pace.newPace();
        assertNotNull(pace.getUUID());
    }

    @Test
    public void newDivision() {
        Pace pace = new Pace();
        String divName = "";
        pace.newDivision(divName);
        assertEquals(divName, pace.getDivisions().get(1).getName());
        String divName2 = "";
        pace.newDivision(divName2);
        assertEquals(divName2, pace.getDivisions().get(2).getName());
    }

    @Test
    public void addDivision() {
        Pace pace = Pace.newPace();
        Division division = new Division();
        assertFalse(pace.getDivisions().contains(division));
        pace.addDivision(division);
        assertTrue(pace.getDivisions().contains(division));
    }

    @Test
    public void removeDivision() {
        Pace pace = Pace.newPace();
        Division division = new Division();
        pace.addDivision(division);
        assertTrue(pace.getDivisions().contains(division));
        pace.removeDivision(division);
        assertFalse(pace.getDivisions().contains(division));
        pace.addDivision(division);
        pace.setDefaultDivision(division);
        pace.removeDivision(division);
        assertTrue(pace.getDivisions().contains(division));
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