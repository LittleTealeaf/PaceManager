package ui;

import app.App;
import data.Division;
import data.Time;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DivisionView extends TabPane implements Updatable {

    //BUG: deleting a division causes the default division's name to be set to ""


    private List<DivisionTab> divisionTabs;
    private final GeneralTab generalTab;


    public DivisionView() {
        super();
        divisionTabs = new ArrayList<>();
        getTabs().add(generalTab = new GeneralTab(this));
        update();
    }

    public void update() {
        generalTab.update();
        if (divisionTabs.size() != App.openedPace.getDivisions().size()) {
            int selIndex = getSelectionModel().getSelectedIndex();

            List<Division> divisions = App.openedPace.getDivisions();
            List<DivisionTab> newDivisionTabs = new ArrayList<>();

            for (Division div : divisions) {
                DivisionTab divisionTab = null;
                for (DivisionTab divList : divisionTabs) {
                    if (divList.division == div) {
                        divisionTab = divList;
                        break;
                    }
                }
                if (divisionTab == null) {
                    divisionTab = new DivisionTab(this, div);
                }
                newDivisionTabs.add(divisionTab);
            }

            getTabs().remove(1, getTabs().size());
            getTabs().addAll(newDivisionTabs);
            divisionTabs = newDivisionTabs;

            if (selIndex > 0) {
                getSelectionModel().select(selIndex);
            }

        } else {
            for (DivisionTab divisionTab : divisionTabs) {
                divisionTab.update();
            }
        }
    }

    private class GeneralTab extends Tab implements Updatable {

        DivisionView parent;
        DivisionPanel[] panels;
        GridPane gridPane = new GridPane();


        GeneralTab(DivisionView parent) {
            super("General");
            setClosable(false);
            gridPane = new GridPane();
            gridPane.setHgap(8);
            gridPane.setVgap(8);
            gridPane.setPadding(new Insets(10));
            this.parent = parent;

            setContent(gridPane);
            update();
        }

        public void update() {
            if (panels == null || panels.length != App.openedPace.getDivisions().size()) {
                gridPane.getChildren().clear();
                Text[] headers = new Text[]{
                        new Text("Name"), new Text("Optimum Time"), new Text("Average Time"), new Text(
                        "Deviation Time"),
                        new Text("Deviation %")
                };
                for (Text t : headers) {
                    t.setFont(new Font(15));
                }
                gridPane.addRow(0, headers);


                panels = new DivisionPanel[App.openedPace.getDivisions().size()];
                for (int i = 0; i < panels.length; i++) {
                    panels[i] = new DivisionPanel(App.openedPace.getDivisions().get(i));
                    Node[] nodes = panels[i].asArray();
                    for (int j = 0; j < nodes.length; j++) {
                        gridPane.add(nodes[j], j, i + 1);
                    }
                }
            } else {
                for (int i = 0; i < panels.length; i++) {
                    panels[i].update();
                }
            }
        }
    }

    private class DivisionTab extends Tab implements Updatable {

        TeamTable table;
        Division division;
        DivisionView parent;
        DivisionPanel divisionPanel;

        Button buttonDeleteDivision;
        Button buttonSetAsDefault;
        HBox boxButtons;


        DivisionTab(DivisionView parent, Division division) {
            super(division.getName());
            setClosable(false);
            this.division = division;
            this.parent = parent;
            divisionPanel = new DivisionPanel(division);

            BorderPane content = new BorderPane();

            //Button Panel
            buttonDeleteDivision = new Button("Delete");
            buttonDeleteDivision.setOnAction(e -> {
                if ((!App.settings.warnOnDelete() || App.warnDelete(
                        this.division.getName())) && App.openedPace.removeDivision(this.division)) {
                    App.update();
                }
            });
            buttonSetAsDefault = new Button("Make Default");
            buttonSetAsDefault.setOnAction(e -> {
                if (App.openedPace.setDefaultDivision(this.division)) {
                    App.update();
                }
            });

            boxButtons = new HBox();
            boxButtons.setPadding(new Insets(7));
            boxButtons.setSpacing(7);

            BorderPane bottomField = new BorderPane();
            HBox divisionsPanelHBox = divisionPanel.asHBox();
            divisionsPanelHBox.setPadding(new Insets(6));
            bottomField.setLeft(divisionsPanelHBox);
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

            if (this.division != App.openedPace.getDefaultDivision()) {
                boxButtons.getChildren().setAll(buttonSetAsDefault, buttonDeleteDivision);
            } else {
                Text defaultMessage = new Text("You cannot delete the\ndefault division");
                defaultMessage.setFont(new Font(11));
                boxButtons.getChildren().setAll(defaultMessage);
            }
            divisionPanel.update();
        }


    }

    private class DivisionPanel implements Updatable {

        static final int FONT_SIZE = 13;

        Division division;

        TextField name;
        TimeInput goalTime;
        Text averageTime, deviationTime, deviationPercent;


        public DivisionPanel(Division division) {
            this.division = division;
            name = new TextField();
            name.focusedProperty().addListener((e, o, n) -> {
                if (!e.getValue().booleanValue()) {
                    this.division.setName(name.getText());
                    App.update();
                }
            });
            goalTime = new TimeInput();
            goalTime.addTimeListener((o, n) -> {
                this.division.setGoalTime(n);
                App.update();
            });
            averageTime = new Text();
            deviationTime = new Text();
            deviationPercent = new Text();
            Font font = new Font(FONT_SIZE);
            averageTime.setFont(font);
            deviationTime.setFont(font);
            deviationPercent.setFont(font);
        }

        public void update() {
            name.setText(division.getName());
            goalTime.setTime(division.getGoalTime());

            Time avg = division.getAverageTime();
            averageTime.setText((avg == null ? "-" : avg.toString()));
            if (avg != null && division.getGoalTime() != null) {
                Time deviation = avg.subtract(division.getGoalTime());
                deviationTime.setText(deviation.toString());
                double percent = ((double) deviation.getValue() / goalTime.getTime().getValue());
                deviationPercent.setText(new DecimalFormat("##.##%").format(percent));
            } else {
                deviationPercent.setText("");
                deviationTime.setText("");
            }
        }

        public HBox asHBox() {
            Text[] labels = new Text[]{
                    new Text("Division:"),
                    new Text("Goal Time:"),
                    new Text("Average:"),
                    new Text("Deviation")
            };
            Font font = new Font(FONT_SIZE);
            for (Text text : labels) {
                text.setFont(font);
            }
            HBox hbox = new HBox(labels[0], name, labels[1], goalTime, labels[2], averageTime, labels[3], deviationTime,
                                 deviationPercent);
            hbox.setSpacing(7);

            return hbox;
        }

        public Node[] asArray() {
            return new Node[]{
                    name, goalTime, averageTime, deviationTime, deviationPercent
            };
        }
    }
}
