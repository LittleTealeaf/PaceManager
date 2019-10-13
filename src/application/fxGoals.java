package application;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import classes.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class fxGoals {
	
	public static boolean alertOnDelete = false;

	private static Stage sGoals;
	private static TableView<Goal> table;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void open() {
		if(sGoals != null) sGoals.close();
		sGoals = new Stage();
		sGoals.setTitle("Goal Times");
		sGoals.widthProperty().addListener((obs,oldv,newv) -> {
			resizeWindow();
		});
		sGoals.heightProperty().addListener((obs,oldv,newv) -> {
			resizeWindow();
		});
		
		table = new TableView<Goal>();
		table.setEditable(false);
		table.prefWidthProperty().bind(sGoals.widthProperty());
		
		
		TableColumn tDiv = new TableColumn("Division");
		tDiv.setCellValueFactory(new PropertyValueFactory<Goal,String>("division"));
		tDiv.widthProperty().addListener((obs,oldv,newv) -> {
			
		});
		TableColumn tGoal = new TableColumn("Optimal Time");
		tGoal.setCellValueFactory(new PropertyValueFactory<Goal,String>("displayTime"));
		table.getColumns().addAll(tDiv,tGoal);
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					try {
						editDiv(table.getSelectionModel().getSelectedItem());
					} catch(IndexOutOfBoundsException e) {}
				}
			}
		});
		table.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if(k.getCode() == KeyCode.DELETE) {
					try {
						deleteDiv(table.getSelectionModel().getSelectedItem());
					} catch(IndexOutOfBoundsException e) {}
				}
			}
		});
		
		HBox hBottom = new HBox();
		hBottom.setSpacing(10);
		Button bCreate = new Button("New");
		bCreate.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				editDiv(null);
			}
		});
		Button bImport = new Button("Import");
		bImport.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				importDivs();
			}
		});
		Button bClose = new Button("Close");
		bClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				sGoals.close();
			}
		});
		Region botRegion = new Region();
		HBox.setHgrow(botRegion, Priority.ALWAYS);
		hBottom.getChildren().addAll(bCreate,bImport,botRegion,bClose);
		hBottom.setPadding(new Insets(5, 10, 5, 10));
		
		VBox vb = new VBox();
		vb.getChildren().addAll(table,hBottom);
		Scene sc = new Scene(vb,500,300);
		sGoals.setScene(sc);
		resizeWindow();
		sGoals.show();
	}
	
	private static Stage sgEdit;
	private static TextField teDiv;
	private static TextField teTime;
	private static Label leError;
	
	private static void editDiv(Goal g) {
		if(sgEdit != null) sgEdit.close();
		sgEdit = new Stage();
		sgEdit.setAlwaysOnTop(true);
		if(g == null) sgEdit.setTitle("Create Goal");
		else sgEdit.setTitle("Edit Goal");
		
		teDiv = new TextField();
		teDiv.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
			if(keyEvent.getCode() == KeyCode.ENTER) {
				leError.setText("");
				if(teDiv.getText() == "") {
					leError.setText("Please include a division name");
				} else teTime.requestFocus();
			}
		});
		Label leDiv = new Label("Division");
		
		teTime = new TextField();
		teTime.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
			if(keyEvent.getCode() == KeyCode.ENTER) {
				eSave(g);
			}
		});
		Label leTime = new Label("Optimal Time");
		
		leError = new Label("");
		
		HBox v1h1 = new HBox(teDiv,leDiv);
		v1h1.setSpacing(10);
		HBox v1h2 = new HBox(teTime,leTime);
		v1h2.setSpacing(10);
		
		VBox vb1 = new VBox(v1h1,v1h2,leError);
		vb1.setSpacing(10);
		
		Button bSave = new Button("Save");
		bSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				eSave(g);
			}
		});
		Button bCancel = new Button("Cancel");
		VBox vb2 = new VBox(bSave,new Label(""),bCancel);
		vb2.setSpacing(10);
		HBox hb = new HBox(vb1,vb2);
		hb.setSpacing(20);
		hb.setPadding(new Insets(15, 12, 15, 12));
		Scene sc = new Scene(hb);
		sgEdit.setScene(sc);
		sgEdit.show();
		if(g != null) {
			teDiv.setText(g.division);
			if(g.time != null) teTime.setText(g.time.toString());
			teTime.requestFocus();
		} else teDiv.requestFocus();
	}
	private static void eSave(Goal g) {
		if(teDiv.getText() == "") {
			leError.setText("Please include a division name");
		} else {
			Time t = new Time(teTime.getText());
			if(t.error == 1) {
				leError.setText("Could not Parse Time");
			} else {
				if(paceManager.goals.size() > 0 && g != null) for(Goal a : paceManager.goals) if(a == g) {
					a.division = teDiv.getText();
					a.time = t;
					updateTable();
					sgEdit.close();
					return;
				}
				Goal v = new Goal(teDiv.getText());
				if(t.error == 0) v.time = t;
				for(Goal a : paceManager.goals) if(a.division.toLowerCase().equals(v.division.toLowerCase())) {
					Alert conf = new Alert(AlertType.CONFIRMATION);
					conf.setTitle("Divison Exists");
					conf.setHeaderText("Would you like to update the existing division?");
					sgEdit.setAlwaysOnTop(false);
					Optional<ButtonType> result = conf.showAndWait();
					sgEdit.setAlwaysOnTop(true);
					if(result.get() == ButtonType.OK) {
						eSave(a);
					} else return;
				}
				paceManager.goals.add(v);
				updateTable();
				sgEdit.close();
			}
		}
	}
	
	private static void importDivs() {
		//Popup
		Alert prompt = new Alert(AlertType.NONE);
		prompt.setTitle("Import");
		prompt.setHeaderText("Do you wish to remove unused goals?");
		ButtonType bYes = new ButtonType("Yes");
		ButtonType bNo = new ButtonType("No");
		ButtonType bCancel = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		prompt.getButtonTypes().addAll(bYes,bNo,bCancel);
		
		Optional<ButtonType> result = prompt.showAndWait();
		boolean remove = false;
		if(result.get() == bYes) remove = true;
		//else if(result.get() == bNo) remove = false;
		else if(result.get() == bCancel) return;
		
		List<String> strDivs = new ArrayList<String>();
		for(Team t : paceManager.teams) if(!strDivs.contains(t.division)) strDivs.add(t.division);
		List<Goal> goals = new ArrayList<Goal>();
		if(paceManager.goals != null) for(Goal g : paceManager.goals) if(strDivs.remove(g.division)) goals.add(g);
		for(String s : strDivs) goals.add(new Goal(s));
		//Adds back remaining if set
		if(!remove) for(Goal g : paceManager.goals) if(!goals.contains(g)) goals.add(g);
		paceManager.goals = goals;
		updateTable();
	}
	
	public static void deleteDiv(Goal g) {
		if(alertOnDelete) {
			Alert conf = new Alert(AlertType.CONFIRMATION);
			conf.setTitle("Delete " + g.division + "?");
			conf.setHeaderText("Do you really want to delete " + g.division + "?");
			Optional<ButtonType> result = conf.showAndWait();
			if(result.get() != ButtonType.OK) return;
		}
		paceManager.goals.remove(g);
		updateTable();
	}
	
	private static void resizeWindow() {
		//System.out.println(w + " " + h);

		table.getColumns().get(0).setPrefWidth(0.5*table.getWidth());
		table.getColumns().get(1).setPrefWidth(0.5*table.getWidth()-5);
		//resizeTable();
	}
	private static void updateTable() {
		if(paceManager.goals != null) {
			System.out.println(paceManager.goals);
			table.getItems().clear();
			table.getItems().addAll(paceManager.goals);
			table.sort();
		}
	}
}
