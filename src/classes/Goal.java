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
		if(time == null) return "";
		return time.toString(true);
	}
	
	public String toString() {
		String s = division;
		if(time != null) s+=": " + time.toString(true);
		return s;
	}
}
