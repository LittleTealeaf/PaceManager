package ui;

import app.App;
import app.Resources;
import app.Updatable;
import data.Team;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0
 */
public class TeamEditor extends Stage implements Updatable {

    private static final List<TeamEditor> EDITORS = new ArrayList<>();

    private final Team team;

    private final TextField nodeTeamName;
    private final TextArea nodeRiders;
    private final TextArea nodeNotes;
    private final TimeInput nodeStartTime;
    private final TimeInput nodeEndTime;
    private final DivisionSelector nodeDivision;
    private final CheckBox nodeExcluded;

    public TeamEditor () {
        this(App.openedPace.newTeam());
    }

    public TeamEditor (Team team) {
        super();

        if (!App.settings.isMultipleTeamsEditing()) {
            closeAll();
        }

        App.addUpdatable(this);

        this.team = team;

        nodeTeamName = new TextField();
        nodeRiders = new TextArea();
        nodeNotes = new TextArea();
        nodeStartTime = new TimeInput();
        nodeEndTime = new TimeInput();
        nodeDivision = new DivisionSelector();
        nodeExcluded = new CheckBox("Excluded");

        EDITORS.add(this);
        setOnCloseRequest(e -> EDITORS.remove(this));
        getIcons().add(Resources.APPLICATION_ICON);
        setAlwaysOnTop(true);
        setScene(generateScene());
        update();
        show();
    }

    public static void closeAll () {
        while (EDITORS.size() > 0) {
            TeamEditor editor = EDITORS.get(0);
            EDITORS.remove(editor);
            editor.close();
        }
    }

    /**
     * @return
     */
    private Scene generateScene () {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        GridPane center = new GridPane();
        center.setHgap(7);
        center.setVgap(7);
        borderPane.setCenter(center);

        center.add(new Label("Team Number"), 0, 0);
        center.add(nodeTeamName, 1, 0);

        center.add(new Label("Riders"), 0, 1, 2, 1);
        center.add(nodeRiders, 0, 2, 2, 2);

        GridPane centerColumn = new GridPane();
        centerColumn.setVgap(6);
        centerColumn.setHgap(6);
        center.add(centerColumn, 2, 0, 1, 3);

        centerColumn.add(new Label("Division"), 0, 0);
        centerColumn.add(nodeDivision, 1, 0);

        centerColumn.add(new Label("Start Time"), 0, 1);
        centerColumn.add(nodeStartTime, 1, 1);

        centerColumn.add(new Label("End Time"), 0, 2);
        centerColumn.add(nodeEndTime, 1, 2);

        centerColumn.add(nodeExcluded, 0, 3, 2, 1);

        center.add(nodeNotes, 2, 3, 2, 1);

        borderPane.setBottom(generateBottomPane());

        return new Scene(borderPane);
    }

    public void update () {
        //update name
        setTitle(team.getTeamName() != null && !team.getTeamName().equals("") ? "Editing Team " + team.getTeamName() : "Creating New Team");
        updateNodes();
    }

    /**
     * @return
     */
    private BorderPane generateBottomPane () {
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

    private void updateNodes () {
        nodeTeamName.setText(team.getTeamName());
        nodeNotes.setText(team.getNotes());
        nodeStartTime.setTime(team.getStartTime());
        nodeEndTime.setTime(team.getEndTime());
        nodeDivision.setDivision(team.getDivision());
        nodeExcluded.setSelected(team.isExcluded());

        if (team.getRiders() != null) {
            StringBuilder builder = new StringBuilder();
            int riderLength = team.getRiders().length;
            for (int i = 0; i < riderLength; i++) {
                builder.append(team.getRiders()[i]);
                if (i < riderLength - 1) {
                    builder.append("\n");
                }
            }
            nodeRiders.setText(builder.toString());
        } else {
            nodeRiders.setText("");
        }
    }

    private void updateTeam () {

        team.setTeamName(nodeTeamName.getText());
        team.setNotes(nodeNotes.getText());
        team.setStartTime(nodeStartTime.getTime());
        team.setEndTime(nodeEndTime.getTime());
        team.setDivision(nodeDivision.getDivision());
        //TODO cleanse data in separate function
        team.setRiders(parseRiders());
        team.setExcluded(nodeExcluded.isSelected());
        App.update();
    }

    /**
     * @return
     */
    public String[] parseRiders () {
        String[] raw = nodeRiders.getText().replace(',', '\n').split("\n");
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

    public Team getTeam () {
        return team;
    }

}
