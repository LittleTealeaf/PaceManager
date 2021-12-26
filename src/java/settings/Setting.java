package settings;

import interfaces.Category;

import java.io.Serializable;

public abstract class Setting<T> implements Serializable, ISetting<T> {

    private final transient String name;
    private final transient Category[] categories;
    private T value;

    public Setting(String name, T value, Category... categories) {
        this.name = name;
        this.value = value;
        this.categories = categories;
    }

    public void set(T value) {
        this.value = value;
        autoSave();
    }

    public T get() {
        return value;
    }

    public boolean isCategory(Category... categories) {
        if (categories != null) {
            for (Category a : categories) {
                for (Category b : this.categories) {
                    if (a.equals(b)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Category[] getCategories() {
        return categories;
    }

    @SuppressWarnings("unchecked")
    void tryCopy(Object other) {
        if (other != null) {
            try {
                set(((Setting<T>) getClass().cast(other)).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
