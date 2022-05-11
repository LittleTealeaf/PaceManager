package org.tealeaf.pacemanager.app.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.tealeaf.pacemanager.app.Context;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.dialogs.CreatePaceDialog;
import org.tealeaf.pacemanager.app.listeners.CloseProjectListener;
import org.tealeaf.pacemanager.app.listeners.RequestExitListener;

public class MenuApp extends MenuBar {

    public MenuApp(Context context) {
        getMenus().addAll(fileMenu(context));
    }

    protected Menu fileMenu(Context context) {

        Menu menu = new Menu("File");
        Identity.APP_MENU_FILE.set(menu);

        addMenuItem(menu, "New", Identity.APP_MENU_FILE_NEW, event -> new CreatePaceDialog(context));
        addMenuItem(menu, "Open", Identity.APP_MENU_FILE_OPEN, event -> {});
        addMenuItem(menu, "Close Project", Identity.APP_MENU_FILE_CLOSE_PROJECT, event -> context.run(CloseProjectListener.class, CloseProjectListener::onCloseProject));
        addMenuItem(menu, "Exit", Identity.APP_MENU_FILE_EXIT, event -> context.run(RequestExitListener.class, RequestExitListener::onRequestExit));

        return menu;
    }

    protected void addMenuItem(Menu menu, String text, Identity identity, EventHandler<ActionEvent> eventHandler) {
        menu.getItems().add(createMenuItem(text, identity, eventHandler));
    }

    protected MenuItem createMenuItem(String text, Identity identity, EventHandler<ActionEvent> eventHandler) {
        MenuItem menuItem = new MenuItem(text);
        identity.set(menuItem);
        menuItem.setOnAction(eventHandler);
        return menuItem;
    }
}
