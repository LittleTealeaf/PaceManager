package classes;

import java.util.ArrayList;
import java.util.List;

import application.paceManager;

public class Team {
	
	public String team;
	public List<String> names;
	public String division;
	public List<String> notes;
	public Time start;
	public Time finish;
	public Boolean excluded;
	
	/**
	 * Central Construction of the class
	 * @param teamName Team Identifier
	 * @param div Division 
	 * @param nms List<String> of Names
	 * @param Start Start Time using the Time class
	 * @param Finish Finish Time using the Time class
	 * @param note Notes in a List<String>
	 */
	private void construct(String teamName, String div, List<String> nms, Time Start, Time Finish, List<String> note) {
		excluded = false;
		team = teamName;
		division = div;
		names = nms;
		start = Start;
		finish = Finish;
		notes = note;
		if(division == null) division = "";
		if(team == null) team = "";
		if(notes == null) notes = new ArrayList<String>();
		if(names == null) names = new ArrayList<String>();
	}
	
	public Team() {
		construct("","",null,null,null,null);
	}
	public Team(Team t) {
		construct(t.team,t.division,t.names,t.start,t.finish,t.notes);
	}
	public Team(String tm) {
		construct(tm,null,null,null,null,null);
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
	
	/**
	 * Gets the elapsed time
	 * @return Time between start and finish, returns null if either start or finish is null
	 */
	public Time elapsed() {
		Time r = new Time();
		if(start == null || finish == null) {
			return null;
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
	
	// The following lines of codes are gets for specific parts of the class used with JavaFX's table display
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
	public String getElapsedFXM() {
		if(finish != null && start != null) return new Time(finish.time - start.time).toString(true);
		return "";
	}
	
	//Runtime Calculations
	//Includes stuff like difference etc.
	
	public Time getTimeDifference(Time goalTime) {
		if(goalTime != null) {
			return new Time(Math.abs(goalTime.time - elapsed().time));
		} else return null;
	}
	
	public String getDifference() {
		if(elapsed() != null) {
			for(Goal g : paceManager.goals) {
				if(g.division.contentEquals(division)) {
					return new Time(Math.abs(g.getGoalTime().time - elapsed().time)).toString(true);
				}
			}
		}
		return "";
	}
	
}
