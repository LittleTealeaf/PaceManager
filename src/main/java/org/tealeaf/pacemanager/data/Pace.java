package org.tealeaf.pacemanager.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pace {

    private String name;
    private final ObservableList<Division> divisions = FXCollections.observableArrayList();
    private final ObservableList<Team> teams = FXCollections.observableArrayList();


    public Pace() {

    }

    public Pace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Division> getDivisions() {
        return divisions;
    }

    public ObservableList<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public Team newTeam() {
        Team team = new Team();
        addTeam(team);
        return team;
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }

    public void addDivision(Division division) {
        divisions.add(division);
    }

    public void removeDivision(Division division) {
        divisions.remove(division);
        teams.parallelStream().filter(team -> division.getId().equals(team.getDivisionId())).forEach(team -> team.setDivision(null));
    }

    public Team getTeam(String id) {
        return teams.parallelStream().filter(team -> team.getId().equals(id)).findFirst().orElse(null);
    }

    public Division getDivision(String id) {
        return divisions.parallelStream().filter(division -> division.getId().equals(id)).findFirst().orElse(null);
    }
}
