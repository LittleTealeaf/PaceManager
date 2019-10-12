package application;


import classes.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class fxGoals {

	private static Stage sGoals;
	private static TableView<Goal> table;
	private static Button bCreate;
	private static Button bImport;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void open() {
		if(sGoals != null) sGoals.close();
		sGoals = new Stage();
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
		TableColumn tGoal = new TableColumn("Optimal Time");
		tGoal.setCellValueFactory(new PropertyValueFactory<Goal,String>("displayTime"));
		table.getColumns().addAll(tDiv,tGoal);
		
		HBox hBottom = new HBox();
		hBottom.setSpacing(10);
		bCreate = new Button("New");
		bImport = new Button("Import");
		
		hBottom.getChildren().addAll(bCreate,bImport);

		VBox vb = new VBox();
		vb.getChildren().addAll(table,hBottom);
		Scene sc = new Scene(vb,500,300);
		sGoals.setScene(sc);
		resizeWindow();
		sGoals.show();
	}
	
	private static void resizeWindow() {
		double w = sGoals.getWidth();
		double h = sGoals.getHeight();
		System.out.println(w + " " + h);

		table.getColumns().get(0).setPrefWidth(0.5*table.getWidth());
		table.getColumns().get(1).setPrefWidth(0.5*table.getWidth());
	}
}
