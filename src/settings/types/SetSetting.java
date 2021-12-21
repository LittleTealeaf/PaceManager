package settings.types;

import org.jetbrains.annotations.NotNull;
import settings.Category;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class SetSetting<T> extends Setting<Set<T>> implements Set<T> {

    public SetSetting(String id, Category category) {
        this(id, category, new HashSet<>());
    }

    public SetSetting(String id, Category category, Set<T> value) {
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
    public boolean contains(Object o) {
        return get().contains(o);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return get().iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return get().toArray();
    }

    @NotNull
    @Override
    public <T1> T1[] toArray(@NotNull T1[] a) {
        return get().toArray(a);
    }

    @Override
    public boolean add(T t) {
        return get().add(t);
    }

    @Override
    public boolean remove(Object o) {
        return get().remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return get().containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        return get().addAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return get().retainAll(c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return get().removeAll(c);
    }

    @Override
    public void clear() {
        get().clear();
    }
}
