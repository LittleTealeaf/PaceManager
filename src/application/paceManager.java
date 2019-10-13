package application;

import java.util.ArrayList;
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
		paceManager.goals = new ArrayList<Goal>();
		//Debug Only
		teams = importTeams.getTeams();
		importTeams.randomizeTimes();
		System.out.println(getTeam("441"));
		
		fxMain.open(args);
	}
	
	public static Team getTeam(String tmName) {
		for(Team t : teams) {
			String a = t.team.toLowerCase().replace(" ", "").replace("\n", "").replace("\r","");
			String b = tmName.toLowerCase().replace(" ", "").replace("\n", "").replace("\r","");
			if(a.contentEquals(b)) return t;
		}
		return null;
	}
}