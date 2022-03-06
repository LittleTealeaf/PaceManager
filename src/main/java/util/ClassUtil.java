package util;

public class ClassUtil {

    public static String formatKey(Class varClass, String name) {
        return varClass.getName() + "." + name;
    }
}