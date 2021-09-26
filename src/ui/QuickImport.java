package ui;

import app.App;
import data.Team;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class QuickImport extends Stage {

    private static QuickImport instance;


    private TextField teamId;
    private TimeInput timeInput;
    private Team team;
    private ComboBox<String> comboBox;

    public QuickImport() {
        super();

        if (instance != null) {
            instance.close();
        }
        instance = this;

        generateInterface();
        show();
    }

    private void generateInterface() {
        BorderPane borderPane = new BorderPane();


        timeInput = new TimeInput();

        GridPane center = new GridPane();
        teamId = new TextField();
        teamId.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER, TAB -> {
                    timeInput.requestFocus();
                }
            }
        });
        teamId.focusedProperty().addListener((e, o, n) -> {
            if (!e.getValue().booleanValue()) {
                loadTeam();
            }
        });

        timeInput.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER, TAB -> {
                    teamId.requestFocus();
                    saveTeam();
                    teamId.setText("");
                    timeInput.setTime(null);
                }
            }
        });

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Start Time", "End Time");
        comboBox.getSelectionModel().select(0);

        center.add(teamId, 0, 0);
        center.add(comboBox, 1, 0);
        center.add(timeInput, 2, 0);

        borderPane.setCenter(center);

        setScene(new Scene(borderPane));
    }

    public void loadTeam() {
        String value = teamId.getText().toUpperCase();
        team = null;
        for (Team team : App.openedPace.getTeams()) {
            if (team.getTeamNumber().toUpperCase().equals(value)) {
                this.team = team;
                break;
            }
        }
        timeInput.setTime(team == null ? null : comboBox.getSelectionModel()
                                                        .getSelectedIndex() == 0 ? team.getStartTime() : team.getEndTime());
    }

    public void saveTeam() {
        if (team != null) {
            if (comboBox.getSelectionModel().getSelectedIndex() == 0) {
                team.setStartTime(timeInput.getTime());
            } else {
                team.setEndTime(timeInput.getTime());
            }
        } else {
            team = App.openedPace.newTeam();
            team.setTeamNumber(teamId.getText());
            saveTeam();
        }
        App.update();
    }

}
