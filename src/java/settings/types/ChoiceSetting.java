package settings.types;

import interfaces.Category;
import settings.EditableSetting;
import settings.Setting;
import settings.enums.EnumSetting;

public class ChoiceSetting<T extends EnumSetting<?>> extends Setting<T> implements EditableSetting {

    public ChoiceSetting(String name, T value, Category... categories) {
        super(name, value, categories);
    }
}
