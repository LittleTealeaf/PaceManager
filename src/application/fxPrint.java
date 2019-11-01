package application;

import java.util.ArrayList;
import java.util.List;

import classes.*;
import solutions.ZoomableScrollPane;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
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
		sPrint.setAlwaysOnTop(false);
		//Gets selected printer
		Printer printer = (Printer) setPrinter.getValue();
		//Sets the printer job
		job = PrinterJob.createPrinterJob(printer);
		
		//Create a layout using the printer's default paper, the set orientation, and equal margin type
		PageLayout layout = printer.getDefaultPageLayout();
		
		job.getJobSettings().setPageLayout(layout);
		
		
		ScrollPane scrollPane = null;
		TableView table = null;
		switch((String) setContent.getValue()) {
		case "All Teams":
			table = util.teamTable(paceManager.teams,new String[] {"team","division","names","startFXM","finishFXM","elapsedFXM"});
			
			scrollPane = getTablePages(job,table,"All Teams");
			
			job.printPage(scrollPane);
			job.endJob();
			
			break;
		case "Announcement":
			table = util.teamTable(paceManager.teams,new String[] {"team","division","names","startFXM","finishFXM","elapsedFXM"});
			
			break;
		case "Scoreboard":
			
			break;
		case "Custom":
			
			break;
		}
		
		sPrint.close();
	}
	
	//http://tutorials.jenkov.com/javafx/scrollpane.html
	
	@SuppressWarnings("unchecked")
	public static ScrollPane getTablePages(PrinterJob job, TableView table, String headerText) {
		//PRE DETERMINED SETTINGS
		final double cellSize = 20;
		
		
		//Gets printable size
		final double pWidth = job.getJobSettings().getPageLayout().getPrintableWidth();
		final double pHeight = job.getJobSettings().getPageLayout().getPrintableHeight();
		
		//Format Table
		table.setFixedCellSize(cellSize);
		
		//Get the default table width (sum of all the columns)
		double colWidth = 0;
		List<TableColumn> cols = table.getColumns();
		for(TableColumn col : cols) {
			colWidth+=col.getWidth();
		}
		
		//Resizes Table
		table.resize(colWidth, table.getItems().size() * (cellSize + 2));
		
		//Adds a scale factor to the table to fit in the page width
		//table.getTransforms().add(new Scale(pWidth / colWidth,1));
		
		// might need to add a custom table(?)
		
		
		
		//Create a header
		Label header = new Label(headerText);
		
		VBox content = new VBox(header,table);
		
		ScrollPane z = new ScrollPane(content);
		z.resize(pWidth, pHeight);
		z.setFitToWidth(true);
		
		Scene sc = new Scene(z,pWidth,pHeight);
		Stage s = new Stage();
		s.setScene(sc);
		s.show();
		
		return z;
//		//Create Header
//		Label l = new Label(header);
//		
//		
//		//Get total width of all the columns
//		List<TableColumn> cols = table.getColumns();
//		double initialWidth = 0;
//		for(TableColumn c : cols) {
//			initialWidth+=c.getWidth();
//		}
//	
//				
//		//Resize table to the total column width and proper height (prior to this the table had 0 width and 0 height)
//		table.resize(initialWidth, pHeight - l.getHeight());
//		
//		//table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//		
//
//		System.out.println(table.getWidth());
//		
//		//Down-scale the width to fit the screen
//		for(TableColumn c : cols) {
//			System.out.print(c.getText() + " " + c.getWidth());
//			//Ratio of the current width to the initial width
//			double ratio = c.getWidth() / initialWidth;
//			
//			//Set the width to the same ratio in relation to the total width
//			c.setPrefWidth(ratio * (pWidth - 5));
//			System.out.println(" " + c.getWidth());
//		}
//		
//		
//		table.setFixedCellSize(cellSize);
//		
//		//table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()));
//		
//		
//		
//		
//		//Border Pane
//		BorderPane r = new BorderPane();
//		
//		r.setTop(l);
//		r.setCenter(table);
//		
////		Scene sc = new Scene(r,pWidth,cellSize*(table.getItems().size()+4) + l.getHeight());
////		r.autosize();
////		
////		Stage s = new Stage();
////		s.setScene(sc);
////		s.show();
//		
//		ZoomableScrollPane b = new ZoomableScrollPane(r);
//		
//		b.resize(r.getWidth(), r.getHeight());
//		
//		r.resize(pWidth,table.getHeight());
//		
//		//Scene sc = new Scene(b,pWidth,cellSize*(table.getItems().size() + 4) + l.getHeight());
//		
//		
//		return b;
	}
}