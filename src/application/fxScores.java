package application;

import java.util.ArrayList;
import java.util.List;

import classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class fxScores {
	
	public static Stage sScores;
	private static TabPane tabPane;
	
	private static List<ScoreTab> scoreTabs;
	
	public static void open() {
		//Close any currently opened stages
		if(sScores != null) sScores.close();
		sScores = new Stage();
		sScores.setTitle("Score Leaderboard");
		//Dynamic Custom Resizing
		sScores.widthProperty().addListener((obs,newv,oldv) -> {
			System.out.println("resize");
			resizeWindow();
		});
		sScores.heightProperty().addListener((obs,newv,oldv) -> {
			resizeWindow();
		});
		
		//UI Layout
		/*
		 * Menu Bar: includes Print, Reload, Etc. (or buttons) also has ones to open the goals
		 * Then tabs using javafx tabs (1 for each division)
		 */
		updateTabs();

		Button bGoals = new Button("Goals");
		bGoals.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fxGoals.open();
			}
		});
		HBox hTop = new HBox(bGoals);
		
		Button bClose = new Button("Close");
		bClose.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				sScores.close();
			}
		});
		HBox hBottom = new HBox(bClose);
		
		VBox vb = new VBox(hTop,tabPane,hBottom);
		
		Scene sc = new Scene(vb,500,300);
		sScores.setScene(sc);
		sScores.show();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void updateTabs() {
		tabPane = new TabPane();
		scoreTabs = new ArrayList<ScoreTab>();
		
		if(paceManager.goals.size() > 0) {
			for(Goal g : paceManager.goals) {
				scoreTabs.add(new ScoreTab(g));
			}
			updateTables();
			for(ScoreTab t : scoreTabs) {
				tabPane.getTabs().add(t.getTab());
			}
			
		}
	}
	
	public static void updateTables() {
		for(ScoreTab t : scoreTabs) t.updateTable(sScores.getWidth());
	}
	
	private static void resizeWindow() {
		updateTables();
	}
}
