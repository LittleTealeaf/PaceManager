package classes;

import java.io.Serializable;
import application.*;

public class Settings implements Serializable {
	public boolean alertOnDeleteTeam;
	public boolean alertOnDeleteGoal;
	
	public Settings() {
		//Default Settings
		alertOnDeleteTeam = false;
		alertOnDeleteGoal = false;
	}
}
