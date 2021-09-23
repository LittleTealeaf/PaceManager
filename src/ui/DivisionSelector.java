package ui;

import app.App;
import data.Division;
import javafx.scene.control.ComboBox;

public class DivisionSelector extends ComboBox<String> {

    private Division[] divisions;

    public DivisionSelector() {
        super();
        setEditable(true);
        updateDivisionList();
    }

    private void updateDivisionList() {
        divisions = App.openedPace.getDivisions().toArray(new Division[0]);
        for (Division division : divisions) {
            getItems().add(division.getName());
        }
    }

    public Division getDivision() {
        if (getSelectionModel().getSelectedIndex() != -1) {
            return divisions[getSelectionModel().getSelectedIndex()];
        } else {
            Division division = new Division(getSelectionModel().getSelectedItem());
            App.openedPace.getDivisions().add(division);
            App.openedPace.save();
            return division;
        }
    }

    public void setDivision(Division division) {
        int index = -1;
        for (int i = 0; i < divisions.length && index == -1; i++) {
            if (divisions[i] == division) {
                index = i;
            }
        }

        if (index != -1) {
            getSelectionModel().select(index);
        }
    }
}
