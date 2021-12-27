package settings.abstracttype;

import interfaces.Category;

import java.util.Queue;

public abstract class ImplementsQueueSetting<T extends Queue<K>, K> extends ImplementsCollectionSetting<T, K> implements Queue<K> {

    public ImplementsQueueSetting(String name, T value, Category... categories) {
        super(name, value, categories);
    }

    @Override
    public boolean offer(K k) {
        return autoSave(get().offer(k));
    }

    @Override
    public K remove() {
        return autoSave(get().remove());
    }

    @Override
    public K poll() {
        return autoSave(get().poll());
    }

    @Override
    public K element() {
        return get().element();
    }

    @Override
    public K peek() {
        return get().peek();
    }
}
