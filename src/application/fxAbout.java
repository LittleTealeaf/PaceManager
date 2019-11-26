package application;

import javafx.stage.Stage;

public class fxAbout {

	public static Stage sChangeLog;
	
	public static void openChangeLog() {
		if(sChangeLog != null && sChangeLog.isShowing()) sChangeLog.close();
	}
}
