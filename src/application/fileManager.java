package application;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

import classes.Pace;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class fileManager {

	//TODO test if I can still include illegal chars with the program
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
			writer.write(new Gson().toJson(new Pace()));
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
			
			Pace data = new Gson().fromJson(jsonString, Pace.class);
			//Import JSON into the pace
			if(!data.version.contentEquals(paceManager.version)) {
				//Versions don't match up
				Alert conf = new Alert(AlertType.CONFIRMATION);
				conf.setTitle("Version Mismatch");
				conf.setHeaderText("File Save is on version " + data.version + " and you're running version " + paceManager.version);
				conf.setContentText("Would you like to import anyways? Some features may not be imported.");
				Optional<ButtonType> result = conf.showAndWait();
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
