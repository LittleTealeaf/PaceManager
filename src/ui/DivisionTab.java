package ui;

import app.App;
import data.Division;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/*
TODO change this to another tab view
each tab is another division with its own updating. One tab is the "general" list of data (like what we have here).
so like
Overview | Default | Pleasure | Hunt | Western | Junior
 */

public class DivisionTab extends TabPane implements Updatable {


    private DivList[] divLists;
    private boolean tabClose;


    public DivisionTab() {
        super();
        divLists = new DivList[0];
        Tab tab = new Tab("TEESTSETST");
        getTabs().add(tab);
        update();
        tabClose = false;
    }

    public void update() {
        int length = App.openedPace.getDivisions().size();
        if(divLists.length != length && !tabClose) {
            if(divLists.length > 0) {
                getTabs().remove(1,divLists.length);
            }
            divLists = new DivList[length];
            for(int i = 0; i < length; i++) {
                divLists[i] = new DivList(this, App.openedPace.getDivisions().get(i));
            }
            getTabs().addAll(divLists);
        } else {
            tabClose = false;
            for(int i = 0; i < divLists.length; i++) {
                divLists[i].update();
            }
        }
    }

    private class DivList extends Tab implements Updatable {

        TeamTable table;
        Division division;
        DivisionTab parent;

        public DivList(DivisionTab parent, Division division) {
            super(division.getName());
            this.division = division;
            this.parent = parent;
            BorderPane content = new BorderPane();
            table = new TeamTable(() -> this.division.getTeams());
            table.getColumns().remove(0);
            content.setCenter(table);
            setContent(content);

            setOnCloseRequest(e -> {
                if(App.settings.warnOnDelete() && App.warnDelete(this.division.getName())) {
                    App.openedPace.removeDivision(this.division);
                    this.parent.tabClose = true;
                    App.update();
                }
            });

            update();
        }

        public void update() {
            table.update();
        }
    }

//    private class DivisionView extends GridPane implements Updatable {
//
//    private DivisionRow[] divisions;
//
//    public DivisionView() {
//        super();
//        setHgap(10);
//        setVgap(10);
//        setPadding(new Insets(10));
//        setOnKeyPressed(e -> {
//            if (e.getCode() == KeyCode.ESCAPE) {
//                reBuild();
//            }
//        });
//        update();
//    }
//
//    public void update() {
//        if (divisions == null || divisions.length != App.openedPace.getDivisions().size()) {
//            reBuild();
//        } else {
//            for (DivisionRow divRow : divisions) {
//                divRow.update();
//            }
//        }
//
//    }
//
//    public void reBuild() {
//        getChildren().clear();
//
//        String[] headers = new String[]{"Division", "# Teams", "Average", "Goal Time", "Deviation", "Deviation %"};
//        for (int i = 0; i < headers.length; i++) {
//            Text header = new Text(headers[i]);
//            header.setFont(new Font(15));
//            add(header, i, 0);
//        }
//        divisions = new DivisionRow[App.openedPace.getDivisions().size()];
//        for (int i = 0; i < divisions.length; i++) {
//            divisions[i] = new DivisionRow(App.openedPace.getDivisions().get(i));
//            divisions[i].addRow(this, i + 1);
//        }
//    }
//
//    private class DivisionRow {
//
//        final Division division;
//        final TextField divisionName;
//        final Text numRiders;
//        final Text averageTime;
//        final TimeInput goalTime;
//        final Text deviationTime;
//        final Text deviationPercent;
//
//        DivisionRow(Division division) {
//            this.division = division;
//            divisionName = new TextField();
//            divisionName.focusedProperty().addListener((e, o, n) -> {
//                if (!e.getValue().booleanValue()) {
//                    division.setName(divisionName.getText());
//                    App.update();
//                }
//            });
//            divisionName.setOnKeyPressed(e -> {
//                if (e.getCode() == KeyCode.ENTER) {
//                    division.setName(divisionName.getText());
//                    App.update();
//                }
//            });
//            numRiders = new Text();
//            averageTime = new Text();
//            goalTime = new TimeInput();
//            goalTime.addTimeListener((o, n) -> {
//                division.setGoalTime(n);
//                App.update();
//            });
//            deviationTime = new Text();
//            deviationPercent = new Text();
//            update();
//        }
//
//        void update() {
//            divisionName.setText(division.getName());
//            numRiders.setText(Integer.toString(division.getTeams().size()));
//
//            Time timeAvg = division.getAverageTime();
//            averageTime.setText(timeAvg != null ? timeAvg.toString() : "-");
//
//            goalTime.setTime(division.getGoalTime());
//            if (goalTime.getTime() != null && timeAvg != null) {
//                Time devTime = new Time(timeAvg.getValue() - goalTime.getTime().getValue());
//                deviationTime.setText(devTime.toString());
//
//                double percent = ((double) devTime.getValue() / goalTime.getTime().getValue());
//                deviationPercent.setText(new DecimalFormat("##.##%").format(percent));
//            } else {
//                deviationTime.setText("-");
//                deviationPercent.setText("-");
//            }
//        }
//
//        void addRow(GridPane gridPane, int row) {
//            gridPane.add(divisionName, 0, row);
//            gridPane.add(numRiders, 1, row);
//            gridPane.add(averageTime, 2, row);
//            gridPane.add(goalTime, 3, row);
//            gridPane.add(deviationTime, 4, row);
//            gridPane.add(deviationPercent, 5, row);
//        }
//    }
//    }

}
