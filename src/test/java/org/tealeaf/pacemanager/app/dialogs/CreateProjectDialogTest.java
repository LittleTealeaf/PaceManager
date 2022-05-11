package org.tealeaf.pacemanager.app.dialogs;

import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.constants.ProjectNameRestrictions;
import test.ApplicationWrapper;

import static org.junit.jupiter.api.Assertions.*;

class CreateProjectDialogTest extends ApplicationWrapper {

    @BeforeEach
    void openCreatePaceDialog() {
        actionOpenCreatePace();
    }

    @Test
    void testTypeInNameField() {
        clickOn(Identity.DIALOG_CREATE_PACE_FIELD_NAME);
        String text = randomFullName();
        write(text);
        assertEquals(text, get(TextField.class, Identity.DIALOG_CREATE_PACE_FIELD_NAME).getText());
    }

    @Test
    void testCreateNewProject() {
        actionCreateNewPace("");
        assertTrue(exists(Identity.APP_LAYOUT));
    }

    @Test
    void testCancel() {
        actionOpenCreatePace();
        clickOn(Identity.DIALOG_CREATE_PACE_BUTTON_CANCEL);
        assertFalse(exists(Identity.DIALOG_CREATE_PACE));
    }

    @Test
    void testNameRestrictionsIllegalCharacters() {
        writeInto(Identity.DIALOG_CREATE_PACE_FIELD_NAME, ProjectNameRestrictions.ILLEGAL_CHARACTERS);
        assertEquals("", get(TextField.class, Identity.DIALOG_CREATE_PACE_FIELD_NAME).getText());
    }

    @Test
    void testNameRestrictionsLength() {
        writeInto(Identity.DIALOG_CREATE_PACE_FIELD_NAME, "a".repeat(ProjectNameRestrictions.MAX_LENGTH + 1));
        assertEquals(ProjectNameRestrictions.MAX_LENGTH, get(TextField.class, Identity.DIALOG_CREATE_PACE_FIELD_NAME).getText().length());
    }
}