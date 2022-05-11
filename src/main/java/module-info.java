module org.tealeaf.pacemanager {
    requires javafx.graphics;
    requires java.prefs;
    requires javafx.fxml;
    requires javafx.controls;
    requires net.harawata.appdirs;
    requires com.google.gson;
    requires org.hildan.fxgson;

    opens org.tealeaf.pacemanager.data to com.google.gson;

    exports org.tealeaf.pacemanager.app;

    exports org.tealeaf.pacemanager.app.dialogs;
    exports org.tealeaf.pacemanager.app.stages;
    exports org.tealeaf.pacemanager.data;
    exports org.tealeaf.pacemanager.events;
    exports org.tealeaf.pacemanager.system;
    exports org.tealeaf.pacemanager.util;
}