package application;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
		
		
		List<BorderPane> borderPanes = new ArrayList<BorderPane>();
		
		switch((String) setContent.getValue()) {
		case "All Teams":
			borderPanes.addAll(getTablePages(job,"",paceManager.teams,new String[] {"team","division","names","startFXM","finishFXM"}, "team"));
			break;
		case "Announcement":
			
			
			break;
		case "Scoreboard":
			
			break;
		case "Custom":
			
			break;
		}
		
		sPrint.close();
	}
	
	/**
	 * Returns pages to print
	 * @param job The printer job, used to get the page dimensions
	 * @param header Text to put in the top, add \n for additional lines
	 * @param teams The List of Teams to include
	 * @param columns Columns in order to display. Valid Columns: team (team number), division (team division), names (rider names), startFXM (start time), finishFXM (end time), elapsedFXM (elapsed time), notesDisplay (notes), positionInDivision (team place) 
	 * @param sortColumn Column, specified above, to sort by (will return null if set column does not)
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private static List<BorderPane> getTablePages(PrinterJob job, String header, List<Team> teams, String[] columns, String sortColumn) {
		//Checks if the sort column is in the columns
		boolean bError = true;
		for(String s : columns) if(s.contentEquals(sortColumn)) bError = false;
		if(bError) {
			System.err.append("Error: sortColumn is not a specified column in columns");
			return null;
		}
		
		//PRESETS
		final double cellSize = 20;
		
		//Gets Printable Size
		final double pWidth = job.getJobSettings().getPageLayout().getPrintableWidth();
		final double pHeight = job.getJobSettings().getPageLayout().getPrintableHeight();
		
		//Return value
		List<BorderPane> ret = new ArrayList<BorderPane>();
		
		//Creating the Table
		TableView table = getTable(teams,columns,sortColumn);
		table.setFixedCellSize(cellSize);
		List<TableColumn> cols = table.getColumns();
		double totalColumnSize = 0;
		for(TableColumn c : cols) totalColumnSize+=c.getPrefWidth();
		table.resize(totalColumnSize, cellSize * (table.getItems().size() + 1));
		
		//Scaling table to the width of the paper
		table.setScaleX(pWidth / table.getWidth());
		
		
		return ret;
	}
	
	/**
	 * Creating a Table
	 * @param teams List of Teams
	 * @param columns Columns as described in {@link getTablePages} 
	 * @param sortColumn Column to sort, will not set sort if it's not included
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static TableView getTable(List<Team> teams, String[] columns, String sortColumn) {
		final double colSizeTeam = 25;
		final double colSizeDiv = 30;
		final double colSizeTime = 30;
		final double colSizePlace = 10;
		final double colSizeNames = 100;
		final double colSizeNotes = 50;
		
		TableView<Team> table = new TableView<Team>();
		table.getItems().setAll(teams);
		for(String s : columns) {
			TableColumn col = new TableColumn(s);
			switch(s.toLowerCase()) {
			case "team":
				col.setText("Team");
				col.setPrefWidth(colSizeTeam);
				break;
			case "div":
				col.setText("Division");
				col.setPrefWidth(colSizeDiv);
				break;
			case "names":
				col.setText("Names");
				col.setPrefWidth(colSizeNames);
				
				//TODO re-incorporate multiple lines (it actually worked)
				// Then set the default cell size to 3 times its current height! 
				
				break;
			case "startFXM":
				col.setText("Start");
				col.setPrefWidth(colSizeTime);
				break;
			case "finishFXM":
				col.setText("Finish");
				col.setPrefWidth(colSizeTime);
				break;
			case "elapsedFXM":
				col.setText("Elapsed");
				col.setPrefWidth(colSizeTime);
				break;
			case "positionInDivision":
				col.setText("Pl.");
				col.setPrefWidth(colSizePlace);
				break;
			case "printableNotes":
				col.setText("Notes");
				col.setPrefWidth(colSizeNotes);
			default: break;
			}
			col.setCellValueFactory(new PropertyValueFactory<Team,String>(s));
			table.getColumns().add(col);
			if(s.contentEquals(sortColumn)) table.getSortOrder().add(col);
		}
		return table;
	}
	
	
	
	//http://tutorials.jenkov.com/javafx/scrollpane.html
	
//	@SuppressWarnings("unchecked")
//	public static ScrollPane getTablePages(PrinterJob job, TableView table, String headerText) {
//		//PRE DETERMINED SETTINGS
//		final double cellSize = 20;
//		
//		
//		//Gets printable size
//		final double pWidth = job.getJobSettings().getPageLayout().getPrintableWidth();
//		final double pHeight = job.getJobSettings().getPageLayout().getPrintableHeight();
//		
//		//Format Table
//		table.setFixedCellSize(cellSize);
//		
//		//Get the default table width (sum of all the columns)
//		double colWidth = 0;
//		List<TableColumn> cols = table.getColumns();
//		for(TableColumn col : cols) {
//			colWidth+=col.getWidth();
//		}
//		
//		//Resizes Table
//		table.resize(colWidth, table.getItems().size() * (cellSize + 2));
//		
//		//Adds a scale factor to the table to fit in the page width
//		//table.getTransforms().add(new Scale(pWidth / colWidth,1));
//		
//		// might need to add a custom table(?)
//		
//		
//		
//		//Create a header
//		Label header = new Label(headerText);
//		
//		VBox content = new VBox(header,table);
//		
//		ScrollPane z = new ScrollPane(content);
//		z.resize(pWidth, pHeight);
//		z.setFitToWidth(true);
//		
//		Scene sc = new Scene(z,pWidth,pHeight);
//		Stage s = new Stage();
//		s.setScene(sc);
//		s.show();
//		
//		return z;
////		//Create Header
////		Label l = new Label(header);
////		
////		
////		//Get total width of all the columns
////		List<TableColumn> cols = table.getColumns();
////		double initialWidth = 0;
////		for(TableColumn c : cols) {
////			initialWidth+=c.getWidth();
////		}
////	
////				
////		//Resize table to the total column width and proper height (prior to this the table had 0 width and 0 height)
////		table.resize(initialWidth, pHeight - l.getHeight());
////		
////		//table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
////		
////
////		System.out.println(table.getWidth());
////		
////		//Down-scale the width to fit the screen
////		for(TableColumn c : cols) {
////			System.out.print(c.getText() + " " + c.getWidth());
////			//Ratio of the current width to the initial width
////			double ratio = c.getWidth() / initialWidth;
////			
////			//Set the width to the same ratio in relation to the total width
////			c.setPrefWidth(ratio * (pWidth - 5));
////			System.out.println(" " + c.getWidth());
////		}
////		
////		
////		table.setFixedCellSize(cellSize);
////		
////		//table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()));
////		
////		
////		
////		
////		//Border Pane
////		BorderPane r = new BorderPane();
////		
////		r.setTop(l);
////		r.setCenter(table);
////		
//////		Scene sc = new Scene(r,pWidth,cellSize*(table.getItems().size()+4) + l.getHeight());
//////		r.autosize();
//////		
//////		Stage s = new Stage();
//////		s.setScene(sc);
//////		s.show();
////		
////		ZoomableScrollPane b = new ZoomableScrollPane(r);
////		
////		b.resize(r.getWidth(), r.getHeight());
////		
////		r.resize(pWidth,table.getHeight());
////		
////		//Scene sc = new Scene(b,pWidth,cellSize*(table.getItems().size() + 4) + l.getHeight());
////		
////		
////		return b;
//	}
}