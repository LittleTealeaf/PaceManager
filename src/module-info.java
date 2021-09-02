module paceManager {
    opens app to com.google.gson;
    opens data to com.google.gson;

    exports ui to javafx.graphics;
    exports app to javafx.graphics;

    requires  javafx.base;
    requires  javafx.graphics;
    requires  javafx.controls;
    requires java.desktop;
    requires java.base;
    requires com.google.gson;
}