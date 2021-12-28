package config;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import interfaces.AutoSavable;
import interfaces.Categorizable;
import interfaces.Category;
import interfaces.Valuable;

import java.lang.reflect.Type;

public class Setting<T> implements Valuable<T>, AutoSavable, Categorizable, JsonSerializer<Setting<T>>, JsonDeserializer<Setting<T>> {

    private T value;
    private transient String name;
    private transient Category[] categories;

    public Setting() {

    }

    public Setting(T value) {
        this.value = value;
    }

    public Setting(String name, T value, Category... categories) {
        this.name = name;
        this.value = value;
        this.categories = categories;
    }

    public Category[] getCategories() {
        return categories;
    }

    @Override
    public boolean isCategory(Category... categories) {
        if (categories != null) {
            for (Category a : categories) {
                for (Category b : this.categories) {
                    if (a == b) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public T getValue() {
        return get();
    }

    public T get() {
        return value;
    }

    @Override
    public void setValue(T value) {
        set(value);
    }

    public void set(T value) {
        this.value = value;
        autoSave();
    }

    public void autoSave() {

    }

    @Override
    public Setting<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Setting<>(context.deserialize(json, new TypeToken<T>() {}.getType()));
    }

    @Override
    public JsonElement serialize(Setting<T> src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.get());
    }
}
