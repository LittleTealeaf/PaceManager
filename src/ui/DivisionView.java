package ui;

import app.App;
import data.Division;
import data.Time;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class DivisionView extends TabPane implements Updatable {


    private List<DivisionTab> divisionTabs;


    public DivisionView() {
        super();
        divisionTabs = new ArrayList<>();
        Tab tab = new Tab("General");
        tab.setClosable(false);
        getTabs().add(tab);
        update();
    }

    public void update() {
        if(divisionTabs.size() != App.openedPace.getDivisions().size()) {
            int selIndex = getSelectionModel().getSelectedIndex();

            List<Division> divisions = App.openedPace.getDivisions();
            List<DivisionTab> newDivisionTabs = new ArrayList<>();

            for(Division div : divisions) {
                DivisionTab divisionTab = null;
                for(DivisionTab divList : divisionTabs) {
                    if(divList.division == div) {
                        divisionTab = divList;
                        break;
                    }
                }
                if(divisionTab == null) {
                    divisionTab = new DivisionTab(this, div);
                }
                newDivisionTabs.add(divisionTab);
            }

            getTabs().remove(1,getTabs().size());
            getTabs().addAll(newDivisionTabs);
            divisionTabs = newDivisionTabs;

            if(selIndex > 0) {
                getSelectionModel().select(selIndex);
            }

        } else {
            for(DivisionTab divisionTab : divisionTabs) {
                divisionTab.update();
            }
        }
    }

    private class DivisionTab extends Tab implements Updatable {

        TeamTable table;
        Division division;
        DivisionView parent;
        TextField name;
        TimeInput goalTime;
        Text averageTime, deviationTime, deviationPercent;

        Button buttonDeleteDivision;
        Button buttonSetAsDefault;
        HBox boxButtons;


        public DivisionTab(DivisionView parent, Division division) {
            super(division.getName());
            setClosable(false);
            this.division = division;
            this.parent = parent;

            BorderPane content = new BorderPane();

            final Font labelFont = new Font(13);

            Text nameLabel = new Text("Division:");
            nameLabel.setFont(labelFont);

            name = new TextField();
            name.setText(division.getName());
            name.focusedProperty().addListener((e,o,n) -> {
                if(!e.getValue().booleanValue()) {
                    this.division.setName(name.getText());
                    App.update();
                }
            });

            Text goalLabel = new Text("Optimum Time:");
            goalLabel.setFont(labelFont);

            goalTime = new TimeInput(division.getGoalTime());
            goalTime.addTimeListener((o,n) -> {
                this.division.setGoalTime(n);
                update();
            });

            averageTime = new Text();
            averageTime.setFont(new Font(12));
            deviationTime = new Text();
            deviationTime.setFont(new Font(12));
            deviationPercent = new Text();
            deviationPercent.setFont(new Font(12));


            HBox divInfo = new HBox(nameLabel,name, goalLabel, goalTime, averageTime);
            divInfo.setSpacing(7);
            divInfo.setPadding(new Insets(5));

            //Button Panel
            buttonDeleteDivision = new Button("Delete");
            buttonDeleteDivision.setOnAction(e -> {
                if((!App.settings.warnOnDelete() || App.warnDelete(this.division.getName())) && App.openedPace.removeDivision(this.division)) {
                    App.update();
                }
            });
            buttonSetAsDefault = new Button("Make Default");
            buttonSetAsDefault.setOnAction(e -> {
                if(App.openedPace.setDefaultDivision(this.division)) {
                    App.update();
                }
            });



            boxButtons = new HBox();
            boxButtons.setPadding(new Insets(7));

            BorderPane bottomField = new BorderPane();
            bottomField.setLeft(divInfo);
            bottomField.setRight(boxButtons);

            content.setBottom(bottomField);

            table = new TeamTable(() -> this.division.getTeams());
            table.getColumns().remove(0);
            content.setCenter(table);

            setContent(content);
            update();
        }

        public void update() {
            table.update();
            setText(division.getName());
            name.setText(division.getName());
            goalTime.setTime(division.getGoalTime());

            Time avg = division.getAverageTime();
            averageTime.setText("Average: " + avg == null ? "-" : avg.toString());
//            Time deviation = avg == null ? null : avg.subtract(division.getGoalTime());


            if(this.division != App.openedPace.getDefaultDivision()) {
                boxButtons.getChildren().setAll(buttonSetAsDefault,buttonDeleteDivision);
            } else {
                Text defaultMessage = new Text("You cannot delete the\ndefault division");
                defaultMessage.setFont(new Font(11));
                boxButtons.getChildren().setAll(defaultMessage);
            }
        }


    }
}
