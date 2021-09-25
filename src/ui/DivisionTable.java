package ui;

import app.App;
import data.Division;
import data.Time;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class DivisionTable extends GridPane implements Updatable {

    public DivisionTable() {
        super();
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10));
        update();
    }

    public void update() {
        getChildren().clear();

        String[] headers = new String[]{"Division", "# Riders", "Average", "Goal Time", "Deviation", "Deviation %"};
        for (int i = 0; i < headers.length; i++) {
            Text header = new Text(headers[i]);
            header.setFont(new Font(15));
            add(header, i, 0);
        }
        Division[] divisions = App.openedPace.getDivisions().toArray(new Division[0]);
        for (int i = 0; i < divisions.length; i++) {
            Division division = divisions[i];
            int row = i + 1;

            Text divLabel = new Text(division.getName());
            add(divLabel, 0, row);

            Text numRiders = new Text(Integer.toString(division.getTeams().size()));
            add(numRiders, 1, row);

            Time averageTime = division.getAverageTime();
            Text avgTime = new Text(averageTime != null ? averageTime.toString() : "-");
            add(avgTime, 2, row);

            TimeInput goalTime = new TimeInput(division.getGoalTime());
            goalTime.addTimeListener((o, n) -> {
                division.setGoalTime(n);
                App.pingUpdate();
            });
            add(goalTime, 3, row);

            if (averageTime != null && goalTime != null) {
                Time devTime = averageTime.subtract(goalTime.getTime());
                Text deviation = new Text(devTime.toString());
                add(deviation, 4, row);

                double percent = ((double) devTime.getValue() / goalTime.getTime().getValue());
                Text deviationPercent = new Text(new DecimalFormat("##.##%").format(percent));
                add(deviationPercent, 5, row);
            } else {
                add(new Text("-"), 4, row);
                add(new Text("-"), 5, row);
            }
        }
    }
}
