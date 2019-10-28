package classes;

public class util {
	public static String readablePlace(int position) {
		if(position == 1) return "1st";
		else if(position == 2) return "2nd";
		else if(position == 3) return "3rd";
		else if(position >= 4 && position <= 20) return position + "th";
		else return position + "";
	}
}
