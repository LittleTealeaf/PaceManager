package org.tealeaf.pacemanager.app;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.Launcher;
import org.tealeaf.pacemanager.app.components.AppMenu;
import org.tealeaf.pacemanager.system.Preferences;
import org.tealeaf.pacemanager.database.PaceHandler;
import org.tealeaf.pacemanager.events.EventCoordinator;
import org.tealeaf.pacemanager.events.EventManager;

import java.util.Set;

import static org.tealeaf.pacemanager.app.Identifier.APP;

/**
 * The root application
 */
public class App extends BorderPane implements EventCoordinator, Launcher.OnStop, Launcher.OnClose {

    private final Preferences preferences;

    private final PaceHandler paceHandler;

    private final Stage stage;

    private final EventCoordinator eventCoordinator;

    public App(Stage stage) {
        super();

        APP.set(this);

        this.stage = stage;

        eventCoordinator = new EventManager();
        paceHandler = new PaceHandler(this);
        preferences = new Preferences(this);

        addListener(this);

        setTop(new AppMenu(this));

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

    public void launchWindow(Scene scene) {
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public PaceHandler getProjectManager() {
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


}
