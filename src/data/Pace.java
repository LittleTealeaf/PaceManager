package data;

import app.App;
import app.Serialization;
import settings.Settings;
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
 * Class representing the combined data for a given Pace. Includes methods for saving or loading a pace
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Pace {

    /**
     * Unique Identifier of the Pace
     *
     * @since 1.0.0
     */
    private final UUID uuid;
    /**
     * List of all divisions included in the pace. The first division listed is considered the "default" division,
     * and cannot (or should not) be removed from the list (resulting in an empty list). Any teams that have no division
     * selected will be automatically included in this division
     *
     * @since 1.0.0
     */
    private final List<Division> divisions;
    /**
     * List of all teams in the pace
     *
     * @since 1.0.0
     */
    private final List<Team> teams;
    /**
     * The file that the pace is stored in. If the pace is brand new, or does not have a file, this value is {@code null}.
     * The data is stored in the file in a JSON file format, regardless of whether the extension is of any extension
     * listed in {@link Settings#getFileExtensions()}
     *
     * @since 1.0.0
     */
    private transient File file;

    /**
     * Creates a new pace, initializing values.
     *
     * @since 1.0.0
     */
    public Pace() {
        uuid = UUID.randomUUID();
        teams = new LinkedList<>();
        divisions = new ArrayList<>();
        divisions.add(new Division("Default"));
    }

    /**
     * Attempts to create a new pace from a given {@code file}.
     * <p>If the file doesn't exist, or if there are errors in
     * parsing the file, returns a new {@code Pace} object with a reference to that file, such that the pace will save
     * to that file.</p>
     *
     * @param file File reference to the saved pace data
     * @return A {@code Pace} object, set to save to the provided file. The {@code Pace} object is an empty pace if
     * there were errors in parsing the file
     * @since 1.0.0
     */
    public static Pace fromFile(File file) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            Pace pace = fromJson(reader);
            pace.setFile(file);
            return pace;
        } catch (Exception e) {
            Pace pace = new Pace();
            pace.setFile(file);
            return pace;
        }
    }

    /**
     * Returns a pace object derived from a Json reader. Populates divisions and updates the division list.
     *
     * @param reader JsonReader to read the pace data from
     * @return Pace object derived from data in the JsonReader
     * @since 1.0.0
     */
    public static Pace fromJson(JsonReader reader) {
        Pace pace = Serialization.getGson().fromJson(reader, Pace.class);
        pace.populateDivisions();
        pace.updateDivisionLists();
        return pace;
    }

    /**
     * Gets a list of divisions
     *
     * @return List of division included in the pace
     * @since 1.0.0
     */
    public List<Division> getDivisions() {
        return divisions;
    }

    /**
     * Gets a list of teams
     *
     * @return List of teams tracked in the pace
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
                if (file.createNewFile()) {
                    System.out.println("Created file " + file.getPath());
                }
                FileWriter writer = new FileWriter(file);
                serialize(writer);
                writer.close();
                App.settings.addRecentFile(getFile().getPath());
            } catch (Exception ignored) {}
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

    //these comments are not good lol

    /**
     * Gets the save file
     *
     * @return File referencing the location of save-data for the pace
     * @see #file
     * @since 1.0.0
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the save file
     *
     * @param file File referencing the location of save-data for the pace
     * @see #file
     * @since 1.0.0
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Gets the pace's UUID
     *
     * @return Unique Identifier of the pace
     * @see #uuid
     * @since 1.0.0
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Creates a new division from a given name, adding it to the list, and returning its UUID
     *
     * @param name Name of the new division
     * @return UUID of the newly created division
     * @since 1.0.0
     */
    public UUID newDivision(String name) {
        Division division = new Division();
        division.setName(name);
        divisions.add(division);
        return division.getUUID();
    }

    /**
     * Adds a given division to the list
     *
     * @param division Division to add to the list
     * @since 1.0.0
     */
    public void addDivision(Division division) {
        divisions.add(division);
    }

    /**
     * Removes a given division from the list. Will not remove the division if the provided division is the current
     * default division.
     *
     * @param division Division to remove
     * @return {@code true} if removing was successful, {@code false} if the division is not in the list, or if the division
     * is the default division and cannot be removed
     */
    public boolean removeDivision(Division division) {
        if (divisions.indexOf(division) > 0) {
            while (division.getTeams().size() > 0) {
                division.getTeams().get(0).setDivision(divisions.get(0));
            }
            return divisions.remove(division);
        } else {
            return false;
        }
    }

    /**
     * Sets the default division
     * <p>Useful if there is a need for changing default divisions to remove the previous default division</p>
     *
     * @param division Division to set as default
     * @return {@code true} if the provided division was set as default, {@code false} if the division is not in the
     * current list of divisions, or it is already the default division
     */
    public boolean setDefaultDivision(Division division) {
        if (!divisions.contains(division) || divisions.get(0) == division) {
            return false;
        } else {
            Division currentDefault = divisions.get(0);
            int index = divisions.indexOf(division);
            divisions.set(0, division);
            divisions.set(index, currentDefault);
            return true;
        }
    }

    /**
     * Lets the pace know that there has been an update to one of its child objects
     */
    public void update() {
        updateDivisionLists();
        if (App.settings.isAggressiveSave()) {
            save();
        }
    }

    /**
     * Prompts the user to delete if settings require
     *
     * @param team Team to delete
     */
    public boolean removeTeam(Team team) {
        if (App.settings.warnOnDelete() == App.warnDelete(team.getTeamName())) {
            boolean result = teams.remove(team);
            if (result) {
                App.update();
            }
            return result;
        } else {
            return false;
        }
    }

    public Team newTeam() {
        Team team = new Team();
        team.setDivision(divisions.get(0));
        teams.add(team);
        return team;
    }

    public Division getDefaultDivision() {
        return divisions.get(0);
    }
}
