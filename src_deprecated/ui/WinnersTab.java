package ui;

import data_deprecated.Division;
import javafx.scene.control.Tab;

//https://www.baeldung.com/java-pdf-creation

public class WinnersTab extends Tab {
    private Division division;

    public WinnersTab(Division division) {
        super(division.getName());
        setClosable(false);
        this.division = division;


        Team[] teams = division.getPlaceOrder();

    }
}
