package application;

import java.util.ArrayList;
import java.util.List;

import classes.Goal;
import classes.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("rawtypes")
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
	//Content Selection:
	private static ChoiceBox setDivision;
	private static RadioButton rtAll;
	private static RadioButton rtSelect;
	private static RadioButton rtSeparate;
	
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
		
		
		Label lCopies = new Label("No. of Copies: ");
		lCopies.setTooltip(new Tooltip("Number of copies to print"));
		Spinner<Integer> sCopies = new Spinner<Integer>();
		sCopies.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));
		
		HBox hbCopies = new HBox(lCopies,sCopies);
		hbCopies.setSpacing(10);
		hbCopies.setPadding(DEFAULTINSETS);
		
		VBox vbPrintSettings = new VBox(hbSelPrinter,hbOrientation,hbCopies);
		
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
		
		//Creating the radio buttons
		ToggleGroup tgTeamSelection = new ToggleGroup();
		rtAll = new RadioButton("All Teams");
		rtSelect = new RadioButton("Select Division:");
		rtSelect.selectedProperty().addListener((obs,oldv,newv) -> {
			setDivision.setDisable(!newv.booleanValue());
		});
		rtSeparate = new RadioButton("Separate Divisions");
		//Setting them all to the toggle group
		rtAll.setToggleGroup(tgTeamSelection);
		rtSelect.setToggleGroup(tgTeamSelection);
		rtSeparate.setToggleGroup(tgTeamSelection);
		
		List<String> goals = new ArrayList<String>();
		//Add the drop-down for select division:
		if(paceManager.goals.size() > 0) for(Goal g : paceManager.goals) {
			goals.add(g.division);
		} 
		setDivision = new ChoiceBox(FXCollections.observableArrayList(goals));
		setDivision.setDisable(true);
		
		HBox hbContentOptionsRTSelect = new HBox(rtSelect,setDivision);
		hbContentOptionsRTSelect.setSpacing(10);
		
		VBox hbContentOptionsTeamSelect = new VBox(rtAll,hbContentOptionsRTSelect,rtSeparate);
		hbContentOptionsTeamSelect.setSpacing(10);
		
		
		//rest
		
		HBox hbContentOptions = new HBox(vbContentOptionsColumns, hbContentOptionsTeamSelect);
		
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
		rtAll.setSelected(true);
		
		sPrint.show();
		
	}
	
	/**
	 * 
	 * @param columns Valid Columns: team, division, names, startFXM, finishFXM, elapsedFXM, notesDisplay
	 * @param teams List of teams to be included
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<Team> getTeamTable(String[] columns, List<Team> teams) {
		TableView<Team> tview = new TableView<Team>();
		
		for(String s : columns) {
			TableColumn col = new TableColumn();
			switch(s) {
			case "team": col.setText("Team"); break;
			case "division": col.setText("Division"); break;
			case "names": col.setText("Names"); break;
			case "startFXM": col.setText("Start"); break;
			case "finishFXM": col.setText("Finish"); break;
			case "elapsedFXM": col.setText("Elapsed"); break;
			case "notesDisplay": col.setText("Notes"); break;
			default: break;
			}
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
		boolean eColumns = !setContent.getValue().equals("Custom");
		cTeam.setDisable(eColumns);
		cNames.setDisable(eColumns);
		cStart.setDisable(eColumns);
		cFinish.setDisable(eColumns);
		cElapsed.setDisable(eColumns);
		cNotes.setDisable(eColumns);
		
		boolean eTeams = !(setContent.getValue().equals("All Teams") || !eColumns);
		rtAll.setDisable(eTeams);
		rtSelect.setDisable(eTeams);
		rtSeparate.setDisable(eTeams);
		
	}

	
	private static void print() {
		//Gets selected printer
		Printer printer = (Printer) setPrinter.getValue();
		//Sets the printer job
		job = PrinterJob.createPrinterJob(printer);
		
		//Create a layout using the printer's default paper, the set orientation, and equal margin type
		PageLayout layout = printer.createPageLayout(printer.getDefaultPageLayout().getPaper(), orientation, Printer.MarginType.EQUAL);
		
		job.getJobSettings().setPageLayout(layout);
		
		//Presets
		/*
		BorderPane scr = new BorderPane();
		
		switch((String) setContent.getValue()) {
		case "All Teams":
			TableView table = getTeamTable(new String[] {"team","division","names","startFXM","finishFXM","elapsedFXM"},paceManager.teams);
			
			scr.setCenter(table);
			
			break;
		case "Announcement":
			
			break;
		case "Scoreboard":
			
			break;
		case "Custom":
			
			break;
		}
		Scene sc = new Scene(scr,layout.getPaper().getWidth(),layout.getPaper().getHeight());
		if(job.printPage(scr) && job.endJob()) {
			sPrint.close();
		}
		*/
		TableView table = null;
		switch((String) setContent.getValue()) {
		case "All Teams":
			table = getTeamTable(new String[] {"team","division","names","startFXM","finishFXM","elapsedFXM"},paceManager.teams);
			
			break;
		case "Announcement":
			
			break;
		case "Scoreboard":
			
			break;
		case "Custom":
			
			break;
		}
		if(table != null) {
			
		}
	}
	
}