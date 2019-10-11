package application;

import classes.*;
import javafx.application.Application;
import javafx.scene.Scene;  
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.control.*; 
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
				//TODO check for if saved
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
					int row = pos.getRow();
					System.out.println(row);
					int col = pos.getColumn();
					if(col == 4) {
						//TODO opens a window for just the notes
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
		cNotes.setEditable(false);
		
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
		for(Team a : paceManager.teams) {
			table.getItems().add(a);
		}
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
	
}
