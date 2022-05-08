package org.tealeaf.pacemanager.app;

public class R {

    public static String APPLICATION = registerID();
    public static String DEBUG_BUTTON = registerID();

    private static int c = 1;


    private static String registerID() {
        return "#%d".formatted(c = c + 1);
    }
}
