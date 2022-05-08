package org.tealeaf.pacemanager.app.components;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.windows.About;
import org.tealeaf.pacemanager.events.EventCoordinator;

import static org.tealeaf.pacemanager.app.Identifier.*;

public class AppMenu extends MenuBar {


    public AppMenu(App app) {
        MENU.set(this);

        getMenus().addAll(new Menu() {{
            MENU_FILE.set(this);

            setText("File");
            getItems().addAll(new MenuItem() {{
                setText("Open");
                MENU_FILE_OPEN.set(this);
            }}, new MenuItem() {{
                setText("Save");
                MENU_FILE_SAVE.set(this);
            }}, new MenuItem() {{
                setText("Save As");
                MENU_FILE_SAVE_AS.set(this);
            }},new MenuItem() {{
                MENU_FILE_EXIT.set(this);
                setText("Exit");
                setOnAction((action) -> app.runEvent(OnMenuExit.class,OnMenuExit::onMenuExit));

            }});
        }}, new Menu() {{
            MENU_HELP.set(this);
            setText("Help");

            getItems().addAll(new MenuItem() {{
                MENU_HELP_ABOUT.set(this);
                setText("About");
               setOnAction(action -> app.launchWindow(About.build(app)));
            }});
        }});
    }

    public interface OnMenuExit {
        void onMenuExit();
    }
}
