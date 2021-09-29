package ui;

import app.App;
import data.Team;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
//TODO update javadocs

/**
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class TeamEditor {

    private static Stage openedStage;
    private final Stage stage;
    private final Team team;

    private final TextField elementTeamIdentifier;
    private final TextArea elementRiders;
    private final TextArea elementNotes;
    private final TimeInput elementStartTime;
    private final TimeInput elementEndTime;
    private final DivisionSelector elementDivision;
    private final CheckBox elementExcluded;
    private boolean isNewTeam;

    /**
     * @since 1.0.0
     */
    public TeamEditor() {
        this(new Team());
        isNewTeam = true;
    }

    /**
     * @param team
     * @since 1.0.0
     */
    public TeamEditor(Team team) {
        this.team = team;
        isNewTeam = false;
        stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setTitle(team.getTeamNumber() != null && !team.getTeamNumber().equals("") ? "Editing Team " + team
                .getTeamNumber() : "Creating New Team");

        elementTeamIdentifier = new TextField();
        elementTeamIdentifier.textProperty().addListener((e, o, n) -> stage.setTitle("Editing Team " + e.getValue()));

        elementRiders = new TextArea();
        elementNotes = new TextArea();
        elementStartTime = new TimeInput();
        elementEndTime = new TimeInput();
        elementDivision = new DivisionSelector();
        elementExcluded = new CheckBox("Excluded");


        stage.setScene(generateScene());
        updateElements();
        open();
    }

    /**
     * @return
     * @since 1.0.0
     */
    private BorderPane generateBottomPane() {
        BorderPane pane = new BorderPane();

        Button buttonClose = new Button("Close");
        buttonClose.setOnAction(e -> close());

        Button buttonSave = new Button("Save");
        buttonSave.setOnAction(e -> updateTeam());

        Button buttonSaveExit = new Button("Save and Exit");
        buttonSaveExit.setOnAction(e -> {
            updateTeam();
            close();
        });

        HBox leftBox = new HBox(buttonClose);
        leftBox.setSpacing(10);
        pane.setLeft(leftBox);

        HBox rightBox = new HBox(buttonSave, buttonSaveExit);
        rightBox.setSpacing(10);
        pane.setRight(rightBox);

        return pane;
    }

    /**
     * @return
     * @since 1.0.0
     */
    private Scene generateScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        GridPane center = new GridPane();
        center.setHgap(7);
        center.setVgap(7);
        borderPane.setCenter(center);

        center.add(new Label("Team Number"), 0, 0);
        center.add(elementTeamIdentifier, 1, 0);

        center.add(new Label("Riders"), 0, 1, 2, 1);
        center.add(elementRiders, 0, 2, 2, 2);

        GridPane centerColumn = new GridPane();
        centerColumn.setVgap(6);
        centerColumn.setHgap(6);
        center.add(centerColumn, 2, 0, 1, 3);

        centerColumn.add(new Label("Division"), 0, 0);
        centerColumn.add(elementDivision, 1, 0);

        centerColumn.add(new Label("Start Time"), 0, 1);
        centerColumn.add(elementStartTime, 1, 1);

        centerColumn.add(new Label("End Time"), 0, 2);
        centerColumn.add(elementEndTime, 1, 2);

        centerColumn.add(elementExcluded, 0, 3, 2, 1);

        center.add(elementNotes, 2, 3, 2, 1);


        borderPane.setBottom(generateBottomPane());

        return new Scene(borderPane);
    }

    /**
     * @since 1.0.0
     */
    public void updateTeam() {

        if(isNewTeam) {
            isNewTeam = false;
            App.openedPace.getTeams().add(team);
        }

        team.setTeamNumber(elementTeamIdentifier.getText());
        team.setNotes(elementNotes.getText());
        team.setStartTime(elementStartTime.getTime());
        team.setEndTime(elementEndTime.getTime());
        team.setDivision(elementDivision.getDivision());
        //TODO cleanse data in separate function
        team.setRiders(parseRiders());
        team.setExcluded(elementExcluded.isSelected());
        updateElements();
        App.update();
    }

    //TODO: Add this method to the "Team" class as a method to be called before saving or something

    /**
     * @return
     * @since 1.0.0
     */
    public String[] parseRiders() {
        String[] raw = elementRiders.getText().replace(',', '\n').split("\n");
        boolean[] delete = new boolean[raw.length];
        int delCount = 0;
        for (int i = 0; i < raw.length; i++) {
            while (raw[i].length() > 0 && raw[i].charAt(0) == ' ') {
                raw[i] = raw[i].substring(1);
            }
            while (raw[i].length() > 0 && raw[i].charAt(raw[i].length() - 1) == ' ') {
                raw[i] = raw[i].substring(0, raw[i].length() - 1);
            }
            if (raw[i].equals("")) {
                delete[i] = true;
                delCount++;
            }
        }
        String[] riders = new String[raw.length - delCount];
        int i = 0, j = 0;
        while (i < riders.length) {
            if (delete[j]) {
                j++;
            } else {
                riders[i] = raw[j];
                j++;
                i++;
            }
        }

        return riders;
    }

    /**
     * @since 1.0.0
     */
    public void updateElements() {
        elementTeamIdentifier.setText(team.getTeamNumber());
        elementNotes.setText(team.getNotes());
        elementStartTime.setTime(team.getStartTime());
        elementEndTime.setTime(team.getEndTime());
        elementDivision.setDivision(team.getDivision());
        elementExcluded.setSelected(team.isExcluded());

        if (team.getRiders() != null) {
            StringBuilder builder = new StringBuilder();
            int riderLength = team.getRiders().length;
            for (int i = 0; i < riderLength; i++) {
                builder.append(team.getRiders()[i]);
                if (i < riderLength - 1) {
                    builder.append("\n");
                }
            }
            elementRiders.setText(builder.toString());
        } else {
            elementRiders.setText("");
        }
    }

    /**
     * @since 1.0.0
     */
    public void open() {
        //if singular is enabled...
        if (openedStage != null && openedStage.isShowing()) {
            openedStage.close();
        }
        stage.show();
        stage.requestFocus();
        openedStage = stage;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @since 1.0.0
     */
    public void close() {
        stage.close();
        if (openedStage == stage) {
            openedStage = null;
        }
    }

}
