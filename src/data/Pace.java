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
public class Pace {

	private final UUID uuid;
	private final List<Division> divisions;
	private final List<Team> teams;
	private transient File file;

	public Pace() {
		uuid = UUID.randomUUID();
		teams = new LinkedList<>();
		divisions = new ArrayList<>();
	}

	/**
	 * Creates a Pace object from a file, and attaches that file to the pace
	 *
	 * @param file Location of the Pace File
	 * @return {@code Pace} object with the {@code File} connected. Will return {@code null} if FileIO or other errors occur.
	 */
	public static Pace fromFile(File file) {
		try {
			JsonReader reader = new JsonReader(new FileReader(file));
			Pace pace = fromJson(reader);
			pace.setFile(file);
			return pace;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Creates a Pace from a given JSON
	 *
	 * @param reader Json Reader
	 * @return Pace object with the given data
	 */
	public static Pace fromJson(JsonReader reader) {
		Pace pace = Serialization.getGson().fromJson(reader, Pace.class);
		pace.populateDivisions();
		return pace;
	}

	/**
	 * Returns a list of all divisions
	 *
	 * @return List of all divisions in the pace
	 */
	public List<Division> getDivisions() {
		return divisions;
	}

	/**
	 * Returns a list of all teams
	 *
	 * @return List of all teams in the pace
	 */
	public List<Team> getTeams() {
		return teams;
	}

	/**
	 * Wipes out all division team lists and repopulates them
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
	 */
	public void populateDivisions() {
		boolean clear = App.settings.getAggressiveMemorySave();
		for (Team team : teams) {
			for (Division division : divisions) {
				if (team.getDivisionUUID().equals(division.getUUID())) {
					team.setDivision(division);
				}
			}
			if (clear) {
				team.clearDivisionUUID();
			}
		}
	}

	/**
	 * Attempts to save the Pace to the file specified
	 */
	public void save() {
		if (file != null) {
			try {
				serialize(new FileWriter(file));
			} catch (Exception ignore) {}
		}
	}

	/**
	 * Serializes the data within this object to a writer
	 *
	 * @param writer Writer to serialize the data to
	 */
	public void serialize(Writer writer) {
		//Updates all
		for (Team team : teams) {
			team.updateDivisionUUID();
		}
		Serialization.getGson().toJson(this, writer);
		if (App.settings.getAggressiveMemorySave()) {
			for (Team team : teams) {
				team.clearDivisionUUID();
			}
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public UUID getUUID() {
		return uuid;
	}
}
