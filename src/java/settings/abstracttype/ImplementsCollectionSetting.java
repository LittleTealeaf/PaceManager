package settings.abstracttype;

import interfaces.Category;
import org.jetbrains.annotations.NotNull;
import settings.Setting;

import java.util.Collection;
import java.util.Iterator;

public abstract class ImplementsCollectionSetting<T extends Collection<K>, K> extends Setting<T> implements Collection<K> {

    public ImplementsCollectionSetting(String name, T value, Category... categories) {
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
    public boolean contains(Object o) {
        return get().contains(o);
    }

    @NotNull
    @Override
    public Iterator<K> iterator() {
        return get().iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return get().toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return get().toArray(a);
    }

    @Override
    public boolean add(K k) {
        return autoSave(get().add(k));
    }

    @Override
    public boolean remove(Object o) {
        return autoSave(get().remove(o));
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return get().containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends K> c) {
        return autoSave(get().addAll(c));
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return autoSave(get().removeAll(c));
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return autoSave(get().retainAll(c));
    }

    @Override
    public void clear() {
        get().clear();
        autoSave();
    }
}
