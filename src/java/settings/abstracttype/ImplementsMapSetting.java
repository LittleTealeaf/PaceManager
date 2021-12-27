package settings.abstracttype;

import interfaces.Category;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import settings.Setting;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class ImplementsMapSetting<T extends Map<K,V>,K,V> extends Setting<T> implements Map<K,V> {

    public ImplementsMapSetting(String name, T value, Category... categories) {
        super(name, value, categories);
    }

    @Override
    public int size() {
        return get().size();
    }

    @Override
    public boolean isEmpty() {
        return get().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return get().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return get().containsValue(value);
    }

    @Override
    public V get(Object key) {
        return get().get(key);
    }

    @Nullable
    @Override
    public V put(K key, V value) {
        return get().put(key,value);
    }

    @Override
    public V remove(Object key) {
        return get().remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {
        get().putAll(m);
    }

    @Override
    public void clear() {
        get().clear();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return get().keySet();
    }

    @NotNull
    @Override
    public Collection<V> values() {
        return get().values();
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return get().entrySet();
    }
}
