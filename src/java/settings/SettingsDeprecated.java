package settings;

import serialization.FileSelfSerializer;
import serialization.Fileable;

import java.io.File;

public class SettingsDeprecated implements FileSelfSerializer<SettingsDeprecated>, Fileable {

    private transient File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
