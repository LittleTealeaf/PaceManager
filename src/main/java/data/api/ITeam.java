package data.api;

import data.interfaces.PaceComponent;
import data.interfaces.Identifiable;
import java.util.Collection;

/**
 * Outlines the requirements that a team object must implement. This includes getters and setters for the name, division, start and end time, as well as riders
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
public interface ITeam extends Identifiable, PaceComponent {

    /**
     * Key values for variables saved within instances of this interface
     */
    String KEY_NAME = ITeam.class.getName() + ".name", KEY_DIVISION_UUID = ITeam.class.getName() + ".divisionUUID", KEY_START_TIME = ITeam.class.getName() + ".timeStart", KEY_END_TIME = ITeam.class.getName() + ".timeEnd", KEY_INCLUDED = ITeam.class.getName() + ".included";

    /**
     * Returns the name, or ID, of the team
     * @return Team name/id
     */
    String getName();
    /**
     * Sets the name or id of the team
     * @param name New name to give the team.
     */
    void setName(String name);

    /**
     * Gets the division object referencing the division that this team is categorized under.
     * @return Reference to the division this team is placed under
     */
    IDivision getDivision();

    /**
     * Sets the current division of the team
     * @param division The new division that the team should be listed under
     */
    void setDivision(IDivision division);

    /**
     * Sets the current division of the team
     * @param divisionUUID The UUID Of the division the team should be listed under
     */
    void setDivision(java.util.UUID divisionUUID);

    /**
     * The time that the riders began the pace
     * @return The clock time the riders left and began the pace. Returns {@code null} if the riders have not yet left
     */
    IClock getStartTime();

    /**
     * Sets the time that the riders began the pace
     * @param startTime The clock time that the team started the pace at
     */
    void setStartTime(IClock startTime);

    /**
     * The time that the riders finished the pace
     * @return The clock time that the riders returned from completing the pace. Returns {@code null} if the riders have not returned yet.
     */
    IClock getEndTime();

    /**
     * Sets the time that the riders finished the pace
     * @param endTime The clock time that the team finished the pace at.
     */
    void setEndTime(IClock endTime);

    /**
     * Gets the current status of the team
     * @return The current status, as listed in {@link Status}
     */
    Status getStatus();

    /**
     * Gets the elapsed time between the start time and the end time
     * @return Elapsed time that the team took to complete the pace. Returns {@code null} if the team has not completed the pace.
     */
    IClock getElapsedTime();

    Collection<IRider> getRiders();
    boolean addRider(IRider rider);
    boolean removeRider(IRider rider);

    boolean isIncluded();
    void setIncluded(boolean included);

    String getNotes();
    void setNotes(String notes);

    /**
     * Indicates the current status of the team, whether they've not started, in progress, or completing the pace.
     * @author Thomas Kwashnak
     */
    enum Status {
        /**
         * Indicates that the team has not yet begun the pace.
         */
        NOT_STARTED,
        /**
         * Indicates that the team is currently in progress of completing the pace
         */
        IN_PROGRESS,
        /**
         * Indicates that the team has completed the pace
         */
        COMPLETED;
    }
}
