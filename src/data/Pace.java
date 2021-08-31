package data;

import com.google.gson.stream.JsonReader;

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
		return Serializer.getGson().fromJson(reader, Pace.class);
	}

	public String serialize() {
		//Update all teams to include their division UUID
		for (Team team : teams) {
			team.updateDivisionUUID();
		}

		return Serializer.getGson().toJson(this);
	}
}
