package settings.types;

import settings.Category;

import java.io.Serial;

public class IntSetting extends Setting<Integer> {

    @Serial
    private static final long serialVersionUID = 1L;

    public IntSetting(String id, Category category, Integer value) {
        super(id, category, value);
    }

    public void set(String string) {
        set(Integer.parseInt(string));
    }

    public void set(String string, int radix) {
        set(Integer.parseInt(string, radix));
    }

    @Override
    public boolean equals(Object other) {
        return get().equals(other);
    }

    @Override
    public String toString() {
        return get().toString();
    }
}
