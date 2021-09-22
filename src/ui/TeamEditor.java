package ui;

import data.Team;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TeamEditor {
    private final Stage stage;
    private final Team team;

    private final TextField teamNumber;
    private final TextArea riders;
    private final TextArea notes;
    private final TimeInput startTime;
    private final TimeInput endTime;
    private final DivisionSelector divisionSelector;
    private final CheckBox excluded;

    public TeamEditor() {
        this(new Team());
    }

    public TeamEditor(Team team) {
        this.team = team;
        stage = new Stage();
        stage.setTitle(team.getTeamNumber() != null && team.getTeamNumber() != "" ? "Editing Team " + team
                .getTeamNumber() : "Creating New Team");

        //Creating the elements
        teamNumber = new TextField();
        riders = new TextArea();
        notes = new TextArea();
        startTime = new TimeInput();
        endTime = new TimeInput();
        divisionSelector = new DivisionSelector();
        excluded = new CheckBox("Excluded");


        GridPane gridPane = new GridPane();
        stage.setScene(new Scene(gridPane));

        stage.show();
    }


    public void updateTeam() {
        team.setTeamNumber(teamNumber.getText());
        team.setNotes(notes.getText());
        team.setStartTime(startTime.getTime());
        team.setEndTime(endTime.getTime());
        team.setDivision(divisionSelector.getDivision());
        team.setRiders(riders.getText().split("\n"));
        team.setExcluded(excluded.isSelected());
    }

    public void updateElements() {
        teamNumber.setText(team.getTeamNumber());
        notes.setText(team.getNotes());
        startTime.setTime(team.getStartTime());
        endTime.setTime(team.getEndTime());
        divisionSelector.setDivision(team.getDivision());
        excluded.setSelected(team.isExcluded());

        StringBuilder builder = new StringBuilder();
        int riderLength = team.getRiders().length;
        for (int i = 0; i < riderLength; i++) {
            builder.append(team.getRiders()[i]);
            if (i < riderLength - 1) {
                builder.append("\n");
            }
        }
        riders.setText(builder.toString());
    }

    public Team getTeam() {
        return team;
    }

}
