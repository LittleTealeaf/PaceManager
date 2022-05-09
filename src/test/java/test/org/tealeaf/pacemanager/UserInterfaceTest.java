package test.org.tealeaf.pacemanager;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.tealeaf.pacemanager.Launcher;
import org.tealeaf.pacemanager.app.layouts.App;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.database.dataobjects.Pace;
import org.tealeaf.pacemanager.system.GsonWrapper;
import org.testfx.api.FxRobotInterface;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.service.query.NodeQuery;
import test.org.tealeaf.pacemanager.database.dataobjects.RandomDataObjects;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInterfaceTest extends ApplicationTest {

    protected App app;

    @BeforeEach
    public void beforeEachTest() throws Exception {
        launch(Launcher.class);
        app = get(App.class,lookup(Identifier.LAYOUT_APP));

    }

    public void actionSelectItemTable(Identifier tableIdentifier, int index) {
//        clickOn(tableIdentifier);
//
//        tableView.getSelectionModel().select(tableView.getItems().get(index));

        TableView tableView = (TableView) get(TableView.class, lookup(tableIdentifier));

        int count = tableView.getItems().size();
        clickOn(tableIdentifier);

        press(count/2 > index ? KeyCode.HOME : KeyCode.END);
        release(KeyCode.HOME,KeyCode.END);
        int times = count/2 > index ? index : count - index;
        for(int i = 0; i < times; i++) {
            press(count/2 > index ? KeyCode.DOWN : KeyCode.UP);
            release(KeyCode.UP,KeyCode.DOWN);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
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

    protected void applyPath(String filePath){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(filePath);
        clipboard.setContents(stringSelection, stringSelection);
        press(KeyCode.CONTROL).press(KeyCode.V).release(KeyCode.V).release(KeyCode.CONTROL);
        push(KeyCode.ENTER);
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

    public FxRobotInterface clickOn(Identifier identifier, MouseButton... buttons) {
        return clickOn(identifier.getID(),buttons);
    }

    public boolean exists(Identifier identifier) {
        return lookup(identifier).tryQuery().isPresent();
    }



    /**
     * Opens a pace, requires that it is the first instruction
     * @param pace
     */
    protected void actionOpenPaceFile(Pace pace, Path filePath) throws IOException {
        File file = filePath.toFile();
        GsonWrapper.write(pace, file);
        press(KeyCode.ALT);
        clickOn(Identifier.LAYOUT_CONTENT_OPEN_PACE_BUTTON);
        release(KeyCode.ALT);
        clickOn(Identifier.LAYOUT_MANUAL_FILE_ENTRY_TEXT).write(file.getPath());
        clickOn(Identifier.LAYOUT_MANUAL_FILE_ENTRY_BUTTON);
        applyPath(file.getPath());
    }

    protected void actionOpenRandomPaceFile(Path tempDir) throws IOException {
        press(KeyCode.ALT);
        clickOn(Identifier.LAYOUT_CONTENT_OPEN_PACE_BUTTON);
        release(KeyCode.ALT);
        clickOn(Identifier.LAYOUT_MANUAL_FILE_ENTRY_TEXT).write(new RandomDataObjects() {}.randomPaceFile(tempDir).getPath());
        clickOn(Identifier.LAYOUT_MANUAL_FILE_ENTRY_BUTTON);
        assertTrue(exists(Identifier.LAYOUT_CONTENT_APP));
    }

    protected void actionOpenNewPace() {
        clickSequence(Identifier.MENU_FILE,Identifier.MENU_FILE_NEW);
    }

    public void clickSequence(Identifier... identifiers) {
        for (Identifier identifier : identifiers) {
            clickOn(identifier);
        }
    }

    protected void actionOpenAbout() {
        clickSequence(Identifier.MENU_HELP,Identifier.MENU_HELP_ABOUT);
    }




}
