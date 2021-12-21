package settings.types;

import settings.Category;

import java.io.Serial;

public class StringSetting extends Setting<String> {

    @Serial
    private static final long serialVersionUID = 1L;

    public StringSetting(String name, Category category, String value) {
        super(name, category, value);
    }

    public StringSetting(String name, Category... categories) {
        this(name, categories, "");
    }

    public StringSetting(String name, Category[] categories, String value) {
        super(name, categories, value);
    }
}
