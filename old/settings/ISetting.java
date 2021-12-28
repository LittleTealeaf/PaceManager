package settings;

import interfaces.AutoSavable;
import interfaces.Category;
import interfaces.Valuable;

public interface ISetting<T> extends AutoSavable, Valuable<T> {

    boolean isCategory(Category... categories);

    Category[] getCategories();

    default T getValue() {
        return get();
    }

    T get();

    default void setValue(T value) {
        set(value);
    }

    void set(T value);

    default void autoSave() {
        if (Settings.AUTO_SAVE_SETTINGS.get()) {
            Settings.save();
        }
    }
}
