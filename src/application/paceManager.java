package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.Goal;
import classes.Team;
import debugdev.importTeams;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/*
 * https://code.makery.ch/blog/javafx-dialogs-official/ --> dialogues
 */

public class paceManager {
	
	public static final String version = "0.1";

	public static List<Team> teams;
	public static List<Goal> goals;
	
	private static final boolean development = true;
	
	public static void main(String[] args) {
		pacePreferences.alertOnDelete = true;
		teams = new ArrayList<Team>();
		goals = new ArrayList<Goal>();
		//Debug Only
		if(development) {
			teams = importTeams.getTeams();
			importTeams.randomizeTimes();
			importTeams.importGoals();	
		}
		
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
	
	public static void deleteTeam(Team t) {
		if(pacePreferences.alertOnDelete) {
			Alert conf = new Alert(AlertType.CONFIRMATION);
			conf.setTitle("Delete " + t.team + "?");
			conf.setHeaderText("Do you really want to delete " + t.team + "?");
			Optional<ButtonType> result = conf.showAndWait();
			if(result.get() != ButtonType.OK) return;
		}
		paceManager.teams.remove(t);
		fxMain.updateTable();
	}
	
	public static List<Team> getTeams(String division) {
		List<Team> ret = new ArrayList<Team>();
		for(Team t : teams) {
			if(t.division.contentEquals(division)) {
				ret.add(t);
			}
		}
		return ret;
	}
}