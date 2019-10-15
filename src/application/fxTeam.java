package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class fxTeam {
	@SuppressWarnings("exports")
	public static Stage sTeam;
	
	private static Team team;
	
	private static TextField tTeam;
	private static TextField tDiv;
	private static TextField tStart;
	private static TextField tFinish;
	private static TextArea tRiders;
	private static TextArea tNotes;
	private static Label lError;
	private static CheckBox cExclude;
	
	public static void open(Team t) {
		team = t;
		if(sTeam != null) sTeam.close();
		sTeam = new Stage();
		sTeam.setWidth(500);
		sTeam.setAlwaysOnTop(true);
		
		Label lTeam = new Label("Team");
		tTeam = new TextField();
		Label lDiv = new Label("Division");
		tDiv = new TextField();
		cExclude = new CheckBox();
		cExclude.setText("Exclude");
		cExclude.setTooltip(new Tooltip("Exclude from Rankings"));
		HBox h1 = new HBox(lTeam,tTeam,lDiv,tDiv,cExclude);
		h1.setSpacing(10);
		
		Label lStart = new Label("Start Time:");
		tStart = new TextField();
		Label lFinish = new Label("Finish Time:");
		tFinish = new TextField();
		HBox h2 = new HBox(lStart,tStart,lFinish,tFinish);
		h2.setSpacing(10);
		
		Label lRiders = new Label("Riders");
		tRiders = new TextArea();
		Label lNotes = new Label("Notes");
		tNotes = new TextArea();
		HBox h3 = new HBox(new VBox(lRiders,tRiders),new VBox(lNotes,tNotes));
		h3.setSpacing(10);
		
		Button bCancel = new Button("Cancel");
		bCancel.setPrefWidth(75);
		bCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				sTeam.close();
			}
		});
		lError = new Label();
		lError.setTextFill(Color.RED);
		Region rb = new Region();
		Button bSave = new Button("Save");
		bSave.setPrefWidth(75);
		bSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(save()) sTeam.close();
			}
		});
		HBox h4 = new HBox(bCancel,lError,rb,bSave);
		HBox.setHgrow(rb, Priority.ALWAYS);
		h4.setSpacing(10);
		
		VBox v = new VBox(h1,h2,h3,h4);
		v.setSpacing(10);
		v.setPadding(new Insets(5,5,5,5));
		
		Scene sc = new Scene(v);
		sTeam.setScene(sc);
		sTeam.show();
		
		if(t == null) {
			sTeam.setTitle("Create Team");
			tTeam.requestFocus();
		}
		else {
			//Loads the current team details
			sTeam.setTitle("Edit Team");
			tTeam.setText(t.team);
			tDiv.setText(t.division);
			tRiders.requestFocus();
			if(t.excluded) cExclude.arm();
			if(t.start != null) tStart.setText(t.start.toString());
			if(t.finish != null) tFinish.setText(t.finish.toString());
			if(t.names.size()>0) {
				int pos = 0;
				for(String l : t.names) {
					if(pos == 0) tRiders.setText(l);
					else tRiders.setText(tRiders.getText() + "\n" + l);
					pos++;
				}
			}
			if(t.notes.size()>0) {
				int pos = 0;
				for(String l : t.notes) {
					if(pos == 0) tNotes.setText(l);
					else tNotes.setText(tNotes.getText() + "\n" + l);
					pos++;
				}
			}
		}
	}

	private static Team compileTeam() {
		Team r = new Team();
		r.team = tTeam.getText();
		r.division = tDiv.getText();
		r.start = new Time(tStart.getText());
		if(r.start.error != 0) r.start = null;
		r.finish = new Time(tFinish.getText());
		if(r.finish.error != 0) r.finish = null;
		String tmp = "";
		List<String> tmpRid = new ArrayList<String>();
		for(char c : tRiders.getText().toCharArray()) {
			if(c == '\n' || c == '\r') {
				tmpRid.add(tmp);
				tmp = "";
			} else tmp+=c;
		}
		tmpRid.add(tmp);
		tmp="";
		r.names = tmpRid;
		List<String> tmpNot = new ArrayList<String>();
		for(char c : tNotes.getText().toCharArray()) {
			if(c == '\n' || c == '\r') {
				tmpNot.add(tmp);
				tmp = "";
			} else tmp+=c;
		}
		tmpNot.add(tmp);
		r.notes = tmpNot;
		r.excluded = cExclude.isArmed();
		while(r.names.contains("")) r.names.contains("");
		return r;
	}
	
	private static boolean save() {
		lError.setText("");
		if(tTeam.getText().contentEquals("")) {
			lError.setText("Must include a Team Identifier");
			return false;
		}
		//If it's "making a team"
		if(team == null) {
			Team existingTeam = paceManager.getTeam(tTeam.getText());
			if(existingTeam != null) {
				//Confirms if the user would want to override the details
				Alert alert = new Alert(AlertType.NONE);
				alert.setTitle("Team ID Taken");
				alert.setHeaderText("Would you like to overwrite this team?");
				alert.setContentText("Overwriting will permenantly delete the old values applied to the team");
				ButtonType bOverwrite = new ButtonType("Overwrite");
				ButtonType bCancel = new ButtonType("Cancel");
				alert.getButtonTypes().addAll(bOverwrite,bCancel);
				sTeam.setAlwaysOnTop(false);
				Optional<ButtonType> result = alert.showAndWait();
				sTeam.setAlwaysOnTop(true);
				if(result.get() == bCancel) {
					return false;
				} else if (result.get() == bOverwrite) {
					paceManager.teams.set(paceManager.teams.indexOf(paceManager.getTeam(tTeam.getText())), compileTeam());
				}
			} else paceManager.teams.add(compileTeam());
		} else {
			paceManager.teams.set(paceManager.teams.indexOf(paceManager.getTeam(tTeam.getText())), compileTeam());
		}
		fxMain.updateTable();
		return true;
	}
}
