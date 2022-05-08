module org.tealeaf.pacemanager {
    requires javafx.graphics;
    requires java.prefs;
    requires javafx.fxml;
    requires javafx.controls;
    requires net.harawata.appdirs;

    exports org.tealeaf.pacemanager;
    exports org.tealeaf.pacemanager.system;
    exports org.tealeaf.pacemanager.app;
    exports org.tealeaf.pacemanager.events;
    exports org.tealeaf.pacemanager.database;
    exports org.tealeaf.pacemanager.database.dataobjects;
}