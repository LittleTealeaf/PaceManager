package settings.types;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import settings.Category;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class MapSetting<K, V> extends Setting<Map<K, V>> implements Map<K, V> {

    public MapSetting(String id, Category category) {
        this(id, category, new HashMap<>());
    }

    public MapSetting(String id, Category category, Map<K, V> value) {
        super(id, category, value);
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
        return get().put(key, value);
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
