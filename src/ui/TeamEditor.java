package ui;

import data.Team;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TeamEditor {
    private Stage stage;
    private final Team team;

    private final TextField teamNumber;
    private final TextArea riders;
    private final TextArea notes;
    private final TimeInput startTime;
    private final TimeInput endTime;
    private final DivisionSelector divisionSelector;
    //Division Selection (hooks into the pace?)

    /*
    private final UUID uuid;
	private String teamNumber;
	private String[] riders;
	private String notes;
	private Time startTime;
	private Time endTime;
	private boolean excluded = false;
	private transient Division division;
	private UUID divisionUUID;
     */

    public TeamEditor() {
        this(new Team());
    }

    public TeamEditor(Team team) {
        this.team = team;
        stage = new Stage();
        stage.setTitle(team.getTeamNumber() != null && team.getTeamNumber() != "" ? "Editing Team " + team.getTeamNumber() : "Creating New Team");

        //Creating the elements
        teamNumber = new TextField();
        riders = new TextArea();
        notes = new TextArea();
        startTime = new TimeInput();
        endTime = new TimeInput();
        divisionSelector = new DivisionSelector();


        GridPane gridPane = new GridPane();
        stage.setScene(new Scene(gridPane));

        stage.show();
    }


    public void updateTeam() {

    }

    public void updateElements() {
        teamNumber.setText(team.getTeamNumber());

        StringBuilder builder = new StringBuilder();
        int riderLength = team.getRiders().length;
        for(int i = 0; i < riderLength; i++) {
            builder.append(team.getRiders()[i]);
            if(i < riderLength - 1) {
                builder.append("\n");
            }
        }
        riders.setText(builder.toString());

        notes.setText(team.getNotes());
        startTime.setTime(team.getStartTime());
        endTime.setTime(team.getEndTime());
        divisionSelector.setDivision(team.getDivision());
    }

    public Team getTeam() {
        return team;
    }

}
