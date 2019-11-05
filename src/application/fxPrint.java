package application;

import java.util.ArrayList;
import java.util.List;

import classes.*;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
	private static CheckBox[] cColumns;
	
	//Content Selection:
	private static ChoiceBox setDivision;
	private static RadioButton rtAll;
	private static RadioButton rtSelect;
	private static RadioButton rtSeparate;
	//Sort Selection
	private static ChoiceBox<String> setSortCol;
	
	//Default Insets
	private static final Insets DEFAULTINSETS = new Insets(10,10,10,10);
	
	public static void open() {
		open("");
	}
	
	@SuppressWarnings("unchecked")
	public static void open(String preset) {	
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
		
		setContent = new ChoiceBox(FXCollections.observableArrayList("Announcement", "Scoreboard", "Custom"));
		setContent.valueProperty().addListener((obs) -> {
			updateStage();
		});
		
		//columns
		
		CheckBox cPosition = new CheckBox("Position");
		CheckBox cTeam = new CheckBox("Team");
		CheckBox cNames = new CheckBox("Names");
		CheckBox cStart = new CheckBox("Start Time");
		CheckBox cFinish = new CheckBox("Finish Time");
		CheckBox cElapsed = new CheckBox("Elapsed Time");
		CheckBox cDifference = new CheckBox("Difference");
		CheckBox cNotes = new CheckBox("Notes");
		cColumns = new CheckBox[] {cPosition, cTeam, cNames, cStart, cFinish, cElapsed, cDifference, cNotes};
		
		//Selecting a new row will update the list of columns
		for(CheckBox c : cColumns) {
			c.selectedProperty().addListener((obs) -> {
				setSortCol.getItems().setAll(getSortColumns());
				setSortCol.setValue(setSortCol.getItems().get(0));
			});
			c.setTooltip(new Tooltip("Include the '" + c.getText() + "' column in the table?"));
		}
		
		VBox vbContentOptionsColumns = new VBox(cColumns);
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
		//Adding tooltips
		rtAll.setTooltip(new Tooltip("Include all recorded teams"));
		rtAll.setTooltip(new Tooltip("Include only a specific division"));
		rtSeparate.setTooltip(new Tooltip("Separate each division into a new table"));
		
		List<String> goals = new ArrayList<String>();
		//Add the drop-down for select division:
		if(paceManager.goals.size() > 0) for(Goal g : paceManager.goals) {
			goals.add(g.division);
		} 
		setDivision = new ChoiceBox(FXCollections.observableArrayList(goals));
		setDivision.setDisable(true);
		setDivision.setTooltip(new Tooltip("Specified Division to print"));
		
		HBox hbContentOptionsRTSelect = new HBox(rtSelect,setDivision);
		hbContentOptionsRTSelect.setSpacing(10);
		
		setSortCol = new ChoiceBox();
		setSortCol.setDisable(true);
		setSortCol.setTooltip(new Tooltip("Column in which the table should be sorted by"));
		
		HBox hbContentOptionsSort = new HBox(setSortCol);
		hbContentOptionsSort.setSpacing(10);
		
		VBox vbContentOptionsTeamSelect = new VBox(rtAll,hbContentOptionsRTSelect,rtSeparate,hbContentOptionsSort);
		vbContentOptionsTeamSelect.setSpacing(10);
		
		
		//rest
		
		HBox hbContentOptions = new HBox(vbContentOptionsColumns, vbContentOptionsTeamSelect);
	
		
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
		sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if(e.isControlDown() && e.getCode() == KeyCode.ENTER) {
					print();
				}
			}
		});
		sPrint.setScene(sc);
		
		//Set Defaults
		rVertical.fire();
		orientation = PageOrientation.PORTRAIT;
		setContent.setValue("");
		rtAll.setSelected(true);
		
		//PRESETS
		// 'if(preset == "") {} else ' makes sure nothing happens if the preset is left blank
		if(preset == "") {} else if(preset.contentEquals("All Teams")) {
			//All Teams
			setContent.setValue("Custom");
			rtAll.setSelected(true);
			setAllColumnValues(false);
			setColumnValue("Team");
			setColumnValue("Division");
			setColumnValue("Names");
			setColumnValue("Start Time");
			setColumnValue("End Time");
			setColumnValue("Elapsed Time");
			setSortCol.setValue("Team");
		} else if(preset.toCharArray()[0] == 'g') {
			//Specific Division
			String div = "";
			//Tries to parse the division, if errors then will just return
			//TODO perhaps push this logic of trying to parse it up to the top to optimize computer work
			try {
				div = preset.substring(1);
			} catch (Exception e) {return;}
			
			setContent.setValue("Custom");
			rtSelect.setSelected(true);
			setDivision.setValue(div);
			setAllColumnValues(false);
			setColumnValue("Position");
			setColumnValue("Team");
			setColumnValue("Elapsed Time");
			setColumnValue("Difference");
		}
		
		sPrint.show();
	}
	private static void setColumnValue(String colName) {setColumnValue(colName,true);}
	private static void setColumnValue(String colName, boolean var) {
		for(CheckBox c : cColumns) if(c.getText().contentEquals(colName)) c.setSelected(var);
	}
	private static void setAllColumnValues(boolean var) {
		for(CheckBox c : cColumns) c.setSelected(var);
	}
	
	/**
	 * Updates the Stage (disabled fields, etc)
	 */
	private static void updateStage() {
		boolean eCustom = !setContent.getValue().equals("Custom");
		for(CheckBox c : cColumns) {
			c.setDisable(eCustom);
		}
		
		rtAll.setDisable(eCustom);
		rtSelect.setDisable(eCustom);
		rtSeparate.setDisable(eCustom);
		
		setSortCol.setDisable(setContent.getValue().equals("Announcement"));
		setSortCol.getItems().setAll(getSortColumns());
		if(setSortCol.getItems().size() > 0) setSortCol.setValue(setSortCol.getItems().get(0));
	}
	
	private static List<String> getSortColumns() {
		List<String> ret = new ArrayList<String>();
		switch((String) setContent.getValue()) {
		case "Custom":
			for(CheckBox c : cColumns) {
				if(!c.isDisabled() && c.isSelected()) ret.add(c.getText());
			}
			break;
		case "Scoreboard":
			for(String s : new String[] {"Team","Division","Elapsed Time"}) ret.add(s);
		}
		return ret;
	}
	
	/**
	 * Gets the columns for the custom print columns for table creation
	 * @return
	 */
	private static String[] getCustomPrintColumns() {
		List<String> s = new ArrayList<String>();
		for(CheckBox c : cColumns) {
			if(c.isSelected()) switch(c.getText()) {
			case "Position": s.add("positionInDivision"); break;
			case "Start Time": s.add("startFXM"); break;
			case "Finish Time": s.add("finishFXM"); break;
			case "Elapsed Time": s.add("elapsedFXM"); break;
			default: s.add(c.getText().toLowerCase());
			}
		}
		String[] ret = new String[s.size()];
		s.toArray(ret);
		return ret;
	}
	/**
	 * Gets the custom sorting option for printing in the form that works for the table generation
	 * @return
	 */
	private static String getCustomPrintSort() {
		switch((String) setSortCol.getValue()) {
		case "Position": return "positionInDivision";
		case "Start Time": return "startFXM";
		case "Finish Time": return "finishFXM";
		case "Elapsed Time": return "elapsedFXM";
		default: return (String) setSortCol.getValue().toLowerCase();
		}
	}

	
	private static void print() {
		//Cancel Scripts
		if((String) setContent.getValue() == "") return;
		else if((String) setSortCol.getValue() == "") return;
		else if(rtSelect.isSelected() && (String) setDivision.getValue() == "") return;
		
		sPrint.setAlwaysOnTop(false);
		//Gets selected printer
		Printer printer = (Printer) setPrinter.getValue();
		//Sets the printer job
		job = PrinterJob.createPrinterJob(printer);
		
		//Create a layout using the printer's default paper
		PageLayout layout = printer.createPageLayout(printer.getDefaultPageLayout().getPaper(), orientation, Printer.MarginType.DEFAULT);
		
		job.getJobSettings().setPageLayout(layout);
		
		
		List<BorderPane> borderPanes = new ArrayList<BorderPane>();
		
		String header = "";
		String[] columns = null;
		List<Team> teams = null;
		switch((String) setContent.getValue()) {
		case "Announcement":
			if(!paceManager.goals.isEmpty()) for(Goal g : paceManager.goals) {
				header = g.division + "  " + g.time.toString();
				columns = new String[] {"positionInDivision","team","names","elapsedFXM"};
				borderPanes.addAll(getTablePages(job,header,paceManager.getTeams(g.division),columns, "positionInDivision"));
			} else return; //TODO error
			break;
		case "Scoreboard":
			header = "Scoreboard:";
			columns = new String[] {"team","division","elapsedFXM","difference"};
			borderPanes.addAll(getTablePages(job,header,paceManager.teams,columns, "team"));
			break;
		case "Custom":
			//Selected Columns
			columns = getCustomPrintColumns();
			//Get selected Teams
			if(rtAll.isSelected()) { //
				teams = paceManager.teams;
			} else if (rtSelect.isSelected()) { //Specifically Selected Division
				String selDiv = (String) setDivision.getValue();
				if(!selDiv.contentEquals("")) {
					teams = paceManager.getTeams(selDiv);
				}
			} else if(rtSeparate.isSelected()) { //Each division in its own respective list, uses custom script as multiple pages are needed
				if(!paceManager.goals.isEmpty()) {
					for(Goal g : paceManager.goals) {
						header = g.division + "  " + g.time.toString();
					
						borderPanes.addAll(getTablePages(job,header,paceManager.getTeams(g.division),columns, getCustomPrintSort()));
					}
					break;
				} else return; //TODO error
			}
			borderPanes.addAll(getTablePages(job,header,teams,columns, getCustomPrintSort()));
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
		final double cellSize = 20 * 3;
		
		//Gets Printable Size
		final double pWidth = job.getJobSettings().getPageLayout().getPrintableWidth();
		final double pHeight = job.getJobSettings().getPageLayout().getPrintableHeight();
		
		//Return value
		List<BorderPane> ret = new ArrayList<BorderPane>();
		
		//Creating the Table
		TableView table = getTable(teams,columns,sortColumn, pWidth);
		table.resize(pWidth, cellSize * (table.getItems().size() + 1));
		
		
		//TODO Add method to print this into a border pane list
		
		//Debug:
		Scene sc = new Scene(table);
		Stage s = new Stage();
		s.setScene(sc);
		s.show();
		
		return ret;
	}
	
	/**
	 * Creating a Table
	 * @param teams List of Teams
	 * @param columns Columns as described in {@link getTablePages} 
	 * @param sortColumn Column to sort, will not set sort if it's not included
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	private static TableView getTable(List<Team> teams, String[] columns, String sortColumn, double pWidth) {
		//Preset Variables
		final double colSizeTeam = 35;
		final double colSizeDiv = 60;
		final double colSizeTime = 75;
		final double colSizePlace = 35;
		final double colSizeNames = 200;
		final double colSizeNotes = 50;
		//Variables
		double totalSize = 0;
		
		//Create a table
		TableView<Team> table = new TableView<Team>();
		//Add teams
		table.getItems().setAll(teams);
		
		//Cycle through each requested column
		for(String s : columns) {
			TableColumn col = new TableColumn(s);
			
			//Variable Settings for each column
			switch(s.toLowerCase()) {
			case "team":
				col.setText("Team");
				col.setPrefWidth(colSizeTeam);
				break;
			case "division":
				col.setText("Division");
				col.setPrefWidth(colSizeDiv);
				break;
			case "names":
				col.setText("Names");
				col.setPrefWidth(colSizeNames);
				
				//Custom Cell Factory
				col.setCellFactory(column -> { return util.getTeamCell(); });
				
				break;
			case "startfxm":
				col.setText("Start");
				col.setPrefWidth(colSizeTime);
				break;
			case "finishfxm":
				col.setText("Finish");
				col.setPrefWidth(colSizeTime);
				break;
			case "elapsedfxm":
				col.setText("Elapsed");
				col.setPrefWidth(colSizeTime);
				break;
			case "positionindivision":
				col.setText("Pl.");
				col.setPrefWidth(colSizePlace);
				col.setSortType(col.getSortType().DESCENDING);
				break;
			case "printablenotes":
				col.setText("Notes");
				col.setPrefWidth(colSizeNotes);
				break;
			case "difference":
				col.setText("Difference");
				col.setPrefWidth(colSizeTime);
			default: break;
			}
			//Adds to total size
			totalSize += col.getPrefWidth();
			//Sets the cell factory
			col.setCellValueFactory(new PropertyValueFactory<Team,String>(s));
			//Adds it to the table
			table.getColumns().add(col);
			//If it's the sort column, set it as the sort column
			if(s.contentEquals(sortColumn)) table.getSortOrder().add(col);
		}
		
		//If smaller than scale
		if(totalSize != pWidth) {
			//Take the difference left over
			double difference = pWidth - totalSize;
			
			//Split remainder up and add that amount to each column
			double colAdd = difference / table.getColumns().size();
			for(TableColumn a : table.getColumns()) {
				a.setPrefWidth(a.getPrefWidth() + colAdd);
			}
		}
		
		//Removes the arrow
		table.getSortOrder().clear();
		
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