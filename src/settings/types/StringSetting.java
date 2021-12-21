package settings.types;

import settings.Category;

import java.io.Serial;

public class StringSetting extends Setting<String> {

    @Serial
    private static final long serialVersionUID = 1L;

    public StringSetting(String id, Category category, String value) {
        super(id, category, value);
    }

    @Override
    public String toString() {
        return get();
    }
}
