package ui;

import app.App;
import data.Division;
import javafx.scene.control.ComboBox;

//TODO update javadocs

/**
 * {@code JavaFX} interface element allowing for a division-selection tool. Uses {@link ComboBox} as a parent class and
 * expands on its functionality
 *
 * @author Thomas Kwashnak
 * @verison 1.0.0
 * @since 1.0.0
 */
public class DivisionSelector extends ComboBox<String> {

    /**
     * List of the divisions selected from the currently opened pace
     *
     * @see App#openedPace
     * @since 1.0.0
     */
    private Division[] divisions;

    /**
     * Creates a new division selector object
     *
     * @since 1.0.0
     */
    public DivisionSelector() {
        super();
        setEditable(true);
        updateDivisionList();
    }

    /**
     * Updates the division list to the current list of divisions from the currently opened pace
     *
     * @see App#openedPace
     * @since 1.0.0
     */
    private void updateDivisionList() {
        divisions = App.openedPace.getDivisions().toArray(new Division[0]);
        for (Division division : divisions) {
            getItems().add(division.getName());
        }
    }

    /**
     * Gets the currently selected division
     *
     * @return The selected division if the user selected a division from the given list.
     * <p>If the user typed in a custom division, it returns a new division with the given name</p>
     * @since 1.0.0
     */
    public Division getDivision() {
        if (getSelectionModel().getSelectedIndex() != -1) {
            return divisions[getSelectionModel().getSelectedIndex()];
        } else {
            Division division = new Division(getSelectionModel().getSelectedItem());
            App.openedPace.getDivisions().add(division);
            App.openedPace.save(); //TODO move this to the Pace class as a result of the Settings option
            return division;
        }
    }

    /**
     * Sets the selected division to the passed parameter. Adds the division to the provided list if it is not already
     * listed.
     *
     * @param division Division to select
     * @since 1.0.0
     */
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
        //TODO add an else clause that manually adds division to the list
    }
}
