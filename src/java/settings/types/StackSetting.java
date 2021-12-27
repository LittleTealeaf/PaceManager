package settings.types;

import interfaces.Category;
import settings.abstracttype.ImplementsListSetting;

import java.util.Stack;

public class StackSetting<T> extends ImplementsListSetting<Stack<T>, T> {

    public StackSetting(String name, Stack<T> value, Category... categories) {
        super(name, value, categories);
    }

    public StackSetting(String name, Category... categories) {
        super(name, new Stack<>(), categories);
    }

    public T push(T item) {
        return autoSave(get().push(item));
    }

    public synchronized T pop() {
        return autoSave(get().pop());
    }

    public boolean empty() {
        return autoSave(get().empty());
    }

    public synchronized int search(Object o) {
        return autoSave(get().search(o));
    }
}
