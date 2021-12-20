package data;

import serialization.SelfSerializer;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pace implements Serializable, SelfSerializer {

    @Serial
    private static final long serialVersionUID = 42L;

    private final List<Division> divisions;
    private final List<Team> teams;

    private transient File file;

    public Pace() {
        divisions = new ArrayList<>();
        teams = new ArrayList<>();
    }

    public Pace(File file) throws IOException, ClassNotFoundException {
        this();
        deserialize(file);
    }

    @Override
    public void postDeserialization() {
        teams.forEach(team -> team.lookupDivision(divisions));
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

    @Override
    public void from(Object other) {
        if (other instanceof Pace pace) {
            divisions.clear();
            teams.clear();
            divisions.addAll(pace.divisions);
            teams.addAll(pace.teams);
        }
    }

    //    public static Pace fromFile(File file) throws IOException, ClassNotFoundException {
//        FileInputStream fileStream = new FileInputStream(file);
//        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
//        Pace pace = (Pace) objectStream.readObject();
//        objectStream.close();
//        fileStream.close();
//        pace.postDeserialization();
//        return pace;
//    }

//    public void toFile(File file) throws IOException {
//        FileOutputStream fileStream = new FileOutputStream(file);
//        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
//        objectStream.writeObject(this);
//        objectStream.close();
//        fileStream.close();
//    }

    /*@Serial
    private void writeObject(ObjectOutputStream out) throws IOException {

    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

    }*/
}
