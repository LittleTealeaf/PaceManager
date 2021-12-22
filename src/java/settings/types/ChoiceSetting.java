package settings.types;

import interfaces.Category;
import settings.Setting;
import settings.enums.EnumSetting;

public class ChoiceSetting<T extends EnumSetting<?>> extends Setting<T> {

    public ChoiceSetting(String name, T value, Category... categories) {
        super(name, value, categories);
    }
}
