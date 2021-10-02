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
     * @since 1.0.0
     */
    private final UUID uuid;
    /**
     * @since 1.0.0
     */
    private String teamNumber;
    /**
     * @since 1.0.0
     */
    private String[] riders;
    /**
     * @since 1.0.0
     */
    private String notes;
    /**
     * @since 1.0.0
     */
    private Time startTime;
    /**
     * @since 1.0.0
     */
    private Time endTime;
    /**
     * @since 1.0.0
     */
    private Boolean excluded;
    /**
     * @since 1.0.0
     */
    private transient Division division;
    /**
     * @since 1.0.0
     */
    private UUID divisionUUID;

    /**
     * @since 1.0.0
     */
    public Team() {
        uuid = UUID.randomUUID();
    }

    /**
     * @return
     * @since 1.0.0
     */
    public Division getDivision() {
        return division;
    }

    /**
     * @param division
     * @since 1.0.0
     */
    public void setDivision(Division division) {
        //Removes itself from previous division
        if (this.division != null) {
            this.division.removeTeam(this);
        }
        this.division = division;
        division.addTeam(this);
    }

    /**
     * @return
     * @since 1.0.0
     */
    public UUID getDivisionUUID() {
        return divisionUUID;
    }

    /**
     * @since 1.0.0
     */
    public void updateDivisionUUID() {
        divisionUUID = division == null ? null : division.getUUID();
    }

    /**
     * @since 1.0.0
     */
    public void clearDivisionUUID() {
        divisionUUID = null;
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
    public String getTeamNumber() {
        return teamNumber;
    }

    /**
     * @param teamNumber
     * @since 1.0.0
     */
    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public String[] getRiders() {
        return riders;
    }

    /**
     * @param riders
     * @since 1.0.0
     */
    public void setRiders(String[] riders) {
        this.riders = riders;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes
     * @since 1.0.0
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     * @since 1.0.0
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * @return
     * @since 1.0.0
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     * @since 1.0.0
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
     * @since 1.0.0
     */
    public Time getElapsedTime() {
        return !hasElapsed() ? null : Time.difference(startTime, endTime);
    }

    /**
     * Checks whether an elapsed time can be calculated
     *
     * @return {@code false} if either {@code startTime} or {@code endTime} are {@code null}
     * @since 1.0.0
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
     * @since 1.0.0
     */
    public boolean isExcluded() {
        return excluded != null && excluded;
    }

    /**
     * @param excluded
     * @since 1.0.0
     */
    public void setExcluded(boolean excluded) {
        this.excluded = excluded ? true : null;
    }

    /**
     * Checks if the team is eligible for final times
     *
     * @return {@code true} if the team has an elapsed time (meaning that they have a start time and an end time), and they are not excluded
     * for any reason
     * @since 1.0.0
     */
    public boolean isCompleted() {
        return !isExcluded() && hasElapsed();
    }

    /**
     * @return Number of riders in the team
     * @since 1.0.0
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
        return "Team: " + teamNumber;
    }
}
