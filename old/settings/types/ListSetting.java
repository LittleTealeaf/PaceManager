package settings.types;

import interfaces.Category;
import settings.abstracttype.ImplementsListSetting;

import java.util.LinkedList;
import java.util.List;

public class ListSetting<T> extends ImplementsListSetting<List<T>, T> {

    public ListSetting(String name, Category... categories) {
        super(name, new LinkedList<>(), categories);
    }

    public ListSetting(String name, List<T> value, Category... categories) {
        super(name, value, categories);
    }
}
