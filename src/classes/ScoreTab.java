package classes;

import application.paceManager;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ScoreTab {
	public TableView<Team> table;
	public Label label;
	public Goal goal;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ScoreTab(Goal g) {
		goal = g;
		
		table = new TableView<Team>();
		table.setEditable(false);
				
		TableColumn cTeamName = new TableColumn("Team");
		cTeamName.setEditable(false);
		cTeamName.setSortable(false);
		cTeamName.setReorderable(false);
		cTeamName.setCellValueFactory(new PropertyValueFactory<Team,String>("team"));
		
		TableColumn cNames = new TableColumn("Riders");
		cNames.setEditable(false);
		cNames.setSortable(false);
		cNames.setReorderable(false);
		cNames.setCellValueFactory(new PropertyValueFactory<Team,String>("names"));
		
		TableColumn cElapsed = new TableColumn("Elapsed Time");
		cElapsed.setEditable(false);
		cElapsed.setSortable(true);
		cElapsed.setReorderable(false);
		cElapsed.setCellValueFactory(new PropertyValueFactory<Team,String>("elapsedFXM"));
		
		TableColumn cDifference = new TableColumn("Difference");
		cDifference.setEditable(false);
		cDifference.setSortable(true);
		cDifference.setReorderable(false);
		cDifference.setCellValueFactory(new PropertyValueFactory<Team,String>("difference"));

		TableColumn cNotes = new TableColumn("Notes");
		cNotes.setEditable(false);
		cNotes.setSortable(false);
		cNotes.setReorderable(false);
		cNotes.setCellValueFactory(new PropertyValueFactory<Team,String>("notesDisplay"));
		
		table.getColumns().addAll(cTeamName,cNames,cElapsed,cDifference,cNotes);
		table.getSortOrder().add(cDifference);
		
		label = new Label("Division Optimum Time: " + goal.getGoalTime().toString(true));
	}
	
	@SuppressWarnings("exports")
	public Tab getTab() {
		Tab tab = new Tab(goal.division);
		tab.setClosable(false);
		tab.setContent(new VBox(label,table));
		return tab;
	}
	
	public void updateTable() {
		updateTable(0);
	}
	
	public void updateTable(double width) {
		label = new Label("Division Optimum Time: " + goal.getGoalTime().toString(true));
		table.getItems().clear();
		for(Team t : paceManager.teams) {
			if(t.division.contentEquals(goal.division) && t.elapsed() != null) table.getItems().add(t);
		}
		table.autosize();
		table.sort();
		resizeTable(width);
	}
	
	public void resizeTable(double width) {
		if(width == 0) return;
		final double wTeam = 50;
		final double wTime = 80;
		table.getColumns().get(0).setPrefWidth(wTeam);
		table.getColumns().get(2).setPrefWidth(wTime);
		table.getColumns().get(3).setPrefWidth(wTime);
		
		double remSpace = width - (wTeam + wTime * 2);
		table.getColumns().get(1).setPrefWidth(remSpace * 0.6);
		table.getColumns().get(4).setPrefWidth(remSpace * 0.4 - 15);
	}
}
