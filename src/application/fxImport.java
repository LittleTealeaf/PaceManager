package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.*;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class fxImport {
	
	private static Stage sImport;
	private static TextField tTeam;
	private static TextField tData;
	@SuppressWarnings("rawtypes")
	private static ChoiceBox cMethod;
	public static Label lData;
	
	/**
	 * 0 Start, 1 End
	 */
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void open() {
		if(sImport != null) sImport.close();
		sImport = new Stage();
		sImport.setWidth(500);
		sImport.setHeight(200);
		sImport.setTitle("Import Data");
		
		Label lTeam = new Label("Team");
		
		tTeam = new TextField();
		tTeam.setEditable(true);
		tTeam.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
			if(keyEvent.getCode() == KeyCode.ENTER) {
				if(cMethod.getSelectionModel().getSelectedIndex() != -1) {
					tData.requestFocus();
				} else cMethod.requestFocus();
			}
		});
		
		Label lChoice = new Label("Import Option:");
		
		cMethod = new ChoiceBox(FXCollections.observableArrayList("Start Time", "End Time"));
		cMethod.getSelectionModel().selectedIndexProperty().addListener((obs,oldv,newv) -> {
			if(cMethod.getSelectionModel().getSelectedIndex() != -1) {
				tData.setEditable(true);
				tData.setPromptText("");
			}
		});
		cMethod.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
			if(keyEvent.getCode() == KeyCode.ENTER) {
				if(cMethod.getSelectionModel().getSelectedIndex() != -1) {
					tData.requestFocus();
				}
			}
		});
		
		lData = new Label("");
		tData = new TextField();
		tData.setEditable(false);
		tData.setPromptText("Select an Import Option");
		tData.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
			if(keyEvent.getCode() == KeyCode.ENTER) {
				saveImport();
			}
		});
		
		VBox h1vb1 = new VBox(lTeam,tTeam);
		VBox h1vb2 = new VBox(lChoice,cMethod);
		VBox h1vb3 = new VBox(lData,tData);
		
		HBox hb = new HBox(h1vb1,h1vb2,h1vb3);
		VBox vbMain = new VBox(hb);
		Scene sc = new Scene(vbMain);
		sImport.setScene(sc);
		sImport.show();
	}
	
	private static void saveImport() {
		if(tTeam.getText() == "") {
			tTeam.setPromptText("Please Type a Team Identifier");
			return;
		}
		Team t = paceManager.getTeam(tTeam.getText());
		Boolean isNew = t == null;
		if(isNew) t = new Team(tTeam.getText());
		Time v;
		switch(cMethod.getSelectionModel().getSelectedIndex()) {
		case 0:
			v = new Time(tData.getText());
			if(v.error == 0) t.start = v;
			else {
				lData.setText("Could not Parse");
				return;
			}
			break;
		case 1:
			v = new Time(tData.getText());
			if(v.error == 0) t.finish = v;
			else {
				lData.setText("Could not Parse");
				return;
			}
			break;
		default:
			lData.setText("Variable Selection not valid");
			return;
		}
		if(isNew) paceManager.teams.add(t);
		tTeam.setText("");
		tData.setText("");
		fxMain.updateTable();
		tTeam.requestFocus();
	}
}
