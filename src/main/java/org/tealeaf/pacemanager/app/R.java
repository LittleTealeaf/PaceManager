package org.tealeaf.pacemanager.app;

public class R {


    private static int c = 1;


    public static String registerID() {
        return "#%d".formatted(c++);
    }
}
