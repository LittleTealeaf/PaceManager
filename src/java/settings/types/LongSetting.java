package settings.types;

import settings.Category;

public class LongSetting extends Setting<Long> {

    public LongSetting(String name, Category... categories) {
        this(name, categories, 0L);
    }

    public LongSetting(String name, Category[] categories, Long value) {
        super(name, categories, value);
    }

    public LongSetting(String name, Category category, Long value) {
        super(name, category, value);
    }
}
