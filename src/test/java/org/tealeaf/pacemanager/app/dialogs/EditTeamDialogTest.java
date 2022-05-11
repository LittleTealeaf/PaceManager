package org.tealeaf.pacemanager.app.dialogs;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.data.EventTime;
import test.ApplicationWrapper;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditTeamDialogTest extends ApplicationWrapper {

    @Test
    void testName() {

        String name = randomName();

        actionCreateNewPace();
        shortcutOpen();
        writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_NAME, name);
        shortcutReopen();
        assertEquals(name, get(TextField.class, Identity.DIALOG_EDIT_TEAM_INPUT_NAME).getText());
    }

    void shortcutOpen() {
        clickOn(Identity.APP_TAB_TEAMS, Identity.APP_TAB_TEAMS_BUTTON_ADD_TEAM);
    }

    void shortcutReopen() {
        shortcutReopen(0);
    }

    void shortcutReopen(int index) {
        clickOn(Identity.DIALOG_EDIT_TEAM_BUTTON_SAVE);
        doubleClickOn(Identity.TABLE_TEAMS_CELL_TEAM_NAME,index);
//        clickOn(Identity.APP_TAB_TEAMS_TABLE);
//        keystroke(KeyCode.HOME);
//        keystroke(KeyCode.ENTER);
//        clickOn(Identity.DIALOG_EDIT_TEAM_BUTTON_SAVE);
//        clickOn(Identity.APP_TAB_TEAMS_TABLE);
//        keystroke(KeyCode.HOME);
//        keystroke(KeyCode.ENTER);
    }

    @Test
    void testAddRider() {
        actionCreateNewPace();
        shortcutOpen();
        clickOn(Identity.DIALOG_EDIT_TEAM_BUTTON_ADD_RIDER);
        assertEquals(1, getIdentityCount(Identity.DIALOG_EDIT_TEAM_INPUT_RIDER_NAME));
        assertEquals(1, getIdentityCount(Identity.DIALOG_EDIT_TEAM_BUTTON_REMOVE_RIDER));
    }

    @Test
    void testRemoveRider() {
        actionCreateNewPace();
        shortcutOpen();
        clickOn(Identity.DIALOG_EDIT_TEAM_BUTTON_ADD_RIDER);
        clickOn(Identity.DIALOG_EDIT_TEAM_BUTTON_REMOVE_RIDER, 0);
        assertEquals(0, getIdentityCount(Identity.DIALOG_EDIT_TEAM_INPUT_RIDER_NAME));
        assertEquals(0, getIdentityCount(Identity.DIALOG_EDIT_TEAM_BUTTON_REMOVE_RIDER));
    }

    @Test
    void testRidersPersist() {
        List<String> riders = Stream.generate(this::randomFullName).limit(5).toList();
        actionCreateNewPace();
        shortcutOpen();
        riders.forEach(this::actionDialogEditTeamAddRider);
        shortcutReopen();
        for (int i = 0; i < riders.size(); i++) {
            assertEquals(riders.get(i), get(TextField.class, Identity.DIALOG_EDIT_TEAM_INPUT_RIDER_NAME, i).getText());
        }
    }

    @Test
    void testStartTime() {
        String timeText = new EventTime(Math.abs(RANDOM.nextLong())).toString();
        actionCreateNewPace();
        shortcutOpen();
        writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_TIME_START, timeText);
        shortcutReopen();
        assertEquals(timeText, get(TextField.class, Identity.DIALOG_EDIT_TEAM_INPUT_TIME_START).getText());
    }

    @Test
    void testTimeEnd() {
        String timeText = new EventTime(Math.abs(RANDOM.nextLong())).toString();
        actionCreateNewPace();
        shortcutOpen();
        writeInto(Identity.DIALOG_EDIT_TEAM_INPUT_TIME_END, timeText);
        shortcutReopen();
        assertEquals(timeText, get(TextField.class, Identity.DIALOG_EDIT_TEAM_INPUT_TIME_END).getText());
    }
}