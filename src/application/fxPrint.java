package application;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.util.Optional;

import classes.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class fxPrint {
	
	/*
	 * Documentation
	 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/PrintinJavapageformatanddocument.htm
	 * https://docs.oracle.com/javase/tutorial/uiswing/misc/printtable.html
	 */

	public static void open() {
		//In form of alert
		Alert aPrint = new Alert(AlertType.NONE);
		aPrint.setTitle("Printing Options");
		aPrint.setHeaderText("Select the preset method, or customize");
		
		//Add Buttons
		ButtonType bScoreBoard = new ButtonType("Scoreboard");
		ButtonType bPrizes = new ButtonType("Prizes");
		ButtonType bCustom = new ButtonType("Custom");
		ButtonType bCancel = new ButtonType("Cancel");
		
		aPrint.getButtonTypes().addAll(bScoreBoard,bPrizes,bCustom,bCancel);
		
		Optional<ButtonType> res = aPrint.showAndWait();
		if(res.get() == bScoreBoard) {
			
		} else if(res.get() == bPrizes) {
			
		} else if(res.get() == bCustom) {
			
		} else {
			
		}
		
		PrinterJob printJob = PrinterJob.getPrinterJob();
		
		
	}
}
