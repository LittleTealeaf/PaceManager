package classes;

import java.util.List;

public class FileJSON {
	public List<Team> teams;
	public List<Goal> goals;
	public FileJSON(List<Team> listTeams, List<Goal> listGoals) {
		teams = listTeams;
		goals = listGoals;
	}
}
