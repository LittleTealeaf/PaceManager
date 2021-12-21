package settings.types;

import org.jetbrains.annotations.NotNull;
import settings.Category;

import java.io.Serial;

public class BooleanSetting extends Setting<Boolean> implements Comparable<Boolean> {

    @Serial
    private static final long serialVersionUID = 1L;

    public BooleanSetting(String id, Category category, Boolean value) {
        super(id, category, value);
    }

    @Override
    public int compareTo(@NotNull Boolean o) {
        return get().compareTo(o);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Boolean otherBoolean) {
            return get().equals(otherBoolean);
        } else if (other instanceof BooleanSetting booleanSetting) {
            return get().equals(booleanSetting.get());
        } else {
            return false;
        }
    }

    public boolean booleanValue() {
        return get();
    }
}
