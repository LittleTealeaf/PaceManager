package org.tealeaf.pacemanager.app.key;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public interface KeyTracker {

    boolean isKeyPressed(KeyCode keyCode);

    void keyEvent(KeyEvent keyEvent);
}
