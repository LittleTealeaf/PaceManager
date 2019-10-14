package application;

import java.util.ArrayList;
import java.util.List;

import classes.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class fileManager {

	private static final String ind = "|";
	
	
	public static File loadedFile;

	public static void save() {
		if(loadedFile == null) {
			saveAs();
			return;
		} else {
			fileSave(loadedFile);
		}
	}
	public static void saveAs() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pace Files","*.pace"));
		fileSave(fileChooser.showSaveDialog(fxMain.sMRef));
	}
	public static void open() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Pace File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pace Files","*.pace"));
		openFile(fileChooser.showOpenDialog(fxMain.sMRef));	
	}
	
	private static void openFile(File file) {
		try {
			Path filePath = file.toPath();
			Charset charset = Charset.defaultCharset();
			try {
				getData(Files.readAllLines(filePath,charset).get(0));
				loadedFile = file;
			} catch (IOException e) {}
		} catch (NullPointerException e) {}
	}

	private static void fileSave(File file) {
		if(file != null) {
			try {
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(saveData());
					fileWriter.flush();
					fileWriter.close();
					loadedFile = file;
				} catch (IOException e) {
					System.out.println("FAIL");
				}
			} catch(NullPointerException e) {
				System.out.println("FAIL");
			}
		}
	}

	private static String saveData() {
		String ret = "";
		ret+="<goaltime>";
		for(Goal a : paceManager.goals) {
			ret+="<division><name>" + a.division + "</name>";
			// + a.goal.ToString() + "</Time></division>";
			if(a.time != null) ret+="<time>" + a.time.toString() + "</time>";
			ret+="</division>";
		}
		ret+="</goaltime><teams>";
		for(Team a : paceManager.teams) {
			
			ret+="<team><teamno>" + a.team + "</teamno><division>" + a.division + "</division><exclude>" + a.excluded + "</exclude><riders>";
			for(String b : a.names) {
				ret+="<rname>" + b + "</rname>";
			}
			ret+="</riders>";
			if(a.start != null) {
				ret+="<timeout>" + a.start.toString(true) + "</timeout>";
			}
			if(a.finish != null) {
				ret+="<timeback>" + a.finish.toString(true) + "</timeback>";
			}
			ret+="<notes>";
			for(String b : a.notes) {
				ret+="<nline>";
				ret+=b;
				if(b == "") System.out.println("NAOWEFNOAWFAEW");
				ret+="</nline>";
			}
			ret+="</notes></team>";
		}
		ret+="</teams>";
		return(ret);
	}
	public static void getData(String data) {
		//Method: Split everything into a list of strings, then go from there
		List<String> parData = new ArrayList<String>();
		String parWord = "";
		int pos = 0;
		for(char c : data.toCharArray()) {
			switch(pos) {
			case 0:
				if(c == '<') {
					if(parWord != "") parData.add(parWord);
					parWord = "";
					pos = 1;
				} else {
					parWord+=c;
				}
				break;
			case 1:
				if(c == '>') {
					parData.add(ind + parWord);
					parWord = "";
					pos = 0;
				} else {
					parWord+=c;
				}
			}
		}
		//while(parData.contains("")) parData.remove("");
		while(parData.contains(ind + "rname")) parData.remove(ind + "rname");
		while(parData.contains(ind + "/rname")) parData.remove(ind + "/rname");
		while(parData.contains(ind + "nline")) parData.remove(ind + "nline");
		while(parData.contains(ind + "/nline")) parData.remove(ind + "/nline");
		//System.out.println(parData);
		
		List<Goal> setGoalTimes = new ArrayList<Goal>();
		List<Team> setTeams = new ArrayList<Team>();
		
		String loc = "/";
		Goal tmGoal = new Goal();
		Team tmTeam = new Team();
		for(String a : parData) {
			//System.out.print("\n" + loc + " | ");
			switch(loc) {
			case "/goaltime":
				// Looking for Divisions
				tmGoal = new Goal();
				break;
			case "/goaltime/division":
				// Division Found
				if(a.contains(ind) && a.contains("/")) setGoalTimes.add(tmGoal);
				break;
			case "/goaltime/division/name":
				if(!a.contains(ind)) tmGoal.division = a;
				break;
			case "/goaltime/division/time":
				if(!a.contains(ind)) tmGoal.time = new Time(a);
				break;
			case "/teams":
				tmTeam = new Team();
				break;
			case "/teams/team":
				if(a.contains(ind) && a.contains("/")) setTeams.add(tmTeam);
				break;
			case "/teams/team/teamno":
				if(!a.contains(ind)) tmTeam.team = a;
				break;
			case "/teams/team/division":
				if(!a.contains(ind)) tmTeam.division = a;
				break;
			case "/teams/team/riders":
				if(!a.contains(ind)) tmTeam.names.add(a);
				break;
			case "/teams/team/timeout":
				if(!a.contains(ind)) tmTeam.start = new Time(a);
				break;
			case "/teams/team/timeback":
				if(!a.contains(ind)) tmTeam.finish = new Time(a);
				break;
			case "/teams/team/notes":
				if(!a.contains(ind)) tmTeam.notes.add(a);
				break;
			}
			loc = setLoc(loc,a);
		}
		// Setting the Data
		System.out.println(setTeams);
		paceManager.teams = setTeams;
		paceManager.goals = setGoalTimes;
		fxMain.updateTable();
	}
	private static String setLoc(String curLoc, String a) {
		String ret = curLoc;
		if(a.contains(ind)) {
			if(a.contains("/")) {
				ret = curLoc.substring(0,curLoc.lastIndexOf("/"));
			} else if(curLoc == "/") {
				ret = curLoc + a.replace(ind, "");
			} else {
				ret = curLoc + "/" + a.replace(ind, "");
			}
		}
		return ret;
	}
	
	public static boolean checkValid(String s) {
		char[] illegalChars = {'/','<','>','[',']','/','\\','='};
		List<Character> chars = new ArrayList<Character>();
		for(char c : s.toCharArray()) chars.add(c);
		for(char c : illegalChars) {
			if(chars.contains(c)) return false;
		}
		return true;
	}
}