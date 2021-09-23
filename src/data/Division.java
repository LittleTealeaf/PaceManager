package data;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Division {

    /**
     * Unique identifier of this division object
     *
     * @see #getUUID()
     */
    private final UUID uuid;
    /**
     * List of team objects within the division.
     *
     * @apiNote is transitive such that teams are not double copied in {@link Pace#serialize(Writer)}
     * @see #addTeam(Team)
     * @see #removeTeam(Team)
     */
    private final transient List<Team> teams;
    /**
     * Display Name of the Division
     */
    private String name;
    /**
     * Optimum Time for the division
     */
    private Time goalTime;

    /**
     * Creates a new division
     * @since 1.0.0
     */
    public Division() {
        uuid = UUID.randomUUID();
        teams = new ArrayList<>();
    }

    /**
     * Creates a division with a given name
     * @param name Display Name of the Division
     */
    public Division(String name) {
        this();
        this.name = name;
    }

    /**
     * @return Display name of the Division
     * @since 1.0.0
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the display name of the division
     * @param name new Display Name for the division
     * @since 1.0.0
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public Time getGoalTime() {
        return goalTime;
    }

    /**
     * @param goalTime
     * @since 1.0.0
     */
    public void setGoalTime(Time goalTime) {
        this.goalTime = goalTime;
    }

    /**
     * @param team
     * @since 1.0.0
     */
    public void addTeam(Team team) {
        teams.add(team);
    }

    /**
     * Moves all teams from the provided division into this division.
     * Removes all teams from the specified division respectfully
     * @param division Division to "steal" all teams from
     */
    public void importTeamsFrom(Division division) {
        for (Team team : division.getTeams()) {
            team.setDivision(this);
            addTeam(team);
            division.removeTeam(team);
        }
    }

    /**
     * @param team
     * @since 1.0.0
     */
    public void removeTeam(Team team) {
        teams.remove(team);
    }

    /**
     * @since 1.0.0
     */
    public void clearTeams() {
        teams.clear();
    }

    /**
     * @return
     * @since 1.0.0
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public String toString() {
        return getName();
    }

    /**
     * @return
     * @since 1.0.0
     */
    public Team[] getPlaceOrder() {
        if (goalTime == null) {
            return null;
        }

        //Get number of places, and update their distance-to-goal time
        int count = 0;
        for (Team team : teams) {
            if (team.hasElapsed() && !team.isExcluded()) {
                count++;
                team.setDistanceToGoal(Time.difference(team.getElapsedTime(), goalTime).absolute());
            }
        }

        //Creates empty Team array and populates with the valid teams
        Team[] standings = new Team[count];
        count--;
        for (int i = 0; i < teams.size() && count >= 0; i++) {
            Team t = teams.get(i);
            if (t.hasElapsed() && !t.isExcluded()) {
                standings[count--] = t;
            }
        }

        //Sorting using a quick bubble sort
        //TODO implement better sorting method
        boolean edited;
        for (int i = 0; i < standings.length; i++) {
            edited = false;
            for (int j = 0; j < standings.length - i - 1; j++) {
                if (standings[i].getDistanceToGoal().compareTo(standings[i + 1].getDistanceToGoal()) == 1) {
                    Team tmp = standings[i];
                    standings[i] = standings[i + 1];
                    standings[i + 1] = tmp;
                    edited = true;
                }
            }
            if (!edited) {
                break;
            }
        }

        return standings;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public Team[] getTeamsAsArray() {
        Team[] array = new Team[teams.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = teams.get(i);
        }
        return array;
    }
}
