package classes;

public class Goal {
	//Division is required
	public String division;
	public Time time;
	
	public Goal(String div) {
		division = div;
		time = null;
	}
	public Goal(String div, Time tm) {
		division = div;
		time = tm;
	}
	
	public String getDivision() {
		return division;
	}
	public String getDisplayTime() {
		return time.toString();
	}
	
	public String toString() {
		return division + ": " + time.toString();
	}
}
