package data;

public class Division {

	private String name;
	private Time goalTime;

	public Division() {

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

	public String toString() {
		return "Division " + getName();
	}
}
