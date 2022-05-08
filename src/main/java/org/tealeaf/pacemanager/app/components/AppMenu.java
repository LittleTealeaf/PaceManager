package org.tealeaf.pacemanager.app.components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.tealeaf.pacemanager.app.layouts.App;
import org.tealeaf.pacemanager.app.layouts.About;
import org.tealeaf.pacemanager.database.PaceHandler;

import static org.tealeaf.pacemanager.app.Identifier.*;

public class AppMenu extends MenuBar {



    public AppMenu(App app) {
        MENU.set(this);

        getMenus().addAll(new Menu() {{
            MENU_FILE.set(this);

            setText("File");
            getItems().addAll(new MenuItem() {{
                setText("New");
                MENU_FILE_NEW.set(this);
                setOnAction(event -> app.runEvent(OnFileNew.class,OnFileNew::onMenuFileNew));
            }},new MenuItem() {{
                setText("Open");
                MENU_FILE_OPEN.set(this);
            }}, new MenuItem() {{
                MENU_FILE_CLOSE.set(this);
                setText("Close");
                setDisable(true);
                app.addListener((PaceHandler.OnPaceClosed) () -> setDisable(true));
                app.addListener((PaceHandler.OnPaceOpened) pace -> setDisable(false));
                setOnAction(event -> app.getPaceHandler().closePace());
            }}, new MenuItem() {{
                setText("Save");
                setDisable(true);
                app.addListener((PaceHandler.OnPaceOpened) pace -> setDisable(false));
                app.addListener((PaceHandler.OnPaceClosed) () -> setDisable(true));
                MENU_FILE_SAVE.set(this);
            }}, new MenuItem() {{
                setText("Save As");
                setDisable(true);
                app.addListener((PaceHandler.OnPaceOpened) pace -> setDisable(false));
                app.addListener((PaceHandler.OnPaceClosed) () -> setDisable(true));
                MENU_FILE_SAVE_AS.set(this);
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

    public interface OnFileNew {
        void onMenuFileNew();
    }
}
