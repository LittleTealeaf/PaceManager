package application;

import java.util.List;
import classes.Goal;
import classes.Team;
import debugdev.importTeams;

/*
 * https://code.makery.ch/blog/javafx-dialogs-official/ --> dialogues
 */

public class paceManager {
	
	public static final String version = "0.1";

	public static List<Team> teams;
	public static List<Goal> goals;
	
	public static void main(String[] args) {
		//Debug Only
		teams = importTeams.getTeams();
		importTeams.randomizeTimes();
		
		fxMain.open(args);
	}
	
	public static Team getTeam(String tmName) {
		for(Team t : teams) if(t.team == tmName) return t;
		return null;
	}
}