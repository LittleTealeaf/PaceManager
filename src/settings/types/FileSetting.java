package settings.types;

import settings.Category;

import java.io.File;
import java.io.Serial;

public class FileSetting extends Setting<File> {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileSetting(String id, Category category, File value) {
        super(id, category, value);
    }
}
