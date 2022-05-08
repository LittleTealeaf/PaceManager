module org.tealeaf.pacemanager {
    requires javafx.graphics;
    requires java.prefs;
    requires javafx.fxml;
    requires javafx.controls;
    requires net.harawata.appdirs;
    requires com.google.gson;

    opens org.tealeaf.pacemanager.database.dataobjects to com.google.gson;
    opens org.tealeaf.pacemanager.database to com.google.gson;


    exports org.tealeaf.pacemanager;
    exports org.tealeaf.pacemanager.system;
    exports org.tealeaf.pacemanager.app;
    exports org.tealeaf.pacemanager.app.layouts;
    exports org.tealeaf.pacemanager.app.components;
    exports org.tealeaf.pacemanager.events;
    exports org.tealeaf.pacemanager.database;
    exports org.tealeaf.pacemanager.database.dataobjects;
    exports org.tealeaf.pacemanager.app.layouts.content;
}