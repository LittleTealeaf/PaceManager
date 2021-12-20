package data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pace implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;

    private final List<Division> divisions;
    private final List<Team> teams;

    public Pace() {
        divisions = new ArrayList<>();
        teams = new ArrayList<>();
    }
}
