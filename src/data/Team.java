package data;

import java.io.Serializable;
import java.util.UUID;

//TODO update javadocs

/**
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Team implements Serializable {

    /**
     * Unique team identifier, used to differentiate between teams, even if the team name is the same
     */
    private final UUID uuid;
    /**
     * Team identifier / name / number
     */
    private String teamName;
    /**
     * String array of the teams riders, each entry represents a different rider
     */
    private String[] riders;
    /**
     * String of any notes included, uses \\n to depict new lines
     */
    private String notes;
    /**
     * The team's starting time
     */
    private Time startTime;
    /**
     * The team's ending time
     */
    private Time endTime;
    /**
     * Whether the team should be manually excluded from final results even if they finished the pace
     */
    private Boolean excluded;
    /**
     * Team's specified division to be placed in
     */
    private transient Division division;
    /**
     * Divisions' UUID, used when storing in JSON file formats to save memory. A lookup is used during file load to
     * populate {@link #division} according to this value
     */
    private UUID divisionUUID;

    /**
     * Creates a new team and assigns that team its unique identifier
     *
     * @see #uuid
     */
    public Team() {
        uuid = UUID.randomUUID();
    }

    /**
     * Gets the reference to the team's division
     *
     * @return Division the team is competing in
     */
    public Division getDivision() {
        return division;
    }

    /**
     * Updates the team's division that it is to compete in. Removes the team from the previous division's team list
     * if the team was previously in a division.
     *
     * @param division Division the team is to compete in
     */
    public void setDivision(Division division) {
        //Removes itself from previous division
        if (this.division != null) {
            this.division.removeTeam(this);
        }
        this.division = division;
        if (division != null) {
            division.addTeam(this);
        }
    }

    /**
     * @return
     */
    public UUID getDivisionUUID() {
        return divisionUUID;
    }

    /**
     *
     */
    public void updateDivisionUUID() {
        divisionUUID = division == null ? null : division.getUUID();
    }

    /**
     *
     */
    public void clearDivisionUUID() {
        divisionUUID = null;
    }

    /**
     * @return
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * @return
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return
     */
    public String[] getRiders() {
        return riders;
    }

    /**
     * @param riders
     */
    public void setRiders(String[] riders) {
        this.riders = riders;
    }

    /**
     * @return
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * @return
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * Calculates the overall time that the team took to complete the pace
     *
     * @return {@code null} if either {@code startTime} or {@code endTime} are {@code null},
     * otherwise returns a new {@link Time} object representing the time elapsed between {@code startTime}
     * and {@code endTime}.
     */
    public Time getElapsedTime() {
        return !hasElapsed() ? null : Time.difference(startTime, endTime);
    }

    /**
     * Checks whether an elapsed time can be calculated
     *
     * @return {@code false} if either {@code startTime} or {@code endTime} are {@code null}
     */
    public boolean hasElapsed() {
        return startTime != null && endTime != null;
    }

    public Time getDistanceToGoal() {
        if (division == null || division.getGoalTime() == null || !isCompleted()) {
            return null;
        } else {
            return division.getGoalTime().subtract(getElapsedTime()).absolute();
        }
    }

    /**
     * @return
     */
    public boolean isExcluded() {
        return excluded != null && excluded;
    }

    /**
     * @param excluded
     */
    public void setExcluded(boolean excluded) {
        this.excluded = excluded ? true : null;
    }

    /**
     * Checks if the team is eligible for final times
     *
     * @return {@code true} if the team has an elapsed time (meaning that they have a start time and an end time), and they are not excluded
     * for any reason
     */
    public boolean isCompleted() {
        return !isExcluded() && hasElapsed();
    }

    /**
     * @return Number of riders in the team
     */
    public int getNumberOfRiders() {
        return riders.length;
    }

    public String getRidersString() {
        if (riders != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < riders.length; i++) {
                builder.append(riders[i]);
                if (i < riders.length - 1) {
                    builder.append("\n");
                }
            }
            return builder.toString();
        } else {
            return "";
        }
    }

    public String getStartString() {
        return startTime == null ? "-" : startTime.toString();
    }

    public String getEndString() {
        return endTime == null ? "-" : endTime.toString();
    }

    public String getElapsedString() {
        Time elapsed = getElapsedTime();
        return elapsed == null ? "-" : elapsed.toString();
    }

    public String getNotesDisplay() {
        StringBuilder builder = new StringBuilder();
        if (isExcluded()) {
            builder.append("[Excluded from placements]\n");
        }
        if (notes != null) {
            String n = notes;
            while (n.contains("\n\n")) {
                n = n.replace("\n\n", "\n");
            }
            builder.append(n);
        }
        return builder.toString();
    }

    public String toString() {
        return "Team: " + teamName;
    }
}
