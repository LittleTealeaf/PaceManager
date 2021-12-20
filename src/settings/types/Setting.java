package settings.types;

import java.io.Serializable;

public abstract class Setting<T> implements Serializable {

    private T value;

    public Setting(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
