package settings.types;

import interfaces.Category;
import settings.Setting;

public class StringSetting extends Setting<String> {

    public StringSetting(String name, String value, Category... categories) {
        super(name, value, categories);
    }
}
