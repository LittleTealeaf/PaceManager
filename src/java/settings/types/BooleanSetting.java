package settings.types;

import org.jetbrains.annotations.NotNull;
import settings.Category;

import java.io.Serial;

public class BooleanSetting extends Setting<Boolean> implements Comparable<Boolean> {

    @Serial
    private static final long serialVersionUID = 1L;

    public BooleanSetting(String name, Category category, Boolean value) {
        super(name, category, value);
    }

    public BooleanSetting(String name, Category[] categories, Boolean value) {
        super(name, categories, value);
    }

    @Override
    public int compareTo(@NotNull Boolean o) {
        return get().compareTo(o);
    }

    public void set(String string) {
        set(Boolean.parseBoolean(string));
    }
}
