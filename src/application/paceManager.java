package application;

import java.util.List;
import classes.*;
import debugdev.importTeams;;

public class paceManager {
	
	public static final String version = "0.1";

	public static List<Team> teams;
	
	public static void main(String[] args) {
		//Debug Only
		teams = importTeams.importTeams();
		importTeams.randomizeTimes();
		
		fxMain.open(args);
	}

}