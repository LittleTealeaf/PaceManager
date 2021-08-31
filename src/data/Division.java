package data;

import java.io.Serializable;
import java.util.UUID;

public class Division implements Serializable {

	private String name;
	private Time goalTime;
	private final UUID uuid;

	public Division() {
		this.uuid = UUID.randomUUID();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getGoalTime() {
		return goalTime;
	}

	public void setGoalTime(Time goalTime) {
		this.goalTime = goalTime;
	}

	public UUID getUUID() {
		return uuid;
	}

	public String toString() {
		return "Division " + getName();
	}
}
