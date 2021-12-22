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
    }

    public T get() {
        return value;
    }

    public Category[] getCategories() {
        return categories;
    }

    public boolean isCategory(Category... categories) {
        for(Category a : categories) {
            for(Category b : this.categories) {
                if(a.equals(b)) {
                    return true;
                }
            }
        }
        return false;
    }
}
