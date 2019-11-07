package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import classes.PaceData;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

//TODO Add a version checker and error message

public class fileManager {

	private static final char[] ILLEGAL_CHARS = {'/','<','>','[',']','/','\\','=','|'};
	
	public static File loadedFile;

	/**
	 * Saves the currently loaded file, if there is no file or the file does not exist it will use the save-as script
	 */
	public static void save() {
		if(loadedFile == null || !loadedFile.exists()) {
			saveAs();
			return;
		} else {
			fileSave(loadedFile);
		}
	}
	/**
	 * Opens a "save-as" dialog
	 */
	public static void saveAs() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pace Files","*.pace"));
		fileSave(fileChooser.showSaveDialog(fxMain.sMRef));
	}
	/**
	 * Opens an "open file" dialog to pass to the openFile method
	 */
	public static void open() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Pace File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Pace Files","*.pace"));
		fileOpen(fileChooser.showOpenDialog(fxMain.sMRef));	
	}
	
	
	//http://tutorials.jenkov.com/java-json/gson.html
	public static void fileSave(File saveFile) {
		try {
			//Create a writer
			FileWriter writer = new FileWriter(saveFile);
			
			//Write the JSON file, using the GSON library
			writer.write(new Gson().toJson(new PaceData(paceManager.teams,paceManager.goals,paceManager.settings)));
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fileOpen(File openFile) {
		//Cancel if file doesn't exist
		if(!openFile.exists()) return;
		try {
			//Create reader to read lines into a list
			List<String> lines = Files.readAllLines(openFile.toPath());
			
			//Cancel if file is empty
			if(lines.size() == 0) return; 
			
			//Concatenate list of strings into a single string
			String jsonString = "";
			for(String l : lines) jsonString+=l;
			
			//Import JSON into the pace
			if(!new Gson().fromJson(jsonString, PaceData.class).updatePace()) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if a string is valid
	 * @param s The String to be checked
	 * @return True if it works, false if it contains invalid characters
	 */
	public static boolean checkValid(String s) {
		List<Character> chars = new ArrayList<Character>();
		for(char c : s.toCharArray()) chars.add(c);
		for(char c : ILLEGAL_CHARS) {
			if(chars.contains(c)) return false;
		}
		return true;
	}
}
