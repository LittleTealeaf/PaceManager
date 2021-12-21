package settings.types;

import settings.Category;

import java.io.File;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class FileListSetting extends ListSetting<File> {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileListSetting(String name, Category... categories) {
        this(name, categories, new ArrayList<>());
    }

    public FileListSetting(String name, Category[] categories, List<File> value) {
        super(name, categories, value);
    }

    public FileListSetting(String name, Category category, List<File> value) {
        super(name, category, value);
    }
}
