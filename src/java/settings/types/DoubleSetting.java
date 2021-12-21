package settings.types;

import settings.Category;

public class DoubleSetting extends Setting<Double> {

    public DoubleSetting(String name, Category... categories) {
        this(name, categories, 0D);
    }

    public DoubleSetting(String name, Category[] categories, Double value) {
        super(name, categories, value);
    }

    public DoubleSetting(String name, Category category, Double value) {
        super(name, category, value);
    }

    public void set(String string) {
        set(Double.parseDouble(string));
    }
}
