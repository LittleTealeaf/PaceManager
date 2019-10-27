package application;

import java.util.ArrayList;
import java.util.List;

import classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
		
		Region regTop1 = new Region();
		HBox.setHgrow(regTop1, Priority.ALWAYS);
		
		Button bPrint = new Button("Print");
		bPrint.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fxPrint.open();
			}
		});
		
		HBox hTop = new HBox(bGoals);
		
		
		
		Region regBottom1 = new Region();
		HBox.setHgrow(regBottom1, Priority.ALWAYS);
		
		Button bClose = new Button("Close");
		
		bClose.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				sScores.close();
			}
		});
		
		HBox hBottom = new HBox(regBottom1,bClose);
		
		
		VBox vb = new VBox(hTop,tabPane,hBottom);
		
		Scene sc = new Scene(vb,800,400);
		sScores.setScene(sc);
		sScores.show();
		
	}
	
	public static void updateTabs() {
		if(sScores != null) {
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
	}
	
	public static void updateTables() {
		if(scoreTabs != null && scoreTabs.size() > 0) for(ScoreTab t : scoreTabs) t.updateTable(sScores.getWidth());
	}
	
	private static void resizeWindow() {
		updateTables();
	}
}