package data;

import com.google.gson.stream.JsonReader;
import main.Application;

import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pace {

	private final List<Division> divisions;
	private final List<Team> teams;

	public Pace() {
		teams = new LinkedList<>();
		divisions = new ArrayList<>();
	}

	/**
	 * Creates a Pace from a given JSON
	 *
	 * @param reader Json Reader
	 * @return Pace object with the given data
	 */
	public static Pace fromJson(JsonReader reader) {
		Pace pace = Serializer.getGson().fromJson(reader, Pace.class);
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
		boolean clear = Application.settings.getAggressiveMemorySave();
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
	 * Serializes the data within this object to a writer
	 *
	 * @param writer Writer to serialize the data to
	 */
	public void serialize(Writer writer) {
		//Updates all
		for (Team team : teams) {
			team.updateDivisionUUID();
		}
		Serializer.getGson().toJson(this, writer);
		if (Application.settings.getAggressiveMemorySave()) {
			for (Team team : teams) {
				team.clearDivisionUUID();
			}
		}
	}
}
