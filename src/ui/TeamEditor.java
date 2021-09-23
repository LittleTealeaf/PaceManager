package ui;

import data.Team;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TeamEditor {

    private static Stage openedStage;

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
        stage.setTitle(team.getTeamNumber() != null && !team.getTeamNumber().equals("") ? "Editing Team " + team
                .getTeamNumber() : "Creating New Team");

        teamNumber = new TextField();
        riders = new TextArea();
        notes = new TextArea();
        startTime = new TimeInput();
        endTime = new TimeInput();
        divisionSelector = new DivisionSelector();
        excluded = new CheckBox("Excluded");


        stage.setScene(generateScene());
        updateElements();
        open();
    }

    private Scene generateScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        GridPane center = new GridPane();
        center.setHgap(7);
        center.setVgap(7);
        borderPane.setCenter(center);

        center.add(new Label("Team Number"),0,0);
        center.add(teamNumber,1,0);

        center.add(new Label("Riders"),0,1,2,1);
        center.add(riders,0,2,2,2);

        center.add(new Label("Division"),2,0);
        center.add(divisionSelector,3,0);

        center.add(new Label("Start Time"),2,1);
        center.add(startTime,3,1);

        center.add(new Label("End Time"),2,2);
        center.add(endTime,3,2);

        return new Scene(borderPane);
    }

    public void updateTeam() {
        team.setTeamNumber(teamNumber.getText());
        team.setNotes(notes.getText());
        team.setStartTime(startTime.getTime());
        team.setEndTime(endTime.getTime());
        team.setDivision(divisionSelector.getDivision());
        //TODO cleanse data in separate function
        team.setRiders(riders.getText().replace(',','\n').split("\n"));
        team.setExcluded(excluded.isSelected());
    }

    public void updateElements() {
        teamNumber.setText(team.getTeamNumber());
        notes.setText(team.getNotes());
        startTime.setTime(team.getStartTime());
        endTime.setTime(team.getEndTime());
        divisionSelector.setDivision(team.getDivision());
        excluded.setSelected(team.isExcluded());

        if(team.getRiders() != null) {
            StringBuilder builder = new StringBuilder();
            int riderLength = team.getRiders().length;
            for (int i = 0; i < riderLength; i++) {
                builder.append(team.getRiders()[i]);
                if (i < riderLength - 1) {
                    builder.append("\n");
                }
            }
            riders.setText(builder.toString());
        } else {
            riders.setText("");
        }
    }

    public void open() {
        //if singular is enabled...
        if(openedStage != null && openedStage.isShowing()) {
            openedStage.close();
        }

        stage.show();
    }

    public Team getTeam() {
        return team;
    }

    public void close() {
        stage.close();
        if(openedStage == stage) {
            openedStage = null;
        }
    }

}
