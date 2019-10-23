package application;

import classes.*;
import javafx.stage.Stage;

public class fxPrint {
	
	/*
	 * Documentation
	 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/PrintinJavapageformatanddocument.htm
	 * https://docs.oracle.com/javase/tutorial/uiswing/misc/printtable.html
	 */
	
	public static Stage sPrint;

	public static void open() {
		if(sPrint != null) sPrint.close();
		sPrint = new Stage();
		
	}
}
