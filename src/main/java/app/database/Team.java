package app.database;

import api.database.ITeam;

public class Team implements ITeam {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
