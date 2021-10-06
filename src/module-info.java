module paceManager {
    opens app to com.google.gson;
    opens data to com.google.gson;

    exports ui to javafx.graphics;
    exports app to javafx.graphics;
    exports data;
    opens settings to com.google.gson;
    exports settings to javafx.graphics;

    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;
}