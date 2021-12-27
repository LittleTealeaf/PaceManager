package settings;

import app.Resources;
import settings.enums.SaveMethod;
import settings.types.BooleanSetting;
import settings.types.ChoiceSetting;
import settings.types.SetSetting;
import settings.types.StackSetting;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static settings.SettingCategory.*;

public class Settings implements Serializable {

    public static final BooleanSetting DISPLAY_24HOUR_TIME;
    public static final BooleanSetting AUTO_SAVE_SETTINGS;
    public static final ChoiceSetting<SaveMethod> SAVE_METHOD;
    public static final StackSetting<File> RECENT_PACE_FILES;
    public static final SetSetting<String> READABLE_PACE_TYPES;
    private static final String FILE_NAME;

    private static final List<Setting> SETTINGS_LIST;

    static {
        FILE_NAME = "settings.ser";

        DISPLAY_24HOUR_TIME = new BooleanSetting("Show 24 hour time", false, APPEARANCE, TIME);
        SAVE_METHOD = new ChoiceSetting<>("Save Method", SaveMethod.A, GENERAL);
        AUTO_SAVE_SETTINGS = new BooleanSetting("Autosave Settings", true, SETTINGS);
        RECENT_PACE_FILES = new StackSetting<>("Recent Files", GENERAL);
        READABLE_PACE_TYPES = new SetSetting<>("Pace Types", SETTINGS);

        SETTINGS_LIST = new LinkedList<>() {{
            add(DISPLAY_24HOUR_TIME);
            add(AUTO_SAVE_SETTINGS);
        }};

        load();
    }

    private final Map<String, Setting<?>> settings;

    private Settings() {
        settings = new HashMap<>();

        for (Field field : getClass().getDeclaredFields()) {
            try {
                if (Modifier.isStatic(field.getModifiers()) && field.get(null) instanceof Setting<?> setting) {
                    settings.put(field.getName(), setting);
                }
            } catch (IllegalAccessException ignored) {
            }
        }
    }

    public static void load() {
        File file = getSettingsFile();
        if (file.exists()) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                ((Settings) objectInputStream.readObject()).loadSettings();
                objectInputStream.close();
            } catch (Exception e) {
            }
        }
    }

    private static File getSettingsFile() {
        File file = Resources.getUserConfigPath(FILE_NAME).toFile();
        if (file.getParentFile().mkdirs()) {
            System.out.println("Directory made");
        }
        return file;
    }

    private void loadSettings() {
        for (Field field : getClass().getDeclaredFields()) {
            try {
                if (Modifier.isStatic(field.getModifiers()) && field.get(null) instanceof Setting<?> setting) {
                    setting.tryCopy(settings.getOrDefault(field.getName(), null));
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }

    public static void save() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(getSettingsFile()));
            objectOutputStream.writeObject(new Settings());
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
