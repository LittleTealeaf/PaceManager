package data;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Thomas Kwashnak
 * @since 1.0.0
 * @version 1.0.0
 */
public class Team implements Serializable {

	private final UUID uuid;
	private String teamNumber;
	private String[] riders;
	private String notes;
	private Time startTime;
	private Time endTime;
	private boolean excluded = false;
	private transient Division division;
	private UUID divisionUUID;
	/**
	 * Only used when populating scoreboards
	 */
	private transient Time distanceToGoal;

	/**
	 * @since 1.0.0
	 */
	public Team() {
		uuid = UUID.randomUUID();
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public Division getDivision() {
		return division;
	}

	/**
	 * @since 1.0.0
	 * @param division
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
	 * @since 1.0.0
	 * @return
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
	 * @since 1.0.0
	 * @return
	 */
	public UUID getUUID() {
		return uuid;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public String getTeamNumber() {
		return teamNumber;
	}

	/**
	 * @since 1.0.0
	 * @param teamNumber
	 */
	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public String[] getRiders() {
		return riders;
	}

	/**
	 * @since 1.0.0
	 * @param riders
	 */
	public void setRiders(String[] riders) {
		this.riders = riders;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @since 1.0.0
	 * @param notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * @since 1.0.0
	 * @param startTime
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public Time getEndTime() {
		return endTime;
	}

	/**
	 * @since 1.0.0
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
	 * @since 1.0.0
	 */
	public Time getElapsedTime() {
		return !hasElapsed() ? null : Time.difference(startTime, endTime);
	}

	/**
	 * Checks whether or not an elapsed time can be calculated
	 *
	 * @since 1.0.0
	 * @return {@code false} if either {@code startTime} or {@code endTime} are {@code null}
	 */
	public boolean hasElapsed() {
		return startTime != null && endTime != null;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public Time getDistanceToGoal() {
		return distanceToGoal;
	}

	/**
	 * @since 1.0.0
	 * @param distanceToGoal
	 */
	public void setDistanceToGoal(Time distanceToGoal) {
		this.distanceToGoal = distanceToGoal;
	}

	/**
	 * @since 1.0.0
	 * @return
	 */
	public boolean isExcluded() {
		return excluded;
	}

	/**
	 * @since 1.0.0
	 * @param excluded
	 */
	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}
}
