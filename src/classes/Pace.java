package classes;

import java.util.ArrayList;
import java.util.List;

import application.*;

public class Pace {
	
	public String Title;
	public String Version;
	
	public List<Team> Teams;
	public List<Goal> Goals;
	public Settings Settings;
	
	//Loaded Variables
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
		Version = paceManager.version;
		loadPace();
	}
	
	public void loadPace() {
		title = Title;
		version = Version;
		teams = Teams;
		goals = Goals;
		settings = Settings;
	}
	
	public void savePace() {
		Title = title;
		Version  = version;
		Teams = teams;
		Goals = goals;
		Settings = settings;
	}
	
	public static void newPace() {
		title = "";
		version = paceManager.version;
		teams = new ArrayList<Team>();
		goals = new ArrayList<Goal>();
		settings = new Settings();
	}
}