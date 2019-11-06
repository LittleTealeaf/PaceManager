package application;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import classes.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

public class fileManager {

	private static final String ind = "|";
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
		//openFile(fileChooser.showOpenDialog(fxMain.sMRef));	
	}
	
	
	//http://tutorials.jenkov.com/java-json/gson.html
	public static void fileSave(File saveFile) {
		Gson gson = new Gson();
		FileJSON data = new FileJSON(paceManager.teams,paceManager.goals);
		
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