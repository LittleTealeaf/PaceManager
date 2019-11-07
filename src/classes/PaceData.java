package classes;

import java.util.List;

import application.*;

public class PaceData {
	
	public String version;
	
	public List<Team> teams;
	public List<Goal> goals;
	public Settings settings;

	public PaceData(List<Team> listTeams, List<Goal> listGoals, Settings getSettings) {
		teams = listTeams;
		goals = listGoals;
		settings = getSettings;
		version = paceManager.version;
	}
	
	public void updatePace() {
		//Update Values
		paceManager.teams = teams;
		paceManager.goals = goals;
		if(settings != null) paceManager.settings = settings;
		
		//Update Windows
		fxMain.updateTable();
		if(fxScores.sScores != null) fxScores.updateTables();
		
	}
}
