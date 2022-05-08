package org.tealeaf.pacemanager.app;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.listeners.IListenerManager;
import org.tealeaf.pacemanager.listeners.ListenerManager;

import java.util.Set;
import java.util.prefs.Preferences;

public class PaceManager extends Application implements IListenerManager {

    private final IListenerManager listenerManager = new ListenerManager();

    private Preferences preferences;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        unregisterListener(this);
    }

    @Override
    public void init() throws Exception {
        super.init();
        registerListener(this);
    }

    public void registerListener(Object object) {
        listenerManager.registerListener(object);
    }

    public void unregisterListener(Object object) {
        listenerManager.unregisterListener(object);
    }

    @Override
    public <T> void callListeners(Class<T> tClass, Notification<T> notification) {
        listenerManager.callListeners(tClass,notification);
    }

    @Override
    public Set<Object> getListeners() {
        return listenerManager.getListeners();
    }

    public interface Notifier<T> {
        void call(T listener);
    }



    @Override
    public void start(Stage stage) {
        preferences = Preferences.userRoot().node("org/tealeaf/pacemanager/preferences");


        stage.setScene(new Scene(new BorderPane() {{
            setId(R.APPLICATION);
            setBottom(new Button() {{
                setId(R.DEBUG_BUTTON);
                setOnMouseClicked((event) -> {
                    setText("Hello");
                });
                setText("Click");
            }});
        }},500,500));



        stage.show();

    }
}
