package data;

import app.App;
import settings.Settings;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Division objects represent a given "division", or "section" of the pace. Each team chooses a specific division they
 * wish to compete in. For example, the 2021 Hunter Pace had the divisions <i>Pleasure, Hunt, Western, Junior</i>. Each
 * division has its own "optimum time", which winners are calculated off of. The goal of the pace is to get as close to
 * the optimum time as possible.
 *
 * <p>The optimum time of the division can be set in two ways. Firstly, a goal time can be specified to be a certain
 * time, either from a prior run or an estimated time based off of a trial run. Alternatively, the user can choose to
 * run off of the average (if a goal time is not specified), using the {@link Settings#useAverageAsGoalTime()} setting.
 *
 * <p>Each division has its own UUID. Each team has a field {@link Team#getDivisionUUID()} that stores their division's
 * UUID. When a pace is loaded, each team looks up their UUID with all given divisions, and then sets their reference
 * to the division. Additionally, each division keeps its own list of its teams, which is also populated during the
 * loading process. Whenever a team is added or removed from their division, this list is updated using the
 * {@link #addTeam(Team)} and {@link #removeTeam(Team)} methods. During saving, each team's division UUID is updated
 * such that they can be assigned at the next load.
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @see Team
 * @since 1.0.0
 */
public class Division {

    /**
     * Unique identifier of this division object. This is how Teams store which division they are in during serialization
     * without referencing the whole object.
     *
     * @see Team#getDivisionUUID()
     */
    private final UUID uuid;
    /**
     * List of team objects within the division.
     * <br>is transitive such that teams are not double copied in {@link Pace#serialize(Writer)}
     *
     * @see #addTeam(Team)
     * @see #removeTeam(Team)
     */
    private final transient List<Team> teams;
    /**
     * Display Name of the Division. Whenever the application needs to display a human-readable name for the division,
     * it uses this string.
     */
    private String name;
    /**
     * Optimum Time for the division
     */
    private Time goalTime;

    /**
     * Creates a new division. Assigns a random UUID and initializes the array list
     */
    public Division() {
        uuid = UUID.randomUUID();
        teams = new ArrayList<>();
    }

    /**
     * Creates a division with a given name. Assigns a random UUID, initializes the array list, and sets the name
     *
     * @param name Display name of the division.
     * @see #setName(String)
     */
    public Division(String name) {
        this();
        setName(name);
    }

    /**
     * Gets the human-readable display name of the division.
     *
     * @return Display name of the Division.
     * @see #setName(String)
     */
    public String getName() {
        return name;
    }

    /**
     * Registers the string to display in places as the "name" of the division. Whenever the program needs to display
     * the division, it uses this string
     *
     * @param name Display Name
     * @see #getName()
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the optimum time for the division. This value specifies the rankings and winners for the specified
     * division.
     *
     * @return Gets the optimum time for the division
     * @see #getUsedGoalTime()
     */
    public Time getGoalTime() {
        return goalTime;
    }

    /**
     * Registers the optimum time for the division. If set to {@code null}, then calculations will be based off of the
     * division's average if {@link Settings#useAverageAsGoalTime()} {@code = true}.
     *
     * @param goalTime Optimum time for the division. {@code null} if removing goal time
     */
    public void setGoalTime(Time goalTime) {
        this.goalTime = goalTime;
    }

    /**
     * Fetches the calculation optimum time for the division. If {@link #goalTime} = {@code null}, this will check
     * if the user has specified to use averages as goal times. If so, will return the average goal time, or return
     * {@code null} if the setting is set to false.
     *
     * @return <b>{@code goalTime}</b> if {@code goalTime != null}<p><b>{@link #getAverageTime()}</b> if
     * {@code goalTime = null}and {@link Settings#useAverageAsGoalTime()} {@code = true}</p><p><b>{@code null}</b>
     * otherwise.</p>
     * @see Settings#useAverageAsGoalTime()
     * @see #getGoalTime()
     */
    public Time getUsedGoalTime() {
        return (getGoalTime() != null) ? getGoalTime() : App.settings.useAverageAsGoalTime() ? getAverageTime() : null;
    }

    //TODO maybe make this set the team's division?

    /**
     * Adds a new team to the division.
     * <p>Does not modify the team or check if the team is set to the division in any way.</p>
     *
     * @param team Team to add to the division
     */
    public void addTeam(Team team) {
        teams.add(team);
    }

    /**
     * Removes a team from the division
     *
     * @param team Team to remove from the division
     * @return {@code true} if the team was able to remove, {@code false} otherwise
     * @see List#remove(Object)
     */
    public boolean removeTeam(Team team) {
        return teams.remove(team);
    }

    /**
     * Clears all teams from the division list. Does not specifically remove each division from the list. Used when
     * updating each team's specific division.
     */
    public void clearTeamsShallow() {
        teams.clear();
    }

    /**
     * Gets the division UUID
     *
     * @return Unique Identifier of the division
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * String representation of the division
     *
     * @return Division's name
     * @see #getName()
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
     */
    public Team[] getPlaceOrder() {
        if (getGoalTime() == null) {
            return null;
        }

        //Get number of places, and update their distance-to-goal time
        int count = 0;
        for (Team team : teams) {
            if (team.hasElapsed() && !team.isExcluded()) {
                count++;
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
        Arrays.sort(standings, (a, b) ->
                a.getDistanceToGoal().compareTo(b.getDistanceToGoal())
        );


        return standings;
    }

    /**
     * Gets all the teams listed in the division
     *
     * @return List of teams recorded in the division
     */
    public List<Team> getTeams() {
        return teams;
    }

    public Time getAverageTime() {
        long sum = 0, outLow = -1, outHigh = -1;
        int count = 0;
        for (Team team : teams) {
            Time elapsed = team.getElapsedTime();
            if (elapsed != null && !team.isExcluded()) {
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
