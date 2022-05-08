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
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotContext;
import org.testfx.api.FxToolkit;
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


    @Test
    public void testLaunching() {

    }

    public <T> T find(Class<T> tClass, String query) {
        return tClass.cast(lookup(query).query());
    }

    @Override
    public NodeQuery lookup(String query) {
        return lookup((node) -> query.equals(node.getId()));
    }

    @Test
    public void testButton() throws Exception {
        clickOn(R.DEBUG_BUTTON);
        assertEquals("Hello",find(Button.class,R.DEBUG_BUTTON).getText());

    }
}