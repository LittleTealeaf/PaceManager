package settings.abstracttype;

import interfaces.Category;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class ImplementsLinkedListSetting<T extends LinkedList<K>, K> extends ImplementsListSetting<T, K> implements Deque<K> {

    public ImplementsLinkedListSetting(String name, T value, Category... categories) {
        super(name, value, categories);
    }

    @Override
    public void addFirst(K k) {
        get().addFirst(k);
    }

    @Override
    public void addLast(K k) {
        get().addLast(k);
    }

    @Override
    public boolean offerFirst(K k) {
        return get().offerFirst(k);
    }

    @Override
    public boolean offerLast(K k) {
        return get().offerLast(k);
    }

    @Override
    public K removeFirst() {
        return get().removeFirst();
    }

    @Override
    public K removeLast() {
        return get().removeLast();
    }

    @Override
    public K pollFirst() {
        return get().pollFirst();
    }

    @Override
    public K pollLast() {
        return get().pollLast();
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
        return get().removeFirstOccurrence(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return get().removeLastOccurrence(o);
    }

    @Override
    public boolean offer(K k) {
        return get().offer(k);
    }

    @Override
    public K remove() {
        return get().remove();
    }

    @Override
    public K poll() {
        return get().poll();
    }

    @Override
    public K element() {
        return get().element();
    }

    @Override
    public K peek() {
        return get().peek();
    }

    @Override
    public void push(K k) {
        get().push(k);
    }

    @Override
    public K pop() {
        return get().pop();
    }

    @NotNull
    @Override
    public Iterator<K> descendingIterator() {
        return get().descendingIterator();
    }
}
