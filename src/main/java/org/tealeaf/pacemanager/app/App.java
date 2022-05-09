package org.tealeaf.pacemanager.app;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.layouts.AppLayout;
import org.tealeaf.pacemanager.app.listeners.CloseProjectListener;
import org.tealeaf.pacemanager.app.listeners.OpenProjectListener;
import org.tealeaf.pacemanager.data.Project;
import org.tealeaf.pacemanager.events.EventCoordinator;
import org.tealeaf.pacemanager.events.EventManager;

import java.util.Set;

public class App extends Stage implements EventCoordinator, OpenProjectListener, CloseProjectListener {

    private final EventCoordinator eventCoordinator = new EventManager(this);
    private final Launcher launcher;
    private final Project project;

    public App(Project project, Launcher launcher) {
        this.project = project;
        this.launcher = launcher;

        setTitle(project.getName());

        setScene(new Scene(new AppLayout(this)));
        setOnCloseRequest(event -> run(OnClose.class,OnClose::onAppClose));

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
        new App(project,launcher);
    }

    @Override
    public void onCloseProject() {
        close();
        launcher.show();
    }

    public interface OnClose {
        void onAppClose();
    }
}
