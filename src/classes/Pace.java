package classes;

import java.util.ArrayList;
import java.util.List;

import application.fxMain;
import application.fxScores;

public class Pace {
	
	public String Title;
	public String Version;
	
	public List<Team> Teams;
	public List<Goal> Goals;
	public Settings Settings;
	
	public static String title;
	public static String version;
	public static List<Team> teams;
	public static List<Goal> goals;
	public static Settings settings;
	
	public Pace() {
		Title = title;
		Version = version;
		Teams = teams;
		Goals = goals;
		Settings = settings;
	}

	public Pace(String setTitle, List<Team> listTeams, List<Goal> listGoals, Settings getSettings) {
		Title = setTitle;
		Teams = listTeams;
		Goals = listGoals;
		Settings = getSettings;
		Version = application.paceManager.version;
		loadPace();
	}
	
	/**
	 * Applies object-values to the pace main values
	 */
	public void loadPace() {
		title = Title;
		version = Version;
		teams = Teams;
		goals = Goals;
		settings = Settings;
		fxMain.updateTable();
		fxScores.updateTabs();
	}
	
	public static void newPace() { newPace(""); }
	public static void newPace(String setTitle) {
		title = setTitle;
		version = application.paceManager.version;
		teams = new ArrayList<Team>();
		goals = new ArrayList<Goal>();
		settings = new Settings();
	}
}