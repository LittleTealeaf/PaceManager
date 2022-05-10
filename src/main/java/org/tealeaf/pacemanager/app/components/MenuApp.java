package org.tealeaf.pacemanager.app.components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.dialogs.CreatePaceDialog;
import org.tealeaf.pacemanager.app.listeners.CloseProjectListener;
import org.tealeaf.pacemanager.app.listeners.RequestExitListener;
import org.tealeaf.pacemanager.events.EventCoordinator;

public class MenuApp extends MenuBar {


    public MenuApp(EventCoordinator eventCoordinator) {
        getMenus().addAll(new Menu() {{
            setText("File");
            Identity.APP_MENU_FILE.set(this);

            getItems().addAll(new MenuItem() {{
                Identity.APP_MENU_FILE_NEW.set(this);
                setText("New");
                setOnAction(event -> new CreatePaceDialog(eventCoordinator));
            }}, new MenuItem() {{
                Identity.APP_MENU_FILE_OPEN.set(this);
                setText("Open");
            }}, new MenuItem() {{
                Identity.APP_MENU_FILE_CLOSE_PROJECT.set(this);
                setText("Close Project");
                setOnAction(event -> eventCoordinator.run(CloseProjectListener.class,CloseProjectListener::onCloseProject));
            }}, new MenuItem() {{
                Identity.APP_MENU_FILE_EXIT.set(this);
                setText("Exit");
                setOnAction(event -> eventCoordinator.run(RequestExitListener.class,RequestExitListener::onRequestExit));
            }});
        }});
    }
}
