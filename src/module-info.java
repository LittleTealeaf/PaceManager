module paceManager {

    opens data to com.google.gson;

    exports data to com.google.gson;
    exports interfaces to com.google.gson;

    requires com.google.gson;
}