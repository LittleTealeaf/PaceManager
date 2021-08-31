package data;

import java.io.Serializable;
import java.util.UUID;

public class Team implements Serializable {

	private final UUID uuid = UUID.randomUUID();
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


	public Team() {
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		//Removes itself from previous division
		if (this.division != null) {
			this.division.removeTeam(this);
		}
		this.division = division;
		division.addTeam(this);
	}

	public UUID getDivisionUUID() {
		return divisionUUID;
	}

	public void updateDivisionUUID() {
		divisionUUID = division == null ? null : division.getUUID();
	}

	public void clearDivisionUUID() {
		divisionUUID = null;
	}

	public UUID getUUID() {
		return uuid;
	}

	public String getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String[] getRiders() {
		return riders;
	}

	public void setRiders(String[] riders) {
		this.riders = riders;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

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
	 * Checks whether or not an elapsed time can be calculated
	 *
	 * @return {@code false} if either {@code startTime} or {@code endTime} are {@code null}
	 */
	public boolean hasElapsed() {
		return startTime != null && endTime != null;
	}

	public Time getDistanceToGoal() {
		return distanceToGoal;
	}

	public void setDistanceToGoal(Time distanceToGoal) {
		this.distanceToGoal = distanceToGoal;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}
}
