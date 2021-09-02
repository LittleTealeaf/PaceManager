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
    private String name;
    private Time goalTime;

    /**
     * Creates a new division with a unique UUID
     *
     * @see #uuid
     */
    public Division() {
        uuid = UUID.randomUUID();
        teams = new ArrayList<>();
    }

    /**
     * Returns the name of the Division
     *
     * @return Division Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Division
     *
     * @param name Division Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Goal Time is the time that all members within the division are aiming for
     *
     * @return Division's Goal Time
     */
    public Time getGoalTime() {
        return goalTime;
    }

    /**
     * Updates the division's goal time
     *
     * @param goalTime Division's Goal Time
     */
    public void setGoalTime(Time goalTime) {
        this.goalTime = goalTime;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }

    public void clearTeams() {
        teams.clear();
    }

    /**
     * Provides the unique identifier for the division
     *
     * @return The division's unique identifier
     * @see #uuid
     */
    public UUID getUUID() {
        return uuid;
    }

    public String toString() {
        return "Division " + getName();
    }

    /**
     * Does not include any that did not complete
     *
     * @return Array of all completed and non-excluded teams ordered by closeness to the average time. Returns {@code null} if there is no goal time
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

    public List<Team> getTeams() {
        return teams;
    }

    public Team[] getTeamsAsArray() {
        Team[] array = new Team[teams.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = teams.get(i);
        }
        return array;
    }
}
