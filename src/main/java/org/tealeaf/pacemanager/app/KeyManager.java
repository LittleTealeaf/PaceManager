package org.tealeaf.pacemanager.app;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class KeyManager {

    private Set<KeyCode> pressed = new HashSet<>();

    public KeyManager(Scene scene) {
        scene.setOnKeyPressed(e -> {
            pressed.add(e.getCode());
        });
        scene.setOnKeyReleased(e -> {
            pressed.remove(e.getCode());
        });
    }
}
