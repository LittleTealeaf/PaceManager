package settings.types;

import settings.Category;

import java.io.Serial;

public class IntSetting extends Setting<Integer> {

    @Serial
    private static final long serialVersionUID = 1L;

    public IntSetting(String name, Category... categories) {
        this(name, categories, 0);
    }

    public IntSetting(String name, Category[] categories, Integer value) {
        super(name, categories, value);
    }

    public IntSetting(String name, Category category, Integer value) {
        super(name, category, value);
    }

    public void set(String string) {
        set(Integer.parseInt(string));
    }

    public void set(String string, int radix) {
        set(Integer.parseInt(string, radix));
    }
}
