package data;

import serialization.Fileable;
import serialization.SelfSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas Kwashnak
 */
public class Pace implements Serializable, Fileable, SelfSerializer {

    @Serial
    private static final long serialVersionUID = 1101L;

    private final List<Division> divisions;
    private final List<Team> teams;

    private transient File file;

    public Pace() {
        divisions = new ArrayList<>();
        teams = new ArrayList<>();
    }

    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        teams.forEach(team -> team.lookupDivision(divisions));
    }
}
