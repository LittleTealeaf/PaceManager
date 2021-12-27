package settings.abstracttype;

import interfaces.Category;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public abstract class ImplementsListSetting<T extends List<K>, K> extends ImplementsCollectionSetting<T, K> implements List<K> {

    public ImplementsListSetting(String name, T value, Category... categories) {
        super(name, value, categories);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends K> c) {
        return get().addAll(index, c);
    }

    @Override
    public K get(int index) {
        return get().get(index);
    }

    @Override
    public K set(int index, K element) {
        return get().set(index, element);
    }

    @Override
    public void add(int index, K element) {
        get().add(index, element);
    }

    @Override
    public K remove(int index) {
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
    public ListIterator<K> listIterator() {
        return get().listIterator();
    }

    @NotNull
    @Override
    public ListIterator<K> listIterator(int index) {
        return get().listIterator(index);
    }

    @NotNull
    @Override
    public List<K> subList(int fromIndex, int toIndex) {
        return get().subList(fromIndex, toIndex);
    }
}
