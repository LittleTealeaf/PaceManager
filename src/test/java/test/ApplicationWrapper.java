package test;

import javafx.scene.input.KeyCode;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.data.EventTime;
import org.tealeaf.pacemanager.data.Team;

/**
 * Wraps steps used for different tests
 */
public class ApplicationWrapper extends UserInterfaceWrapper implements Randomizer {

    protected void actionCreateNewPace() {
        press(KeyCode.CONTROL);
        actionOpenCreatePace();
        release(KeyCode.CONTROL);
    }

    protected void actionOpenCreatePace() {
        clickOn(Identity.LAUNCHER_BUTTON_NEW);
    }

    protected void actionCreateNewPace(String name) {
        actionOpenCreatePace();
        writeInto(Identity.DIALOG_CREATE_PACE_FIELD_NAME, name);
        clickOn(Identity.DIALOG_CREATE_PACE_BUTTON_CREATE);
    }

    protected void actionAddRandomTeam() {
        actionAddTeam(randomTeam());
    }

    protected void actionAddTeam(Team team) {
        clickOn(Identity.APP_TAB_TEAMS, Identity.APP_TAB_TEAMS_BUTTON_ADD_TEAM);
        writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_NAME, team.getName());
        team.getRiders().forEach(this::actionDialogEditTeamAddRider);
        if(team.getStartTime() != null) {
            writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_TIME_START, team.getStartTime().toString());
        }
        if(team.getEndTime() != null) {
            writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_TIME_END, team.getStartTime().toString());
        }
        clickOn(Identity.DIALOG_EDIT_TEAM_BUTTON_SAVE);
    }

    protected void actionDialogEditTeamAddRider() {
        actionDialogEditTeamAddRider(randomFullName());
    }

    protected void actionDialogEditTeamAddRider(String name) {
        int i = getIdentityCount(Identity.DIALOG_EDIT_TEAM_INPUT_RIDER_NAME);
        clickOn(Identity.DIALOG_EDIT_TEAM_BUTTON_ADD_RIDER);
        writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_RIDER_NAME, i, name);
    }
}
