package application;

import java.util.ArrayList;
import java.util.List;

import classes.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class fxScores {
	
	public static Stage sScores;
	public static List<TableView<Team>> tables = new ArrayList<TableView<Team>>();
	private static TabPane tabPane;
	
	public static void open() {
		//Close any currently opened stages
		if(sScores != null) sScores.close();
		sScores = new Stage();
		sScores.setTitle("Score Leaderboard");
		//Dynamic Custom Resizing
		sScores.widthProperty().addListener((obs) -> {
			resizeWindow();
		});
		sScores.heightProperty().addListener((obs) -> {
			resizeWindow();
		});
		
		//UI Layout
		/*
		 * Menu Bar: includes Print, Reload, Etc. (or buttons) also has ones to open the goals
		 * Then tabs using javafx tabs (1 for each division)
		 */
		updateTabs();
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void updateTabs() {
		tabPane = new TabPane();
		tables = new ArrayList<TableView<Team>>();
		if(paceManager.goals.size() > 0) for(Goal g : paceManager.goals) {
			//Creates the Tab Page
			Tab tab = new Tab(g.division);
			
			TableView<Team> table = new TableView<Team>();
			table.setEditable(false);
			
			
			TableColumn cTeamName = new TableColumn("Team");
			cTeamName.setCellValueFactory(new PropertyValueFactory<Team,String>("team"));
			
			//TableColumn cDivision = new TableColumn("Division");
			//cDivision.setCellValueFactory(new PropertyValueFactory<Team,String>("division"));
			
			TableColumn cNames = new TableColumn("Riders");
			cNames.setEditable(false);
			cNames.setCellValueFactory(new PropertyValueFactory<Team,String>("names"));
			
			TableColumn cElapsed = new TableColumn("Elapsed Time");
			cElapsed.setCellValueFactory(new PropertyValueFactory<Team,String>("elapsedFXM"));

			TableColumn cNotes = new TableColumn("Notes");
			cNotes.setCellValueFactory(new PropertyValueFactory<Team,String>("notesDisplay"));
			
			table.getColumns().addAll(cTeamName,cNames,cElapsed,cNotes);
			
			
		}
	}
	
	private static void updateTables() {
		for(TableView<Team> table : tables) {
			table.getItems().clear();
			//table.getItems().addAll(paceManager.getTeams(division)) FIND A WAY TO GET DIVISION
			table.sort();
		}
	}
	
	private static void resizeWindow() {
		
	}
}
