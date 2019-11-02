package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * Notes only displays the first line of the notes
 * windows:
 * fxEditTeam
 */

public class fxMain extends Application {
	
	private static TableView<Team> table;
	
	public static Stage sMRef;

	public static void open(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "exports" })
	@Override
	public void start(Stage sMain) {
		sMRef = sMain;
		sMain.setTitle("Pace Manager " + paceManager.version);
		sMain.widthProperty().addListener((obs,oldv,newv) -> {
			resizeColumns();
		});
		sMain.maximizedProperty().addListener((obs,oldv,newv) -> {
			resizeColumns();
		});
		
		//Menus
		Menu m1 = new Menu("File");
		MenuItem m1New = new MenuItem("New");
		m1New.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Create New?");
				alert.setHeaderText("Opening a new file will clear the currently loaded file");
				alert.setContentText("Make sure that you've saved the loaded one before accepting");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() != ButtonType.OK) {
					return;
				}
				fileManager.loadedFile = null;
				paceManager.goals = new ArrayList<Goal>();
				paceManager.teams = new ArrayList<Team>();
				updateTable();
			}
		});
		MenuItem m1Open = new MenuItem("Open");
		m1Open.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fileManager.open();
			}
		});
		MenuItem m1Save = new MenuItem("Save");
		m1Save.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fileManager.save();
			}
		});
		MenuItem m1SaveAs = new MenuItem("Save As");
		m1SaveAs.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fileManager.saveAs();
			}
		});
		SeparatorMenuItem m1Separator1 = new SeparatorMenuItem();
		MenuItem m1Exit = new MenuItem("Exit");
		m1Exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.exit(0);
			}
		});
		m1.getItems().addAll(m1New,m1Open,m1Save,m1SaveAs,m1Separator1,m1Exit);
		
		Menu m2 = new Menu("Teams");
		MenuItem m2Create = new MenuItem("Create Team");
		m2Create.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fxTeam.open(null);
			}
		});
		SeparatorMenuItem m2Separator1 = new SeparatorMenuItem();
		MenuItem m2Import = new MenuItem("Import");
		m2Import.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fxImport.open();
			}
		});
		MenuItem m2ImportFile = new MenuItem("Import from File...");
		m2ImportFile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fileImport.importFile();
			}
		});
		m2.getItems().addAll(m2Create,m2Separator1,m2Import,m2ImportFile);

		Menu m3 = new Menu("Pace");
		MenuItem m3Goals = new MenuItem("Goal Times");
		m3Goals.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fxGoals.open();
			}
		});
		MenuItem m3Scores = new MenuItem("Scores");
		m3Scores.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fxScores.open();
			}
		});
		MenuItem m3Print = new MenuItem("Print");
		m3Print.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				fxPrint.open();
			}
		});
		m3.getItems().addAll(m3Goals,m3Scores,m3Print);
		
		MenuBar mb = new MenuBar();
		mb.getMenus().addAll(m1,m2,m3);
		
		//Table 
		table = new TableView<Team>();
		table.setEditable(false);
		/*
		 * https://stackoverflow.com/a/32442449
		 */
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					try {
						TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
						//int row = pos.getRow();
						//System.out.println(row);
						int col = pos.getColumn();
						Team sel = table.getSelectionModel().getSelectedItem();
						if(col == 6) {
							openNotes(sel, click.getScreenX(), click.getScreenY());
						} else {
							fxTeam.open(sel,col);
						}
					} catch(IndexOutOfBoundsException e) {}
				}
			}
		});
		table.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if(k.getCode() == KeyCode.DELETE) {
					try {
						paceManager.deleteTeam(table.getSelectionModel().getSelectedItem());
					} catch(IndexOutOfBoundsException e) {}
				}
			}
		});

		/*
		 * How the following works:
		 * - Creates the Column
		 * TableColumn cTeamName = new TableColumn("Team");
		 * 
		 * - Routs the column to the specified variable (using get____(); ) of Team
		 * cTeamName.setCellValueFactory(new PropertyValueFactory<Team,String>("team"));
		 * 
		 * - Any of the custom variables to set
		 * cTeamName.setReorderable(false);
		 */
		
		TableColumn cTeamName = new TableColumn("Team");
		cTeamName.setCellValueFactory(new PropertyValueFactory<Team,String>("team"));
		cTeamName.setReorderable(false);
		
		TableColumn cDivision = new TableColumn("Division");
		cDivision.setCellValueFactory(new PropertyValueFactory<Team,String>("division"));
		cDivision.setReorderable(false);
		
		TableColumn cNames = new TableColumn("Riders");
		cNames.setEditable(false);
		cNames.setCellFactory(column -> {
			return util.getTeamCell();
		});
		cNames.setCellValueFactory(new PropertyValueFactory<Team,String>("names"));
		cNames.setReorderable(false);
		
		TableColumn cTime = new TableColumn("Times");
		cTime.setReorderable(false);
		TableColumn cTStart = new TableColumn("Start");
		cTStart.setCellValueFactory(new PropertyValueFactory<Team,String>("startFXM"));
		cTStart.setReorderable(false);
		TableColumn cTFinish = new TableColumn("Finish");
		cTFinish.setCellValueFactory(new PropertyValueFactory<Team,String>("finishFXM"));
		cTFinish.setReorderable(false);
		TableColumn cTElapsed = new TableColumn("Elapsed");
		cTElapsed.setCellValueFactory(new PropertyValueFactory<Team,String>("elapsedFXM"));
		cTElapsed.setReorderable(false);
		
		cTime.getColumns().addAll(cTStart,cTFinish,cTElapsed);
		TableColumn cNotes = new TableColumn("Notes");
		cNotes.setCellValueFactory(new PropertyValueFactory<Team,String>("notesDisplay"));
		cNotes.setReorderable(false);
		
		table.getColumns().addAll(cTeamName,cDivision,cNames,cTime,cNotes);
		
		table.prefHeightProperty().bind(sMain.heightProperty());
        table.prefWidthProperty().bind(sMain.widthProperty());
        
        //Disables horizontal scrollbar
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		
        //TODO add hbox with est arrivals and such
		VBox vb = new VBox(mb,table);
		
		Scene sc = new Scene(vb, 500, 300);
		sMain.setMaximized(true);
		sMain.setScene(sc); 
		sMain.show();
		
		updateTable();
	}
	
	public static void updateTable() {
		table.getItems().clear();
		table.getItems().addAll(paceManager.teams);
		table.sort();
		resizeColumns();
		fxScores.updateTables();
	}
	
	/**
	 * Automatically resizes the columns to the table
	 */
	private static void resizeColumns() {
		final double wTeam = 50;
		final double wDiv = 70;
		final double wTime = 80;
		
		//Min width to apply the proper formatting
		if(table.getWidth() > 420) {
			//Team
			table.getColumns().get(0).setMaxWidth(wTeam);
			table.getColumns().get(0).setMinWidth(wTeam);
			//Division
			table.getColumns().get(1).setMaxWidth(wDiv);
			table.getColumns().get(1).setMinWidth(wDiv);
			//Start Time
			table.getColumns().get(3).getColumns().get(0).setMaxWidth(wTime);
			table.getColumns().get(3).getColumns().get(0).setMinWidth(wTime);
			//End Time
			table.getColumns().get(3).getColumns().get(1).setMaxWidth(wTime);
			table.getColumns().get(3).getColumns().get(1).setMinWidth(wTime);
			//Elapsed Time
			table.getColumns().get(3).getColumns().get(2).setMaxWidth(wTime);
			table.getColumns().get(3).getColumns().get(2).setMinWidth(wTime);
		}
	}
	/**
	 * Closes the sub-windows to this class
	 */
	public static void closeSubWindows() {
		if(mNotes.isShowing()) mNotes.close();
	}
	
	private static Stage mNotes;
	private static TextArea nText;
	private static Team nTeam;
	
	/**
	 * Method to open the sub-notes window
	 * @param t Team to open notes
	 * @param x The x position of the mouse
	 * @param y The y position of the mouse
	 */
	private static void openNotes(Team t, double x, double y) {
		nTeam = t;
		// WIDTH and HEIGHT are the set width and height of the box
		final double WIDTH = 300;
		final double HEIGHT = 300;
		
		if(mNotes == null) {
			//Stage setup, only needs to happen once
			mNotes = new Stage();
			mNotes.setAlwaysOnTop(true);
			mNotes.initStyle(StageStyle.UNDECORATED);
			mNotes.setResizable(true);
			mNotes.focusedProperty().addListener((obs,wasFocused,isNowFocused) -> {
				if(!isNowFocused) {
					mNotes.close();
					updateTable();
				}
			});
			mNotes.setWidth(WIDTH);
			mNotes.setHeight(HEIGHT);
		}
		
		//Keeps from window showing off-screen
		mNotes.setX(Math.min(sMRef.getWidth() - WIDTH, x));
		mNotes.setY(Math.min(sMRef.getHeight() - HEIGHT, y));
		
		nText = new TextArea();
		
		//Get and display the notes
		int pos = 0;
		for(String l : t.notes) {
			if(pos == 0) nText.setText(l);
			else nText.setText(nText.getText() + "\n" + l);
			pos++;
		}
		//Add a listener that saves the data (reverses the display) whenever the field is edited. A bit cumbersome but it works nonetheless
		nText.textProperty().addListener((observable, oldValue, newValue) -> {
			List<String> notes = new ArrayList<String>();
			String tmp = "";
			for(char c : nText.getText().toCharArray()) {
				if(c == '\n' || c == '\r') {
					notes.add(tmp);
					tmp = "";
				} else tmp+=c;
			}
			notes.add(tmp);
			if(notes.get(0).equals("")) notes.remove(0);
			nTeam.notes = notes;
		});
		
		nText.prefWidthProperty().bind(mNotes.maxWidthProperty());
		nText.prefHeightProperty().bind(mNotes.heightProperty());
		
		Button nbClose = new Button();
		nbClose.setText("Close");
		nbClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent click) {
				mNotes.close();
				updateTable();
			}
		});
		
		VBox vb = new VBox();
		vb.getChildren().addAll(nText, nbClose);
		Scene sc = new Scene(vb,mNotes.getWidth(),mNotes.getHeight());
		
		sc.addEventHandler(KeyEvent.ANY, keyEvent -> {
			if(keyEvent.getCode() == KeyCode.ESCAPE) {
				mNotes.close();
				updateTable();
			}
		});
		
		mNotes.setScene(sc);
		mNotes.show();
	}
	
}
