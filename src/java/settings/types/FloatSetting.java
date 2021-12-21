package settings.types;

import settings.Category;

public class FloatSetting extends Setting<Float> {

    public FloatSetting(String name, Category... categories) {
        this(name, categories, 0F);
    }

    public FloatSetting(String name, Category[] categories, Float value) {
        super(name, categories, value);
    }

    public FloatSetting(String name, Category category, Float value) {
        super(name, category, value);
    }

    public void set(String string) {
        set(Float.parseFloat(string));
    }
}
