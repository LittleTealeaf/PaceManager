package data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pace {

	private List<Division> divisions;
	private List<Team> teams;

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
}
