package org.tealeaf.pacemanager.app;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.components.AppMenu;

import java.util.Set;

import static org.tealeaf.pacemanager.app.Identifier.*;

public class PaceManager extends Application implements AppMenu.Listener {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }


    public interface Notifier<T> {
        void call(T listener);
    }



    @Override
    public void start(Stage stage) {

        stage.setScene(new Scene(new BorderPane() {{
            APPLICATION.set(this);

            setTop(new AppMenu(PaceManager.this));

        }},500,500));



        stage.show();

    }
}
