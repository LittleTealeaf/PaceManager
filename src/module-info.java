module paceManager {

    opens data_deprecated to com.google.gson;

    exports data_deprecated to com.google.gson;
    exports interfaces to com.google.gson;

    requires com.google.gson;
}