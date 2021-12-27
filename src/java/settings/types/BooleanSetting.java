package settings.types;

import interfaces.Category;
import org.jetbrains.annotations.NotNull;
import settings.Setting;

public class BooleanSetting extends Setting<Boolean> implements Comparable<Boolean> {

    public BooleanSetting(String name, Boolean value, Category... categories) {
        super(name, value, categories);
    }

    @Override
    public int compareTo(@NotNull Boolean o) {
        return getValue().compareTo(o);
    }

    public void set(String value) {
        set(Boolean.parseBoolean(value));
    }
}
