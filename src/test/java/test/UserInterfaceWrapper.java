package test;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.Launcher;
import org.testfx.api.FxRobotInterface;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.service.query.NodeQuery;

import java.util.Arrays;

public class UserInterfaceWrapper extends ApplicationTest {

    @BeforeEach
    public void launchApplication() throws Exception {
        launch(Launcher.class);
    }

    @Override
    public void start(Stage stage) {
        stage.show();
    }

    @AfterEach
    public void cleanupStages() throws Exception {
        FxToolkit.cleanupStages();
    }

    @AfterEach
    public void cleanupInputs() {
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    public App getApp() {
        for (Window window : listWindows()) {
            if (window instanceof App app) {
                return app;
            }
        }
        return null;
    }

    public void clickOn(Identity... identities) {
        for (Identity identity : identities) {
            clickOn(identity.toID());
        }
    }

    public void doubleClickOn(Identity identity) {
        doubleClickOn(identity.toID());
    }

    public void doubleClickOn(Identity identity, int i) {
        doubleClickOn(identity.toID(i));
    }

    public FxRobotInterface clickOn(@NotNull Identity identity, MouseButton... mouseButtons) {
        return clickOn(identity.toID(), mouseButtons);
    }

    public boolean exists(Identity identity) {
        return lookup(identity).tryQuery().isPresent();
    }

    public NodeQuery lookup(Identity identity) {
        return lookup(identity.toID());
    }

    @Override
    public NodeQuery lookup(String query) {
        return lookup(node -> query.equals(node.getId()));
    }

    public <T> T get(Class<T> tClass, Identity identity, int i) {
        return get(tClass, lookup(identity, i));
    }

    public <T> T get(Class<T> tClass, NodeQuery nodeQuery) {
        return tClass.cast(nodeQuery.query());
    }

    public NodeQuery lookup(Identity identity, int i) {
        return lookup(identity.toID(i));
    }

    public <T> T get(Class<T> tClass, Identity identity) {
        return get(tClass, lookup(identity));
    }

    public void writeInto(Identity identity, String text) {
        clickOn(identity);
        write(text);
    }

    public void clickOn(Identity identity) {
        clickOn(identity.toID());
    }

    public void writeInto(Identity identity, int i, String text) {
        clickOn(identity, i);
        write(text);
    }

    public void clickOn(Identity identity, int i) {
        clickOn(identity.toID(i));
    }

    public int getIdentityCount(Identity identity) {
        int count = 0;
        while (exists(identity, count)) {
            count++;
        }
        return count;
    }

    public boolean exists(Identity identity, int i) {
        return lookup(identity, i).tryQuery().isPresent();
    }

    public void keystroke(KeyCode... keyCodes) {
        press(keyCodes[0]);
        if (keyCodes.length > 1) {
            keystroke(Arrays.copyOfRange(keyCodes, 1, keyCodes.length));
        }
        release(keyCodes[0]);
    }
}
