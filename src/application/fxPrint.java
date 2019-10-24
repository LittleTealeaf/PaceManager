package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.*;
import javafx.collections.ObservableSet;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class fxPrint {
	
	private final static int POINTS_PER_INCH = 72;
	
	/*
	 * Documentation
	 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/PrintinJavapageformatanddocument.htm
	 * 
	 * printing tables
	 * https://docs.oracle.com/javase/tutorial/uiswing/misc/printtable.html
	 */

	public static void open() {
		//In form of alert
		Alert aPrint = new Alert(AlertType.NONE);
		aPrint.setTitle("Printing Options");
		aPrint.setHeaderText("Select the preset method, or customize");
		
		//Add Buttons
		ButtonType bTeams = new ButtonType("All Teams");
		ButtonType bScoreBoard = new ButtonType("Scoreboard");
		ButtonType bPrizes = new ButtonType("Prizes");
		ButtonType bCustom = new ButtonType("Custom");
		ButtonType bCancel = new ButtonType("Cancel");
		
		aPrint.getButtonTypes().addAll(bTeams,bScoreBoard,bPrizes,bCustom,bCancel);
		
		//Gets the result
		Optional<ButtonType> res = aPrint.showAndWait();
		
		//ObservableSet<Printer> printers = Printer.getAllPrinters();
		
		PrinterJob job = PrinterJob.createPrinterJob();
		if(job == null) return;
		
		//Depending on the print option
		//uses String[] args
		if(res.get() == bTeams) {
			
		} else if(res.get() == bScoreBoard) {
			
		} else if(res.get() == bPrizes) {
			
		} else if(res.get() == bCustom) {
			
		} else {
			return; //Cancels Print
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView getTeamTable(String[] columns, List<Team> teams) {
		TableView<Team> tview = new TableView<Team>();
		
		for(String s : columns) {
			TableColumn col = new TableColumn(s);
			col.setCellValueFactory(new PropertyValueFactory<Team,String>(s));
			tview.getColumns().add(col);
		}
		
		return tview;
	}
}