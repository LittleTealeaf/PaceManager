module org.tealeaf.pacemanager {
    requires javafx.graphics;
    requires java.prefs;
    requires javafx.fxml;
    requires javafx.controls;
    requires net.harawata.appdirs;
    requires com.google.gson;

    exports org.tealeaf.pacemanager.app;
    exports org.tealeaf.pacemanager.app.dialogs;
    exports org.tealeaf.pacemanager.app.layouts;
    exports org.tealeaf.pacemanager.data;
    exports org.tealeaf.pacemanager.events;
    exports org.tealeaf.pacemanager.system;
}