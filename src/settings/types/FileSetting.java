package settings.types;

import settings.Category;

import java.io.File;
import java.io.Serial;

public class FileSetting extends Setting<File> {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileSetting(String name, Category... categories) {
        this(name, categories, null);
    }

    public FileSetting(String name, Category[] categories, File value) {
        super(name, categories, value);
    }

    public FileSetting(String name, Category category, File value) {
        super(name, category, value);
    }

    public boolean isNull() {
        return get() == null;
    }
}
