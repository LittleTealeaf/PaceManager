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

    private TextField teamNumber;
    private TextArea riders;
    private TextArea notes;
    private TimeInput startTime;
    private TimeInput endTime;
    private DivisionSelector divisionSelector;
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

        GridPane gridPane = new GridPane();

        stage.setScene(new Scene(gridPane));

        stage.show();
    }


    public void updateTeam() {

    }

    public Team getTeam() {
        return team;
    }

}
