package classes;

import java.util.List;
import java.util.ArrayList;

public class Team {
	
	public String team;
	public List<String> names;
	public String division;
	public List<String> notes;
	public Time start;
	public Time finish;
	
	private void construct(String teamName, String div, List<String> nms, Time Start, Time Finish, List<String> note) {
		team = teamName;
		division = div;
		names = nms;
		start = Start;
		finish = Finish;
		notes = note;
		if(notes == null) notes = new ArrayList<String>();
		if(names == null) names = new ArrayList<String>();
	}
	
	public Team() {
		construct("","",null,null,null,null);
	}
	public Team(String tm, String div) {
		construct(tm,div,null,null,null,null);
	}
	public Team(String tm, String div, List<String> riders, List<String> note) {
		construct(tm,div,riders,null,null,note);
	}
	public Team(String tm, String div, List<String> riders, Time start, Time finish) {
		construct(tm,div,riders,start,finish,null);
	}
	public Team(String tm, String div, List<String> riders, Time start, Time finish, List<String> note) {
		construct(tm,div,riders,start,finish,note);
	}
	
	public Time elapsed() {
		Time r = new Time();
		if(start == null || finish == null) {
			r.error = 2;
		} else r.time = finish.time - start.time;
		return r;
	}
	
	public String toString() {
		String r = team;
		r+=" | " + division;
		r+=" | " + names;
		if(start != null) r += " | Start " + start.toString();
		if(finish != null) r +=" | Finish " + finish.toString();
		return r;
	}
	
	// Integration for fxMain
	public String getTeam() {
		return team;
	}
	public String getDivision() {
		return division;
	}
	public String getNames() {
		String r = "";
		for(String n : names) {
			if(r != "") r+=", ";
			r+=n;
		}
		return r;
	}
	public String getStartFXM() {
		if(start != null) return start.toString();
		else return "";
	}
	public String getFinishFXM() {
		if(finish != null) return finish.toString();
		else return "";
	}
	public String getNotesDisplay() {
		if(notes != null && notes.size() > 0) {
			return notes.get(0);
		}
		return "";
	}
	
}
