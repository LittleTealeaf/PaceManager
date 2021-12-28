package settings.types;

import interfaces.Category;
import settings.abstracttype.ImplementsCollectionSetting;

import java.util.LinkedHashSet;
import java.util.Set;

public class SetSetting<T> extends ImplementsCollectionSetting<Set<T>, T> implements Set<T> {

    public SetSetting(String name, Category... categories) {
        super(name, new LinkedHashSet<>(), categories);
    }

    public SetSetting(String name, Set<T> value, Category... categories) {
        super(name, value, categories);
    }
}
