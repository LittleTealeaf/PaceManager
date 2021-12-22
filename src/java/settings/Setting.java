package settings;

import interfaces.Category;
import interfaces.Valuable;

import java.io.Serializable;

public abstract class Setting<T> implements Serializable, Valuable<T> {

    private final transient String name;
    private final transient Category[] categories;
    private T value;

    public Setting(String name, T value, Category... categories) {
        this.name = name;
        this.value = value;
        this.categories = categories;
    }

    @Override
    public T getValue() {
        return get();
    }

    @Override
    public void setValue(T value) {
        set(value);
    }

    public void set(T value) {
        this.value = value;
        autoSave();
    }

    protected void autoSave() {
        if (Settings.AUTO_SAVE_SETTINGS.get()) {
            Settings.save();
        }
    }

    public T get() {
        return value;
    }

    protected <K> K autoSave(K returnValue) {
        autoSave();
        return returnValue;
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
