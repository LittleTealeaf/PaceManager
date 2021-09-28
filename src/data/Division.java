package data;

import app.App;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class representing all data for a given Division
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Division {

    /**
     * Unique identifier of this division object
     *
     * @see #getUUID()
     * @since 1.0.0
     */
    private final UUID uuid;
    /**
     * List of team objects within the division.
     *
     * @apiNote is transitive such that teams are not double copied in {@link Pace#serialize(Writer)}
     * @see #addTeam(Team)
     * @see #removeTeam(Team)
     * @since 1.0.0
     */
    private final transient List<Team> teams;
    /**
     * Display Name of the Division
     *
     * @since 1.0.0
     */
    private String name;
    /**
     * Optimum Time for the division
     *
     * @since 1.0.0
     */
    private Time goalTime;

    /**
     * Creates a new division
     *
     * @since 1.0.0
     */
    public Division() {
        uuid = UUID.randomUUID();
        teams = new ArrayList<>();
    }

    /**
     * Creates a division with a given name
     *
     * @param name Display Name of the Division
     * @since 1.0.0
     */
    public Division(String name) {
        this();
        this.name = name;
    }

    /**
     * Gets the display name
     *
     * @return Display name of the Division
     * @since 1.0.0
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the display name
     *
     * @param name new Display Name for the division
     * @since 1.0.0
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the goal time
     *
     * @return Gets the optimum time for the division
     * @since 1.0.0
     */
    public Time getGoalTime() {
        return goalTime;
    }

    /**
     * Sets the goal time
     *
     * @param goalTime Optimum time for the division
     * @since 1.0.0
     */
    public void setGoalTime(Time goalTime) {
        this.goalTime = goalTime;
    }

    //TODO maybe make this set the team's division?

    /**
     * Adds a new team to the division.
     * <p>Does not modify the team or check if the team is set to the division in any way.</p>
     *
     * @param team Team to add to the division
     * @since 1.0.0
     */
    public void addTeam(Team team) {
        teams.add(team);
    }

    /**
     * Moves all teams from the provided division into this division.
     * Removes all teams from the specified division respectfully
     *
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
     * Removes a team from the division
     *
     * @param team Team to remove from the division
     * @return {@code true} if the team was able to be removed, {@code false} otherwise
     * @see List#remove(Object)
     * @since 1.0.0
     */
    public boolean removeTeam(Team team) {
        return teams.remove(team);
    }

    /**
     * Clears all teams from the division list
     *
     * @since 1.0.0
     */
    public void clearTeams() {
        teams.clear();
    }

    /**
     * Gets the division UUID
     *
     * @return Unique Identifier of the division
     * @since 1.0.0
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * String representation of the division
     *
     * @return Division's name
     * @see #getName()
     * @since 1.0.0
     */
    public String toString() {
        return getName();
    }

    /**
     * Obtains a list of teams in the division who have completed and are eligible for a place, in order from closest
     * to the optimum time to farthest from the optimum time
     *
     * @return Array List of eligible teams in order of closeness to the goal time
     * @see #goalTime
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
     * Gets all the teams listed in the division
     *
     * @return List of teams recorded in the division
     * @since 1.0.0
     */
    public List<Team> getTeams() {
        return teams;
    }

    public Time getAverageTime() {
        long sum = 0;
        int count = 0;
        long outLow = -1;
        long outHigh = -1;
        for (Team team : teams) {
            Time elapsed = team.getElapsedTime();
            if (elapsed != null) {
                long elapsedTime = elapsed.getValue();
                sum += elapsedTime;
                count++;
                if (elapsedTime < outLow || outLow == -1) {
                    outLow = elapsedTime;
                }
                if (elapsedTime > outHigh || outHigh == -1) {
                    outHigh = elapsedTime;
                }
            }
        }
        if (App.settings.excludeOutliers() && count > 2) {
            sum -= outHigh + outLow;
            count -= 2;
        }
        return count == 0 ? null : new Time(sum / count);
    }

    public boolean equals(Object other) {
        return other instanceof Division && ((Division) other).uuid.equals(uuid);
    }
}
