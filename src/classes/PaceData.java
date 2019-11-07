package classes;

import java.util.List;

import application.*;

public class PaceData {
	
	public String title;
	public String version;
	
	public List<Team> teams;
	public List<Goal> goals;
	public Settings settings;

	public PaceData(String setTitle, List<Team> listTeams, List<Goal> listGoals, Settings getSettings) {
		title = setTitle;
		teams = listTeams;
		goals = listGoals;
		settings = getSettings;
		version = paceManager.version;
	}
	
	public void updatePaceForce() { updatePace(true); }
	public boolean updatePace() { return updatePace(false); }
	public boolean updatePace(boolean force) {
		if(!version.contentEquals(paceManager.version) && !force) return false;
		//Update Values
		paceManager.title = title;
		paceManager.teams = teams;
		paceManager.goals = goals;
		if(settings != null) paceManager.settings = settings;
		
		//Update Windows
		fxMain.updateTable();
		if(fxScores.sScores != null) fxScores.updateTables();
		return true;
	}
}
