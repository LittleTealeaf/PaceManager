package app.database;

import api.database.IDivision;
import api.database.IPace;

public class Division implements IDivision {

    private transient IPace pace;

    @Override
    public String getName() {
        return null;
    }

    public void setPace(IPace pace) {
        this.pace = pace;
    }
}
