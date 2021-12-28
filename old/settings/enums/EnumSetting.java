package settings.enums;

import interfaces.EnumStringable;

public interface EnumSetting<T extends Enum<T>> {

    default String displayString() {
        if (this instanceof EnumStringable enumStringable) {
            return enumStringable.asString();
        } else {
            return toString();
        }
    }
}
