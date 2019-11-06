package classes;

import java.util.List;

public class PaceData {
	public List<Team> teams;
	public List<Goal> goals;
	
	public PaceData(List<Team> listTeams, List<Goal> listGoals) {
		teams = listTeams;
		goals = listGoals;
	}
	public PaceData() {}
}
