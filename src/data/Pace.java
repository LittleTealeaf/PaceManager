package data;

import app.App;
import app.Serialization;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


//Idea: Additional Thread for periodical saving

/**
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Pace {

    private final UUID uuid;
    private final List<Division> divisions;
    private final List<Team> teams;
    private transient File file;

    /**
     * @since 1.0.0
     */
    public Pace() {
        uuid = UUID.randomUUID();
        teams = new LinkedList<>();
        divisions = new ArrayList<>();
        divisions.add(new Division("Default"));
    }

    /**
     * @param file
     * @return
     * @since 1.0.0
     */
    public static Pace fromFile(File file) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            Pace pace = fromJson(reader);
            pace.setFile(file);
            return pace;
        } catch (Exception e) {
            System.out.println(e.toString());
            Pace pace = new Pace();
            pace.setFile(file);
            return pace;
        }
    }

    /**
     * @param reader
     * @return
     * @since 1.0.0
     */
    public static Pace fromJson(JsonReader reader) {
        Pace pace = Serialization.getGson().fromJson(reader, Pace.class);
        pace.populateDivisions();
        pace.updateDivisionLists();
        return pace;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public List<Division> getDivisions() {
        return divisions;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * Wipes out all division team lists and repopulates them
     *
     * @since 1.0.0
     */
    public void updateDivisionLists() {
        for (Division division : divisions) {
            division.clearTeams();
        }
        for (Team team : teams) {
            if (team.getDivision() != null) {
                team.getDivision().addTeam(team);
            }
        }
    }

    /**
     * Populates each {@link Team team's} {@link Division} value based on their {@code DivisionUUID} parameter
     *
     * @since 1.0.0
     */
    public void populateDivisions() {
        boolean clear = App.settings.isAggressiveMemorySave();
        for (Team team : teams) {
            if (team.getDivisionUUID() != null) {
                boolean found = false;
                for (Division division : divisions) {
                    if (team.getDivisionUUID().equals(division.getUUID())) {
                        team.setDivision(division);
                        found = true;
                    }
                }
                if (clear || !found) {
                    team.clearDivisionUUID();
                }
            }

            if (team.getDivisionUUID() == null) {
                team.setDivision(divisions.get(0));
            }

        }
    }

    /**
     * Attempts to save the Pace to the file specified
     *
     * @since 1.0.0
     */
    public void save() {
        if (file != null) {
            try {
                if(file.createNewFile()) {
                    System.out.println("Created file " + file.getPath());
                }
                FileWriter writer = new FileWriter(file);
                serialize(writer);
                writer.close();
            } catch (Exception ignore) {
            }
        }
    }

    /**
     * Serializes the data within this object to a writer
     *
     * @param writer Writer to serialize the data to
     * @since 1.0.0
     */
    public void serialize(Writer writer) {
        //Updates all
        updateDivisionLists();
        for (Team team : teams) {
            if (team.getDivision() != divisions.get(0)) {
                team.updateDivisionUUID();
            } else {
                team.clearDivisionUUID();
            }
        }
        Serialization.getGson().toJson(this, writer);
        if (App.settings.isAggressiveMemorySave()) {
            for (Team team : teams) {
                team.clearDivisionUUID();
            }
        }
    }

    /**
     * @return
     * @since 1.0.0
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file
     * @since 1.0.0
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public UUID getUUID() {
        return uuid;
    }

    public UUID newDivision(String name) {
        Division division = new Division();
        division.setName(name);
        divisions.add(division);
        return division.getUUID();
    }

    public void addDivision(Division division) {
        divisions.add(division);
    }

    public boolean removeDivision(Division division) {
        if(divisions.indexOf(division) > 0) {
        	updateDivisionLists();
        	for(Team team : division.getTeams()) {
        		team.setDivision(divisions.get(0));
			}
        	return divisions.remove(division);
		} else {
			return false;
		}
    }

    public void pingUpdate() {
        if(App.settings.isAggressiveSave()) {
            save();
        }
    }
}
