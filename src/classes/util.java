package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

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
	
	@SuppressWarnings("rawtypes")
	public static TableCell getTeamCell() {
		return new TableCell<Team, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if(item==null || empty) {
                    setGraphic(null);
                } else {
                    VBox vbox = new VBox();
                    List<String> textList = Arrays.asList(item.split(", "));
                    for(int i=0;i<textList.size();i++) {
                        Label lbl = new Label(textList.get(i));
                        vbox.getChildren().add(lbl);
                    }
                    setGraphic(vbox);
                }
            }
        };
	}
	
	/**
	 * Sorts teams by a certain method
	 * @param teams List of Teams to sort
	 * @param sortCol Valid Columns: team (team number), division (team division), startFXM (start time), finishFXM (end time), elapsedFXM (elapsed time) positionInDivision (team place)
	 * @return
	 */
	public static List<Team> sortTeams(List<Team> teams, String sortCol) {return sortTeams(teams,sortCol,false);}
	public static List<Team> sortTeamsReverse(List<Team> teams, String sortCol) { return sortTeams(teams,sortCol,true);}
	
	private static List<Team> sortTeams(List<Team> teams, String sortCol, boolean reverse) {
		List<Team> ret = new ArrayList<Team>();
		Collections.sort(teams, (a, b) -> {
			
			int r = 1;
			if(reverse) r = -1;
			
			switch(sortCol) {
			case "team": return a.team.compareTo(b.team) * r;
			case "division": return a.division.compareTo(b.division) * r;
			case "startFXM": return Float.compare(a.start.time, b.start.time) * r;
			case "finishFXM": return Float.compare(a.finish.time, b.finish.time) * r;
			case "elapsedFXM": return Float.compare(a.elapsed().time, b.elapsed().time) * r;
			case "positionInDivision": return a.getPositionInDivision().compareTo(b.getPositionInDivision()) * r;
			default: return 0;
			}
		});
		return ret;
	}
}
