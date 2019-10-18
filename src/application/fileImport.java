package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import classes.Team;
import javafx.stage.FileChooser;

public class fileImport {
	
	public static void importFile() {
		//Creates a file dialogue
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Pace File");
		
		//Get team script with results from file dialog
		List<Team> teams = getTeams(fileChooser.showOpenDialog(fxMain.sMRef));
		//File could not open
		if(teams == null) return;
		
		//No Teams (add a test to make sure that the file is the valid file)
		if(teams.size() == 0) {
			// Alert that file could not open
			return;
		}
		
		/*
		 * WINDOW: import options
		 * options include:
		 * 	"delete unused teams" - selected unused teams
		 * 	"Overwrite Values of teams? (or select which ones to overwrite)
		 */
		
	}
	
	private static List<Team> getTeams(File file) {
		if(file == null) return null;
		List<Team> teams = new ArrayList<Team>();
		try {
			FileInputStream stream = new FileInputStream(file);
			try {
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
							//System.out.println("Col " + posCol + " cell: " + getString(cell));
							
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
				
			} catch (IOException e) {
				return null;
			}
		} catch (FileNotFoundException e) {
			return null;
		}
		return teams;
	}
	
	
	//Returns the string data of the cell, regardless of the cell type
	private static String getString(Cell cell) {
		switch (cell.getCellType()) {
        case STRING:
            return cell.getStringCellValue();
        case NUMERIC:
            double r = cell.getNumericCellValue();
            if((int) r == (double) r) {
            	return (int) r + "";
            } else return r + "";
        case BOOLEAN:
            return cell.getBooleanCellValue() + "";
        default :
        	return "";
        }
	}
	
	/*
	 * Headers on row 2
	 * Reference:
	 * Columns
	 *  - Time
	 *  - Division
	 *  - Team No
	 *  - *blank*
	 *  - Rider 1
	 *  - Rider 2
	 *  - Rider 3
	 *  
	 */
	
	/*
	public static void open() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Pace File");
		 File myFile = fileChooser.showOpenDialog(fxMain.sMRef);
         FileInputStream fis;
		try {
			fis = new FileInputStream(myFile);
			
			try {
	        	// Finds the workbook instance for XLSX file
	             XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
	            
	             // Return first sheet from the XLSX workbook
	             XSSFSheet mySheet = myWorkBook.getSheetAt(0);
	            
	             // Get iterator to all the rows in current sheet
	             Iterator<Row> rowIterator = mySheet.iterator();
	            
	             // Traversing over each row of XLSX file
	             while (rowIterator.hasNext()) {
	                 Row row = rowIterator.next();

	                 // For each row, iterate through each columns
	                 Iterator<Cell> cellIterator = row.cellIterator();
	                 while (cellIterator.hasNext()) {

	                     Cell cell = cellIterator.next();

	                     switch (cell.getCellType()) {
	                     case STRING:
	                         System.out.print(cell.getStringCellValue() + "\t");
	                         break;
	                     case NUMERIC:
	                         System.out.print(cell.getNumericCellValue() + "\t");
	                         break;
	                     case BOOLEAN:
	                         System.out.print(cell.getBooleanCellValue() + "\t");
	                         break;
	                     default :
	                  
	                     }
	                 }
	                 System.out.println("");
	             }
	         } catch(IOException e) {
	        	 e.printStackTrace();
	         }
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         
	}
	*/
}
