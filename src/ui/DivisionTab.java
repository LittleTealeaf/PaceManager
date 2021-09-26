package ui;

import app.App;
import data.Division;
import data.Time;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class DivisionTab extends GridPane implements Updatable {

    private DivisionRow[] divisions;

    public DivisionTab() {
        super();
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10));
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                reBuild();
            }
        });
        update();
    }

    public void update() {
        if (divisions == null || divisions.length != App.openedPace.getDivisions().size()) {
            reBuild();
        } else {
            for (DivisionRow divRow : divisions) {
                divRow.update();
            }
        }

    }

    public void reBuild() {
        getChildren().clear();

        String[] headers = new String[]{"Division", "# Teams", "Average", "Goal Time", "Deviation", "Deviation %"};
        for (int i = 0; i < headers.length; i++) {
            Text header = new Text(headers[i]);
            header.setFont(new Font(15));
            add(header, i, 0);
        }
        divisions = new DivisionRow[App.openedPace.getDivisions().size()];
        for (int i = 0; i < divisions.length; i++) {
            divisions[i] = new DivisionRow(App.openedPace.getDivisions().get(i));
            divisions[i].addRow(this, i + 1);
        }
    }

    private static class DivisionRow {

        final Division division;
        final TextField divisionName;
        final Text numRiders;
        final Text averageTime;
        final TimeInput goalTime;
        final Text deviationTime;
        final Text deviationPercent;

        DivisionRow(Division division) {
            this.division = division;
            divisionName = new TextField();
            divisionName.focusedProperty().addListener((e, o, n) -> {
                if (!e.getValue().booleanValue()) {
                    division.setName(divisionName.getText());
                    App.updateApplication();
                }
            });
            divisionName.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    division.setName(divisionName.getText());
                    App.updateApplication();
                }
            });
            numRiders = new Text();
            averageTime = new Text();
            goalTime = new TimeInput();
            goalTime.addTimeListener((o, n) -> {
                division.setGoalTime(n);
                App.updateApplication();
            });
            deviationTime = new Text();
            deviationPercent = new Text();
            update();
        }

        void update() {
            divisionName.setText(division.getName());
            numRiders.setText(Integer.toString(division.getTeams().size()));

            Time timeAvg = division.getAverageTime();
            averageTime.setText(timeAvg != null ? timeAvg.toString() : "-");

            goalTime.setTime(division.getGoalTime());
            if (goalTime.getTime() != null && timeAvg != null) {
                Time devTime = new Time(timeAvg.getValue() - goalTime.getTime().getValue());
                deviationTime.setText(devTime.toString());

                double percent = ((double) devTime.getValue() / goalTime.getTime().getValue());
                deviationPercent.setText(new DecimalFormat("##.##%").format(percent));
            } else {
                deviationTime.setText("-");
                deviationPercent.setText("-");
            }
        }

        void addRow(GridPane gridPane, int row) {
            gridPane.add(divisionName, 0, row);
            gridPane.add(numRiders, 1, row);
            gridPane.add(averageTime, 2, row);
            gridPane.add(goalTime, 3, row);
            gridPane.add(deviationTime, 4, row);
            gridPane.add(deviationPercent, 5, row);
        }
    }
}
