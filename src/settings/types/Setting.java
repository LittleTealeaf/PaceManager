package settings.types;

import settings.Category;

import java.io.Serializable;

public abstract class Setting<T> implements Serializable {

    private final String id;
    private T value;
    private final Category category;

    public Setting(String id, Category category, T value) {
        this.id = id;
        this.value = value;
        this.category = category;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }
}
