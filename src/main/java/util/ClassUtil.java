package util;

public final class ClassUtil {

    private ClassUtil() {}

    public static String formatKey(Class varClass, String name) {
        return varClass.getName() + "." + name;
    }
}