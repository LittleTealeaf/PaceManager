package app;

import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Resources {

    private static final AppDirs APP_DIRS;

    private static final String APP_NAME, APP_AUTHOR, APP_VERSION;
    private static final boolean USE_ROAMING;

    static {
        APP_DIRS = AppDirsFactory.getInstance();
        APP_NAME = "Pace Manager";
        APP_AUTHOR = "Tealeaf";
        APP_VERSION = null;
        USE_ROAMING = true;
    }

    /**
     * Grabs a system resources as a stream.
     * <p>
     * Make sure to run a {@code maven compile} cycle before testing, otherwise the file will not be included in
     * the resources
     * </p>
     *
     * @param name Resource Path of resource <br>If in a subdirectory, format such as {@code /dev/pace2021.json},
     *             if not format such as {@code pace2021.json}
     *
     * @return Input Stream of desired resource
     */
    public static InputStream getResource(String name) {
        return Resources.class.getResourceAsStream(name);
    }

    public static Path getUserDataPath() {
        return getUserDataPath("");
    }

    public static Path getUserDataPath(String... path) {
        return Paths.get(APP_DIRS.getUserDataDir(APP_NAME, APP_VERSION, APP_AUTHOR, USE_ROAMING), path);
    }

    public static Path getUserConfigPath() {
        return getUserConfigPath("");
    }

    public static Path getUserConfigPath(String... path) {
        return Paths.get(APP_DIRS.getUserConfigDir(APP_NAME, APP_VERSION, APP_AUTHOR, USE_ROAMING), path);
    }

    public static Path getUserLogPath() {
        return getUserLogPath("");
    }

    public static Path getUserLogPath(String... path) {
        return Paths.get(APP_DIRS.getUserLogDir(APP_NAME, APP_VERSION, APP_AUTHOR), path);
    }

    public static Path getUserCachePath() {
        return getUserCachePath("");
    }

    public static Path getUserCachePath(String... path) {
        return Paths.get(APP_DIRS.getUserCacheDir(APP_NAME, APP_VERSION, APP_AUTHOR), path);
    }
}
