package org.tealeaf.pacemanager.app.key;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class KeyboardManager implements KeyTracker {

    private final Set<KeyCode> keysDown = new HashSet<>();

    public KeyboardManager() {

    }

    public void resetKeyPresses() {
        keysDown.clear();
    }

    @Override
    public boolean isKeyPressed(KeyCode keyCode) {
        return false;
    }

    public void keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            keysDown.add(keyEvent.getCode());
        } else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            keysDown.remove(keyEvent.getCode());
        }
    }
}
