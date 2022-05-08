package org.tealeaf.pacemanager.database.dataobjects;

import org.tealeaf.pacemanager.database.PaceHandler;
import org.tealeaf.pacemanager.events.EventCoordinator;
import org.tealeaf.pacemanager.util.Closable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Pace implements Closable {

    private final List<Division> divisions = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();

    private final EventCoordinator eventCoordinator;

    public Pace(EventCoordinator eventCoordinator) {
        this.eventCoordinator = eventCoordinator;
        eventCoordinator.addListener(this);
        initialize();
    }

    public void initialize() {
        Stream.concat(divisions.parallelStream().map(i -> (PaceComponent) i), teams.parallelStream().map(i -> (PaceComponent) i)).parallel().forEach(
                item -> item.setPace(this));
    }

    public Division getDivision(String id) {
        return new ArrayList<>(divisions).parallelStream().filter(i -> i.getID().equals(id)).findFirst().orElse(null);
    }

    public Team getTeam(String id) {
        return new ArrayList<>(teams).parallelStream().filter(i -> i.getID().equals(id)).findFirst().orElse(null);
    }

    public List<Team> getTeams(Division division) {
        return getTeams(division.getID());
    }

    public List<Team> getTeams(String divisionId) {
        return new ArrayList<>(teams).parallelStream().filter(i -> i.getDivisionId().equals(divisionId)).toList();
    }

    public void addTeam(Team team) {
        synchronized (teams) {
            team.setPace(this);
            teams.add(team);
            eventCoordinator.runEvent(OnTeamAdded.class, i -> i.onTeamAdded(team));
        }
    }

    public void removeTeam(Team team) {
        synchronized (teams) {
            if (teams.remove(team)) {
                team.setPace(null);
                eventCoordinator.runEvent(OnTeamRemoved.class, i -> i.onTeamRemoved(team));
            }
        }
    }

    public void addDivision(Division division) {
        synchronized (divisions) {
            division.setPace(this);
            divisions.add(division);
            eventCoordinator.runEvent(OnDivisionAdded.class, i -> i.onDivisionAdded(division));
        }
    }

    public void removeDivision(Division division) {
        synchronized (divisions) {
            if(divisions.remove(division)) {
                division.setPace(null);
                eventCoordinator.runEvent(OnDivisionRemoved.class,i -> i.onDivisionRemoved(division));
            }
        }
    }

    public void onDivisionModified(Division division) {
        eventCoordinator.runEvent(OnDivisionModified.class,i -> i.onDivisionModified(division));
    }

    @Override
    public void close() {
        eventCoordinator.removeListener(this);
    }

    public interface OnTeamAdded {

        void onTeamAdded(Team team);
    }

    public interface OnTeamRemoved {

        void onTeamRemoved(Team team);
    }

    public interface OnDivisionAdded {

        void onDivisionAdded(Division division);
    }

    public interface OnDivisionModified {
        void onDivisionModified(Division division);
    }

    public interface OnDivisionRemoved {

        void onDivisionRemoved(Division division);
    }
}
