module org.tealeaf.pacemanager {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.media;
    requires javafx.web;
    requires java.prefs;

    exports org.tealeaf.pacemanager.test.listeners;

    exports org.tealeaf.pacemanager.test to javafx.fxml, javafx.graphics;
    exports org.tealeaf.pacemanager.test.app to javafx.fxml, javafx.graphics;
}