package settings;

import serialization.Fileable;

import java.io.File;
import java.io.Serializable;

public class Settings implements Serializable, Fileable {

    private transient File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
