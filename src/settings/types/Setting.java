package settings.types;

import settings.Category;

import java.io.Serializable;

public abstract class Setting<T> implements Serializable {

    private final String name;
    private T value;
    private final Category[] categories;

    public Setting(String name, Category category, T value) {
        this(name, new Category[]{category}, value);
    }

    public Setting(String name, Category[] categories, T value) {
        this.name = name;
        this.value = value;
        this.categories = categories;
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
        for (Category settingCategory : this.categories) {
            for (Category queryCategory : categories) {
                if (settingCategory.equals(queryCategory)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Setting<?> setting = (Setting<?>) o;

        if (getName() != null ? !getName().equals(setting.getName()) : setting.getName() != null) return false;
        return getValue() != null ? getValue().equals(setting.getValue()) : setting.getValue() == null;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, get().toString());
    }
}
