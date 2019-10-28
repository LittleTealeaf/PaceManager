package classes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class util {
	/**
	 * Turns int into readable value for place
	 * @param Position (1st is 1, 2nd is 2, 3rd is 3)
	 * @return 1st, 2nd, 3rd, etc (as a string)
	 */
	public static String readablePlace(int position) {
		if(position == 1) return "1st";
		else if(position == 2) return "2nd";
		else if(position == 3) return "3rd";
		else if(position >= 4 && position <= 20) return position + "th";
		else return position + "";
	}
	
	/**
	 * Returns a table with teams and columns
	 * @param teams Teams you want
	 * @param columns Valid columns include anything in {@link Team} that has a get(obj) function. Make sure the first letter is lowercase
	 * @return Table of everything!
	 */
	public static TableView<Team> teamTable(List<Team> teams, String[] columns) {
		List<String> cols = new ArrayList<String>();
		for(String s : columns) cols.add(s);
		return teamTable(teams,cols);
	}
	/**
	 * Returns a table with teams and columns
	 * @param teams Teams you want
	 * @param columns Valid columns include anything in {@link Team} that has a get(obj) function. Make sure the first letter is lowercase
	 * @return Table of everything!
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static TableView<Team> teamTable(List<Team> teams, List<String> columns) {
		TableView<Team> r = new TableView<Team>();
		
		//Adds columns
		for(String s : columns) {
			TableColumn col = new TableColumn();
			switch(s) {
			case "team": col.setText("Team"); break;
			case "division": col.setText("Division"); break;
			case "names": col.setText("Names"); break;
			case "startFXM": col.setText("Start"); break;
			case "finishFXM": col.setText("Finish"); break;
			case "elapsedFXM": col.setText("Elapsed"); break;
			case "notesDisplay": col.setText("Notes"); break;
			case "positionInDivision": col.setText("Place"); break;
			default: break;
			}
			col.setCellValueFactory(new PropertyValueFactory<Team,String>(s));
			r.getColumns().add(col);
		}
		r.getItems().setAll(teams);
		
		
		return r;
	}
}
