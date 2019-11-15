package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import classes.Pace;
import classes.Team;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class fileImport {
	
	private static List<Team> teams;

	public static void importFile() {
		//Creates a file dialogue
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Pace File");
		
		//Get team script with results from file dialog
		teams = getTeams(fileChooser.showOpenDialog(fxMain.sMRef));
		
		//If getting teams was not successful
		if(teams == null) return;
		if(teams.size() == 0) {
			//TODO Warning for not able to import file
			return;
		}
		openOptions();
	}
	
	
	private static Stage sOptions;
	
	private static CheckBox cClearTeams;
	private static CheckBox cKeepExisting;
	
	
	private static void openOptions() {
		//TODO convert this to use an Alert
		if(sOptions != null) sOptions.close();
		sOptions = new Stage();
		sOptions.setTitle("Import Options");
		sOptions.initModality(Modality.APPLICATION_MODAL);

		//Option Nodes
		
		cClearTeams = new CheckBox("Clear Teams");
		cClearTeams.setTooltip(new Tooltip("Clear the team list before importing teams?"));
		cClearTeams.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				cKeepExisting.setDisable(cClearTeams.isSelected());
			}
		});
		
		cKeepExisting = new CheckBox("Keep Existing");
		cKeepExisting.setSelected(true);
		cKeepExisting.setTooltip(new Tooltip("Keep Teams that the imported teams doesn't update"));
		
		//HBottom Nodes
		Button bCancel = new Button("Cancel");
		bCancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sOptions.close();
			}
		});
		Region rb = new Region();
		Button bImport = new Button("Import");
		bImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				importTeams(teams, cClearTeams.isSelected(),cKeepExisting.isSelected());
				sOptions.close();
			}
		});
		
		// Creating HBottom
		HBox hBottom = new HBox(bCancel,rb,bImport);
		HBox.setHgrow(rb, Priority.ALWAYS);
		
		VBox dt = new VBox(cClearTeams,cKeepExisting,hBottom);
		dt.setSpacing(10);
		dt.setPadding(new Insets(10,10,10,10));
		
		Scene sc = new Scene(dt,500,100);
		sOptions.setScene(sc);
		sOptions.show();
	}
	
	private static void importTeams(List<Team> tms, boolean clearTeams, boolean keepExisting) {
		//Clears Teams if configured to do so
		if(clearTeams) {
			Pace.teams = tms;
		} else {
			//Only used if set to not keep unique
			List<Team> setTeams = new ArrayList<Team>();
			
			for(Team a : tms) {
				for(Team b : Pace.teams) {
					if(a.team.contentEquals(b.team)) {
						b.names = a.names;
						b.division = a.division;
						if(!keepExisting) {
							setTeams.add(b);
						}
					}
				}
			}
			
			if(!keepExisting) Pace.teams = setTeams;
		}
		fxMain.updateTable();
	}	

	
	private static List<Team> getTeams(File file) {
		if(file == null) return null;
		List<Team> teams = new ArrayList<Team>();
		try {
			FileInputStream stream = new FileInputStream(file);
			// Finds the workbook instance for XLSX file and gets the first spreadsheet
			XSSFWorkbook workbook = new XSSFWorkbook (stream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			int posRow = 0;
			int posCol = 0;
			//Goes through each row
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				posRow++;
				Row row = rowIterator.next();
				
				//Goes through each column
				if(posRow > 2) {
					Team r = new Team();
					posCol = 0;
					Iterator<Cell> cellIterator = row.cellIterator();
					while(cellIterator.hasNext()) {
						posCol++;
						Cell cell = cellIterator.next();
						
						if(posCol == 2) {
							r.division = getString(cell).toLowerCase();
							if(r.division.length() > 1) r.division = r.division.substring(0, 1).toUpperCase() + r.division.substring(1);
						} else if (posCol == 3) {
							r.team = getString(cell);
							
						} else if (posCol == 4 || posCol == 5 || posCol == 6) {
							r.names.add(getString(cell));
						}
						
					}
					while(r.names.contains("")) r.names.remove("");
					if(r.names.size() > 0 && !r.team.contentEquals("")) {
						teams.add(r);
					}
				}
			}
			workbook.close();
		} catch(IOException e) {
			return null;
		}
		return teams;
	}
	
	
	//Returns the string data of the cell, regardless of the cell type
	private static String getString(Cell cell) {
		try {
			return cell.getStringCellValue();
		} catch(Exception e) {
			try {
				double a = cell.getNumericCellValue();
				if((int) a == a) return (int) a + "";
				else return a + "";
			} catch(Exception f) {
				try {
					return cell.getBooleanCellValue() + "";
				} catch(Exception g) {}
			}
		}
		return "";
	}
}
