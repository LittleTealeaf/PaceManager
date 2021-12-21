package settings.types;

import interfaces.Valuable;
import settings.Category;

import java.io.Serializable;
import java.util.Arrays;

/**
 * An abstract method that provides method dictations, parameters, and methods a Setting class should have. This class provides the following
 * implementations:
 * <ul><li>Setting value, of type {@code T}, including methods to {@link #get() get} and {@link #set(Object) set} the value</li>
 * <li>Setting name. Initialized in constructors, and represents the official name for the setting.</li>
 * <li>Categories the setting falls under, including methods to {@link #getCategories() get} the categories, as well as to check if the setting
 * falls under any category in a provided set.
 * </li></ul>
 * @author Thomas Kwashnak
 * @param <T> Type of value of the setting
 */
public abstract class Setting<T> implements Serializable, Valuable<T> {

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

    /**
     * Sets the current value to the provided value.
     * @param value Value that the setting should be set to.
     */
    public void set(T value) {
        this.value = value;
    }

    /**
     * Gets the current value of the setting
     * @return The current setting value
     */
    public T get() {
        return value;
    }

    /**
     * Gets the current list of categories that the setting falls under.
     * @return {@code Category[]} array of all categories this setting falls under.
     */
    public Category[] getCategories() {
        return categories;
    }

    /**
     * Checks if the setting contains any of the categories provided
     * @param categories List of categories (as {@code Category[]} or as independent parameters), where this method should return {@code true} if
     *                   it is a part of at least one of them.
     * @return {@code true} if this setting is a part of at least one of the given categories. {@code false} if the setting is not a part of any
     * categories listed, or no categories were provided.
     */
    public boolean isCategory(Category... categories) {
        return isCategory(Arrays.asList(categories));
    }

    /**
     * Checks if the setting contains any of the categories provided
     * @param categories An iterable object of categories.
     * @return {@code true} if this setting is a part of at least one of the given categories. {@code false} if the setting is not a part of any
     * categories listed, or no categories were provided.
     */
    public boolean isCategory(Iterable<Category> categories) {
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

    /**
     * Returns the setting name
     * @return Setting name as a String object
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current value of the setting
     * @return The current setting value
     * @see #get()
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the current value to the provided value.
     * @param value Value that the setting should be set to.
     * @see #set(Object)
     */
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, get().toString());
    }
}
