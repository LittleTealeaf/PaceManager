package settings.types;

import interfaces.Category;
import org.jetbrains.annotations.NotNull;
import settings.Setting;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OldListSetting<T> extends Setting<List<T>> implements List<T> {

    public OldListSetting(String name, List<T> value, Category... categories) {
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
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        return get().addAll(index,c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return get().removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return get().retainAll(c);
    }

    @Override
    public void clear() {
        get().clear();
    }

    @Override
    public T get(int index) {
        return get().get(index);
    }

    @Override
    public T set(int index, T element) {
        return get().set(index,element);
    }

    @Override
    public void add(int index, T element) {
        get().add(index,element);
    }

    @Override
    public T remove(int index) {
        return get().remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return get().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return get().lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator() {
        return get().listIterator();
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator(int index) {
        return get().listIterator(index);
    }

    @NotNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return get().subList(fromIndex,toIndex);
    }
}
