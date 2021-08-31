package data;

import java.util.UUID;

/**
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Division {

	private String name;
	private Time goalTime;
	private final UUID uuid;

	/**
	 * Creates a new division with a unique UUID
	 */
	public Division() {
		this.uuid = UUID.randomUUID();
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

	/**
	 * Provides the unique identifier for the division
	 *
	 * @return The division's unique identifier
	 */
	public UUID getUUID() {
		return uuid;
	}

	public String toString() {
		return "Division " + getName();
	}
}
