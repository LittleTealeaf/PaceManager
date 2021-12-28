package settings.abstracttype;

import interfaces.Category;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;
import java.util.Iterator;

public abstract class ImplementsDequeSetting<T extends Deque<K>, K> extends ImplementsQueueSetting<T, K> implements Deque<K> {

    public ImplementsDequeSetting(String name, T value, Category... categories) {
        super(name, value, categories);
    }

    @Override
    public void addFirst(K k) {
        get().addFirst(k);
        autoSave();
    }

    @Override
    public void addLast(K k) {
        get().addLast(k);
        autoSave();
    }

    @Override
    public boolean offerFirst(K k) {
        return autoSave(get().offerFirst(k));
    }

    @Override
    public boolean offerLast(K k) {
        return autoSave(get().offerLast(k));
    }

    @Override
    public K removeFirst() {
        return autoSave(get().removeFirst());
    }

    @Override
    public K removeLast() {
        return autoSave(get().removeLast());
    }

    @Override
    public K pollFirst() {
        return autoSave(get().pollFirst());
    }

    @Override
    public K pollLast() {
        return autoSave(get().pollLast());
    }

    @Override
    public K getFirst() {
        return get().getFirst();
    }

    @Override
    public K getLast() {
        return get().getLast();
    }

    @Override
    public K peekFirst() {
        return get().peekFirst();
    }

    @Override
    public K peekLast() {
        return get().peekLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return autoSave(get().removeFirstOccurrence(o));
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return autoSave(get().removeLastOccurrence(o));
    }

    @Override
    public void push(K k) {
        get().push(k);
        autoSave();
    }

    @Override
    public K pop() {
        return autoSave(get().pop());
    }

    @NotNull
    @Override
    public Iterator<K> descendingIterator() {
        return get().descendingIterator();
    }
}
