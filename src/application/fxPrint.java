package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class fxPrint {
	
	/*
	 * Documentation
	 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/PrintinJavapageformatanddocument.htm
	 * 
	 * printing tables
	 * https://docs.oracle.com/javase/tutorial/uiswing/misc/printtable.html
	 */
	
	public static Stage sPrint;
	
	private static ObservableSet<Printer> printers;
	private static ChoiceBox setPrinter;
	private static ChoiceBox setContent;
	private static PrinterJob job;
	private static PageOrientation orientation;
	
	//Check Boxes
	private static CheckBox cTeam;
	private static CheckBox cNames;
	private static CheckBox cStart;
	private static CheckBox cFinish;
	private static CheckBox cElapsed;
	private static CheckBox cNotes;
	
	//Default Insets
	private static final Insets DEFAULTINSETS = new Insets(10,10,10,10);
	
	@SuppressWarnings("unchecked")
	public static void open() {	
		if(sPrint != null) sPrint.close();
		sPrint = new Stage();
		
		printers = Printer.getAllPrinters();		
		
		sPrint.setTitle("Print");
		sPrint.setAlwaysOnTop(true);
		
		// PRINTING OPTIONS
		
		Label lPrinterSel = new Label("Printer: ");
		lPrinterSel.setTooltip(new Tooltip("Which printer do you want to print to?"));
		//Choice Box for Printers
		setPrinter = new ChoiceBox<Printer>(FXCollections.observableArrayList(printers));
		setPrinter.setValue(Printer.getDefaultPrinter());
		
		HBox hbSelPrinter = new HBox(lPrinterSel,setPrinter);
		hbSelPrinter.setPadding(DEFAULTINSETS);
		
		//Radio Buttons for Horizontal / Vertical alignment
		ToggleGroup tgOrientation = new ToggleGroup();
		RadioButton rVertical = new RadioButton("Veritcal");
		rVertical.setToggleGroup(tgOrientation);
		rVertical.armedProperty().addListener((obs) -> {
			//When Clicked
			orientation = PageOrientation.PORTRAIT;
		});
		RadioButton rHorizontal = new RadioButton("Horizontal");
		rHorizontal.setToggleGroup(tgOrientation);
		rHorizontal.armedProperty().addListener((obs) -> {
			//When Clicked
			orientation = PageOrientation.LANDSCAPE;
		});
		
		HBox hbOrientation = new HBox(rVertical,rHorizontal);
		hbOrientation.setSpacing(10);
		hbOrientation.setPadding(DEFAULTINSETS);
		
		VBox vbPrintSettings = new VBox(hbSelPrinter,hbOrientation);
		
		//CONTENT OPTIONS
		
		setContent = new ChoiceBox(FXCollections.observableArrayList("All Teams", "Announcement", "Scoreboard", "Custom"));
		setContent.valueProperty().addListener((obs) -> {
			updateStage();
		});
		
		//columns
		
		cTeam = new CheckBox("Teams");
		cNames = new CheckBox("Names");
		cStart = new CheckBox("Start Time");
		cFinish = new CheckBox("Finish Time");
		cElapsed = new CheckBox("Elapsed Time");
		cNotes = new CheckBox("Notes");
		
		VBox vbContentOptionsColumns = new VBox(cTeam,cNames,cStart,cFinish,cElapsed,cNotes);
		vbContentOptionsColumns.setSpacing(3);
		vbContentOptionsColumns.setPadding(DEFAULTINSETS);
		
		//Team Selection
		
		
		
		//rest
		
		HBox hbContentOptions = new HBox(vbContentOptionsColumns);
		
		VBox vbContentOptions = new VBox(setContent,hbContentOptions);
		vbContentOptions.setSpacing(10);
		vbContentOptions.setPadding(DEFAULTINSETS);
		
		//BOTTOM
		
		Button bCancel = new Button("Cancel");
		bCancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				sPrint.close();
			}
		});
		
		Region rBottom = new Region();
		HBox.setHgrow(rBottom, Priority.ALWAYS);
		
		Button bPrint = new Button("Print");
		bPrint.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				print();
			}
		});
		
		HBox hbMid = new HBox(vbPrintSettings, vbContentOptions);		
		HBox hbBottom = new HBox(bCancel,rBottom,bPrint);
		hbBottom.setPadding(new Insets(5, 5, 5, 5));
		
		VBox vb = new VBox(hbMid,hbBottom);
		
		Scene sc = new Scene(vb);
		sPrint.setScene(sc);
		
		//Set Defaults
		rVertical.fire();
		orientation = PageOrientation.PORTRAIT;
		setContent.setValue("All Teams");
		
		sPrint.show();
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * 
	 * @param columns Valid Columns: team, division, names, startFXM, finishFXM, elapsedFXM, notesDisplay
	 * @param teams List of teams to be included
	 * @return
	 */
	public static TableView getTeamTable(String[] columns, List<Team> teams) {
		TableView<Team> tview = new TableView<Team>();
		
		for(String s : columns) {
			TableColumn col = new TableColumn(s);
			col.setCellValueFactory(new PropertyValueFactory<Team,String>(s));
			tview.getColumns().add(col);
		}
		tview.getItems().setAll(teams);
		
		return tview;
	}
	public static TableView getTeamTable(List<String> columns, List<Team> teams) {
		return getTeamTable((String[]) columns.toArray(),teams);
	}
	
	private static void updateStage() {
		boolean eCustom = !setContent.getValue().equals("Custom");
		cTeam.setDisable(eCustom);
		cNames.setDisable(eCustom);
		cStart.setDisable(eCustom);
		cFinish.setDisable(eCustom);
		cElapsed.setDisable(eCustom);
		cNotes.setDisable(eCustom);

	}
	
	private static void print() {
		//Gets selected printer
		Printer printer = (Printer) setPrinter.getValue();
		//Sets the printer job
		job = PrinterJob.createPrinterJob(printer);
		
		//Create a layout using the printer's default paper, the set orientation, and equal margin type
		PageLayout layout = printer.createPageLayout(printer.getDefaultPageLayout().getPaper(), orientation, Printer.MarginType.EQUAL);
		
		job.getJobSettings().setPageLayout(layout);
		
		String[] columns = null;
		switch((String) setContent.getValue()) {
		case "All Teams":
			break;
		case "Announcement":
			break;
		case "Scoreboard":
			break;
		case "Custom":
			
			break;
		} 
	}
	
}