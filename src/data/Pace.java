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

	public List<Division> getDivisions() {
		return divisions;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public static Pace fromJson(JsonReader reader) {
		Pace pace = Serializer.getGson().fromJson(reader, Pace.class);
		pace.populateDivisions();
		return pace;
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
