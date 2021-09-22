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
 * @since 1.0.0
 * @version 1.0.0
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
	}

	/**
	 * @since 1.0.0
	 * @param file
	 * @return
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
	 *
	 * @since 1.0.0
	 * @param reader
	 * @return
	 */
	public static Pace fromJson(JsonReader reader) {
		Pace pace = Serialization.getGson().fromJson(reader, Pace.class);
		pace.populateDivisions();
		return pace;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public List<Division> getDivisions() {
		return divisions;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public List<Team> getTeams() {
		return teams;
	}

	/**
	 * Wipes out all division team lists and repopulates them
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
	 * @since 1.0.0
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
	 * @since 1.0.0
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
	 * @since 1.0.0
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

	/**
	 * @since 1.0.0
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @since 1.0.0
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public UUID getUUID() {
		return uuid;
	}
}
