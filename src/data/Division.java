package data;

import java.io.Serial;

public class Division extends DivisionPointer {

    @Serial
    private static final long serialVersionUID = 42L;

    public Division() {

    }

    @Override
    public Division asDivision() {
        return this;
    }

    @Override
    public DivisionPointer asDivisionPointer() {
        return new DivisionPointer(this);
    }
}
