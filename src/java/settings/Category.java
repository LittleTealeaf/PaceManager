package settings;

import interfaces.EnumStringable;

public enum Category implements EnumStringable {
    GENERAL,
    APPLICATION,
    CALCULATIONS,
    FILES;

    private String string;

    public String toString() {
        return string == null ? string = asString() : string;
    }
}
