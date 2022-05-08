package org.tealeaf.pacemanager.app.components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import static org.tealeaf.pacemanager.app.Identifier.*;

public class AppMenu extends MenuBar {



    public AppMenu(Listener listener) {
        MENU.set(this);

        getMenus().addAll(new Menu() {{
            MENU_FILE.set(this);

            setText("File");
            getItems().addAll(new MenuItem() {{
                MENU_FILE_EXIT.set(this);
                setText("Hello");
            }});
        }});
    }



    public interface Listener {

    }
}
