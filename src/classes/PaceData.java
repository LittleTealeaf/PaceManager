package classes;

import java.util.List;

import application.*;

public class PaceData {
	public List<Team> teams;
	public List<Goal> goals;
	
	public PaceData(List<Team> listTeams, List<Goal> listGoals) {
		teams = listTeams;
		goals = listGoals;
	}
	public PaceData() {}
	
	public void updatePace() {
		paceManager.teams = teams;
		paceManager.goals = goals;
		fxMain.updateTable();
		if(fxScores.sScores != null) fxScores.updateTables();
	}
}
