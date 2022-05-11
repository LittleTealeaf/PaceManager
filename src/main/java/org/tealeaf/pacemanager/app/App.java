package org.tealeaf.pacemanager.app;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.api.Context;
import org.tealeaf.pacemanager.app.dialogs.EditTeamDialog;
import org.tealeaf.pacemanager.app.key.KeyTracker;
import org.tealeaf.pacemanager.app.key.KeyboardManager;
import org.tealeaf.pacemanager.app.listeners.CloseProjectListener;
import org.tealeaf.pacemanager.app.listeners.OpenProjectListener;
import org.tealeaf.pacemanager.app.listeners.RequestExitListener;
import org.tealeaf.pacemanager.app.stages.AppLayout;
import org.tealeaf.pacemanager.data.Project;
import org.tealeaf.pacemanager.data.Team;
import org.tealeaf.pacemanager.events.EventCoordinator;
import org.tealeaf.pacemanager.events.EventManager;

import java.util.Set;

public class App extends Stage implements Context, OpenProjectListener, CloseProjectListener, RequestExitListener {

    private final EventCoordinator eventCoordinator = new EventManager(this);

    private final KeyTracker keyTracker = new KeyboardManager();
    private final Launcher launcher;
    private final Project project;

    public App(Project project, Launcher launcher) {
        this.project = project;
        this.launcher = launcher;

        project.registerEventCoordinator(this);

        setTitle("Pace Manager - " + project.getName());

        setScene(new Scene(new AppLayout(this)));
        setOnCloseRequest(event -> run(OnClose.class, OnClose::onAppClose));

        show();
    }

    @Override
    public void addListener(Object object) {
        eventCoordinator.addListener(object);
    }

    @Override
    public void removeListener(Object object) {
        eventCoordinator.removeListener(object);
    }

    @Override
    public <T> void run(Class<T> listenerClass, Event<T> event) {
        eventCoordinator.run(listenerClass, event);
    }

    @Override
    public Set<Object> getListeners() {
        return eventCoordinator.getListeners();
    }

    @Override
    public void openProject(Project project) {
        close();
        new App(project, launcher);
    }

    @Override
    public void onCloseProject() {
        close();
        launcher.show();
    }

    @Override
    public void onRequestExit() {
        try {
            close();
            launcher.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isKeyPressed(KeyCode keyCode) {
        return keyTracker.isKeyPressed(keyCode);
    }

    @Override
    public void keyEvent(KeyEvent keyEvent) {
        keyTracker.keyEvent(keyEvent);
    }

    public interface OnClose {

        void onAppClose();
    }

    public Project getProject() {
        return project;
    }
}
