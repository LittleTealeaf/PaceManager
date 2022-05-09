package org.tealeaf.pacemanager.app.layouts;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.Launcher;
import org.tealeaf.pacemanager.app.KeyManager;
import org.tealeaf.pacemanager.app.components.AppMenu;
import org.tealeaf.pacemanager.app.layouts.content.AppContent;
import org.tealeaf.pacemanager.app.layouts.content.OpenPaceContent;
import org.tealeaf.pacemanager.app.util.StageHolder;
import org.tealeaf.pacemanager.database.dataobjects.Pace;
import org.tealeaf.pacemanager.system.Preferences;
import org.tealeaf.pacemanager.database.PaceHandler;
import org.tealeaf.pacemanager.events.EventCoordinator;
import org.tealeaf.pacemanager.events.EventManager;
import org.tealeaf.pacemanager.util.Closable;

import java.util.Set;

import static org.tealeaf.pacemanager.app.Identifier.LAYOUT_APP;

/**
 * The root application
 */
public class App extends BorderPane implements EventCoordinator, Launcher.OnStop, Launcher.OnClose, PaceHandler.OnPaceOpened, PaceHandler.OnPaceClosed {


    private final Preferences preferences;

    private final PaceHandler paceHandler;

    private final Stage stage;

    private final EventCoordinator eventCoordinator;

    public App(Stage stage) {
        super();
        stage.setWidth(1000);
        stage.setHeight(1000);

        LAYOUT_APP.set(this);

        this.stage = stage;
        eventCoordinator = new EventManager();
        paceHandler = new PaceHandler(this);
        preferences = new Preferences(this);

        addListener(this);

        setTop(new AppMenu(this));
        setContent(new OpenPaceContent(this));
    }
    @Override
    public Set<Object> getListeners() {
        return eventCoordinator.getListeners();
    }

    @Override
    public boolean addListener(Object object) {
        return eventCoordinator.addListener(object);
    }

    @Override
    public boolean removeListener(Object object) {
        return eventCoordinator.removeListener(object);
    }

    @Override
    public <T> void runEvent(Class<T> listener, EventAction<T> action) {
        eventCoordinator.runEvent(listener,action);
    }

    public Stage getStage() {return stage;}

    public void launchWindow(Scene scene) {
        launchWindow(scene,null);
    }

    public void launchWindow(Parent node) {
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        if(node instanceof StageHolder stageHolder) {
            stageHolder.setStage(stage);
        }
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void launchWindow(Scene scene, StageHolder stageHolder) {
        Stage stage = new Stage();
        stage.setScene(scene);
        if(stageHolder != null) {
            stageHolder.setStage(stage);
        }

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void setContent(Node node) {
        if(getCenter() instanceof Closable closable) {
            closable.close();
        }
        setCenter(node);
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public PaceHandler getPaceHandler() {
        return paceHandler;
    }

    @Override
    public void onStop() {
        System.out.println("Stop");
    }


    @Override
    public void onClose() {
        System.out.println("Close");
    }

    @Override
    public void onPaceOpened(Pace pace) {
        setContent(new AppContent(this));
    }

    @Override
    public void onPaceClosed() {
        setContent(new OpenPaceContent(this));
    }
}
