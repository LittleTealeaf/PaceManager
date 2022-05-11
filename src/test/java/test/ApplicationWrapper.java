package test;

import javafx.scene.input.KeyCode;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.data.EventTime;

/**
 * Wraps steps used for different tests
 */
public class ApplicationWrapper extends UserInterfaceWrapper implements Randomizer {

    protected void actionOpenCreatePace() {
        clickOn(Identity.LAUNCHER_BUTTON_NEW);
    }

    protected void actionCreateNewPace() {
        press(KeyCode.CONTROL);
        actionOpenCreatePace();
        release(KeyCode.CONTROL);
    }

    protected void actionCreateNewPace(String name) {
        actionOpenCreatePace();
        writeInto(Identity.DIALOG_CREATE_PACE_FIELD_NAME, name);
        clickOn(Identity.DIALOG_CREATE_PACE_BUTTON_CREATE);
    }
    protected void actionDialogEditTeamAddRider() {
        actionDialogEditTeamAddRider(randomFullName());
    }

    protected void actionDialogEditTeamAddRider(String name) {
        int i = getIdentityCount(Identity.DIALOG_EDIT_TEAM_INPUT_RIDER_NAME);
        clickOn(Identity.DIALOG_EDIT_TEAM_BUTTON_ADD_RIDER);
        writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_RIDER_NAME, i, name);
    }

    protected void actionAddRandomTeam() {
        clickOn(Identity.APP_TAB_TEAMS,Identity.APP_TAB_TEAMS_BUTTON_ADD_TEAM);
        writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_NAME,randomName());
        int count = RANDOM.nextInt(1,5);
        for(int i = 0; i < count; i++) {
            actionDialogEditTeamAddRider();
        }
        if(RANDOM.nextBoolean()) {
            long time = Math.abs(RANDOM.nextLong() / 2);
            writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_TIME_START,new EventTime(time).toString());
            if(RANDOM.nextBoolean()) {
                writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_TIME_END,new EventTime(RANDOM.nextLong(time,Long.MAX_VALUE)).toString());
            }
        }
        clickOn(Identity.DIALOG_EDIT_TEAM_BUTTON_SAVE);
    }
}
