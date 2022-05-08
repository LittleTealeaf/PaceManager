package org.tealeaf.pacemanager;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.layouts.App;
import org.tealeaf.pacemanager.system.AppData;

public class Launcher extends Application {

    private App app;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        app.runEvent(OnStop.class, OnStop::onStop);
        super.stop();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) {

        stage.setScene(new Scene(app = new App(stage)));
        stage.setOnCloseRequest((action) -> app.runEvent(OnClose.class, OnClose::onClose));
        stage.setTitle("Pace Manager");
        stage.show();
    }

    public interface OnClose {

        void onClose();
    }

    public interface OnStop {

        void onStop();
    }
}
