package ui;

import app.App;
import data.Division;
import data.Pace;
import javafx.scene.control.ComboBox;

import java.awt.*;

public class DivisionSelector extends ComboBox<String> {

    private final Division[] divisions;

    public DivisionSelector() {
        super();
        setEditable(true);
        divisions = App.openedPace.getDivisions().toArray(new Division[0]);
        for(Division division : divisions) {
            getItems().add(division.getName());
        }
    }

    public Division getDivision() {
        if(getSelectionModel().getSelectedIndex() != -1) {
            return divisions[getSelectionModel().getSelectedIndex()];
        } else {
            Division division = new Division(getSelectionModel().getSelectedItem());
            App.openedPace.getDivisions().add(division);
            App.openedPace.save();
            return division;
        }
    }
}
