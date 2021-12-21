package settings.types;

import settings.Category;

import java.io.File;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class FileListSetting extends ListSetting<File> {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileListSetting(String id, Category category) {
        this(id, category, new ArrayList<>());
    }

    public FileListSetting(String id, Category category, List<File> value) {
        super(id, category, value);
    }
}
