package org.tealeaf.pacemanager;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.Identifier;
import org.testfx.api.FxRobotInterface;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.service.query.NodeQuery;

public class UserInterfaceTest extends ApplicationTest {

    protected App app;

    @BeforeEach
    public void beforeEachTest() throws Exception {
        launch(Launcher.class);
        app = get(App.class,lookup(Identifier.APP));
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

    public <T> T get(Class<T> tClass, NodeQuery nodeQuery) {
        return tClass.cast(nodeQuery.query());
    }

    public NodeQuery lookup(Identifier identifier) {
        return lookup(identifier.toString());
    }

    @Override
    public NodeQuery lookup(String query) {
        return lookup(node -> query.equals(node.getId()));
    }

    public FxRobotInterface clickOn(Identifier identifier) {
        return clickOn(identifier.toString());
    }

    public boolean exists(Identifier identifier) {
        return lookup(identifier).tryQuery().isPresent();
    }
}
