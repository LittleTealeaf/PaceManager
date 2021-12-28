package config;

import interfaces.Category;
import interfaces.EnumStringable;

public enum ConfigCategory implements EnumStringable, Category {
    GENERAL,
    SETTINGS,
    SAVE,
    TIME,
    APPEARANCE;

    private final String string;

    ConfigCategory() {
        string = asString();
    }

    public String toString() {
        return string == null ? super.toString() : string;
    }

    @Override
    public String getName() {
        return string;
    }
}
