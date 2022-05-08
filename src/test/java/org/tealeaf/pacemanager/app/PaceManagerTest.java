package org.tealeaf.pacemanager.app;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.*;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.finder.NodeFinder;
import org.testfx.service.query.NodeQuery;

import javax.management.Query;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

import static org.tealeaf.pacemanager.app.Identifier.*;

public class PaceManagerTest extends ApplicationTest {


    @BeforeEach
    public void beforeEachTest() throws Exception {

        launch(PaceManager.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @AfterEach
    public void afterEachTest() throws Exception {
        FxToolkit.cleanupStages();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    public <T> T find(Class<T> tClass, String query) {
        return tClass.cast(lookup(query).query());
    }

    @Override
    public NodeQuery lookup(String query) {
        return lookup((node) -> query.equals(node.getId()));
    }

    public FxRobotInterface clickOn(Identifier identifier) {
        return clickOn(identifier.toString());
    }

    @Test
    public void testLaunch() {}

    @Test
    public void testExit() {
        clickOn(MENU_FILE);
        clickOn(MENU_FILE_EXIT);
    }

}