package org.tealeaf.pacemanager.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.stages.LauncherLayout;
import org.tealeaf.pacemanager.app.listeners.OpenProjectListener;
import org.tealeaf.pacemanager.data.Project;
import org.tealeaf.pacemanager.events.EventCoordinator;
import org.tealeaf.pacemanager.events.EventManager;

import java.util.Set;

public class Launcher extends Application implements EventCoordinator, OpenProjectListener {

    private final EventManager eventManager = new EventManager(this);

    private Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Pace Manager");

        Scene scene = new Scene(new LauncherLayout(this));
        stage.setWidth(500);
        stage.setHeight(500);


        stage.setScene(scene);


        stage.show();
    }

    @Override
    public void addListener(Object object) {
        eventManager.addListener(object);
    }

    @Override
    public void removeListener(Object object) {
        eventManager.removeListener(object);
    }

    @Override
    public <T> void run(Class<T> listenerClass, Event<T> event) {

        eventManager.run(listenerClass, event);
    }

    @Override
    public Set<Object> getListeners() {
        return eventManager.getListeners();
    }

    @Override
    public void openProject(Project project) {
        new App(project,this);
        stage.hide();
    }

    public void show() {
        stage.show();
    }
}
