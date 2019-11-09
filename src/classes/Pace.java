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
		updateStatics();
	}

	public Pace(String setTitle, List<Team> listTeams, List<Goal> listGoals, Settings getSettings) {
		title = setTitle;
		teams = listTeams;
		goals = listGoals;
		settings = getSettings;
		version = paceManager.version;
		updateStatics();
	}
	
	public void updateStatics() {
		title = Title;
		version = Version;
		teams = Teams;
		goals = Goals;
		settings = Settings;
	}
	public void updateLocals() {
		Title = title;
		Version  = version;
		Teams = teams;
		Goals = goals;
		Settings = settings;
	}
}