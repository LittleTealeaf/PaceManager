package settings;

import interfaces.Category;
import interfaces.EnumStringable;

public enum SettingCategory implements EnumStringable, Category {
    GENERAL,
    TIME,
    APPEARANCE,
    SAVING,
    SETTINGS;

    private String string;

    @Override
    public String getName() {
        return toString();
    }

    public String toString() {
        return string == null ? string = asString() : string;
    }
}
