package application;

import java.util.ArrayList;
import java.util.List;
import classes.*;
import javafx.application.Application;
import javafx.scene.Scene;  
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.control.*; 
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/*
 * Notes only displays the first line of the notes
 * windows:
 * fxMainNotes
 * fxEditTeam
 */

public class fxMain extends Application {
	
	private static TableView<Team> table;

	public static void open(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "exports" })
	@Override
	public void start(Stage sMain) {
		sMain.setTitle("Pace Manager " + paceManager.version);
		
		//Menus
		Menu m1 = new Menu("File");
		MenuItem miOpen = new MenuItem("Open");
		MenuItem miSave = new MenuItem("Save");
		MenuItem miSaveAs = new MenuItem("Save As");
		MenuItem miClose = new MenuItem("Close");
		MenuItem miExit = new MenuItem("Exit");
		miExit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.exit(0);
			}
		});
		
		m1.getItems().add(miOpen);
		m1.getItems().add(miSave);
		m1.getItems().add(miSaveAs);
		m1.getItems().add(miClose);
		m1.getItems().add(miExit);
		
		MenuBar mb = new MenuBar();
		mb.getMenus().add(m1);
		
		//Table 
		table = new TableView<Team>();
		table.setEditable(false);
		/*
		 * https://stackoverflow.com/a/32442449
		 */
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
					//int row = pos.getRow();
					//System.out.println(row);
					int col = pos.getColumn();
					Team sel = table.getSelectionModel().getSelectedItem();
					if(col == 5) {
						openNotes(sel, click.getScreenX(), click.getScreenY());
					} else {
						//TODO opens a window for everything
					}
				}
			}
		});

		
		TableColumn cTeamName = new TableColumn("Team");
		cTeamName.setCellValueFactory(new PropertyValueFactory<Team,String>("team"));
		cTeamName.setMinWidth(50);
		
		
		TableColumn cDivision = new TableColumn("Division");
		cDivision.setCellValueFactory(new PropertyValueFactory<Team,String>("division"));
		cDivision.setMinWidth(70);
		
		TableColumn cNames = new TableColumn("Riders");
		cNames.setEditable(false);
		cNames.setCellValueFactory(new PropertyValueFactory<Team,String>("names"));
		
		TableColumn cTime = new TableColumn("Times");
		TableColumn cTStart = new TableColumn("Start");
		cTStart.setCellValueFactory(new PropertyValueFactory<Team,String>("startFXM"));
		cTStart.setMinWidth(80);
		TableColumn cTFinish = new TableColumn("Finish");
		cTFinish.setMinWidth(80);
		cTFinish.setCellValueFactory(new PropertyValueFactory<Team,String>("finishFXM"));
		
		cTime.getColumns().addAll(cTStart,cTFinish);
		TableColumn cNotes = new TableColumn("Notes");
		cNotes.setCellValueFactory(new PropertyValueFactory<Team,String>("notesDisplay"));
		
		table.getColumns().addAll(cTeamName,cDivision,cNames,cTime,cNotes);
		
		table.prefHeightProperty().bind(sMain.heightProperty());
        table.prefWidthProperty().bind(sMain.widthProperty());
		
		
		VBox vb = new VBox(mb);
		vb.getChildren().add(table);
		
		Scene sc = new Scene(vb, 500, 300);
		sMain.setMaximized(true);
		sMain.setScene(sc); 
		sMain.show();
		
		updateTable();
	}
	
	public static void updateTable() {
		table.getItems().clear();
		for(Team a : paceManager.teams) table.getItems().add(a);
		updateWidths();
	}
	
	private static void updateWidths() {
		/*
		 * Team Name = 50
		 * Division = 70
		 * Names = (50% remaining)
		 * 
		 * Times = 80
		 */
	}
	
	public static void closeSubWindows() {
		if(mNotes.isShowing()) mNotes.close();
	}
	
	private static Stage mNotes;
	private static TextArea nText;
	private static Team nTeam;
	
	private static void openNotes(Team t, double x, double y) {
		nTeam = t;
		if(mNotes == null) {
			mNotes = new Stage();
			mNotes.setAlwaysOnTop(true);
			mNotes.initStyle(StageStyle.UNDECORATED);
			mNotes.setResizable(true);
			mNotes.focusedProperty().addListener((obs,wasFocused,isNowFocused) -> {
				if(!isNowFocused) {
					mNotes.close();
				}
			});
			mNotes.setWidth(300);
			mNotes.setHeight(300);
		}
		mNotes.setX(x);
		mNotes.setY(y);
		
		nText = new TextArea();
		nText.textProperty().addListener((observable, oldValue, newValue) -> {
			List<String> notes = new ArrayList<String>();
			String tmp = "";
			for(char c : nText.getText().toCharArray()) {
				if(c == '\n') {
					notes.add(tmp);
					tmp = "";
				} else tmp+=c;
			}
			notes.add(tmp);
			if(notes.get(0) == "") notes.remove(0);
			paceManager.teams.get(paceManager.teams.indexOf(nTeam)).notes = notes;
		});
		nText.prefWidthProperty().bind(mNotes.maxWidthProperty());
		nText.prefHeightProperty().bind(mNotes.heightProperty());
		
		int pos = 0;
		for(String l : t.notes) {
			if(pos == 0) nText.setText(l);
			else nText.setText(nText.getText() + "\n" + l);
		}
		
		Button nbClose = new Button();
		nbClose.setText("Close");
		nbClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent click) {
				mNotes.close();
				updateTable();
			}
		});
		
		VBox vb = new VBox();
		vb.getChildren().addAll(nText, nbClose);
		Scene sc = new Scene(vb,mNotes.getWidth(),mNotes.getHeight());
		sc.addEventHandler(KeyEvent.ANY, keyEvent -> {
			if(keyEvent.getCode() == KeyCode.ESCAPE) {
				mNotes.close();
				updateTable();
			}
		});
		mNotes.setScene(sc);
		mNotes.show();
	}
	
}
