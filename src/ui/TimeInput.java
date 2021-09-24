package ui;

import data.Time;
import javafx.scene.control.TextField;
//TODO update javadocs
/**
 * {@code JavaFX} UI Element allowing the user to input a Time into a text box. Uses a {@code TextField} to allow for
 * interaction and display
 * @see Time
 * @since 1.0.0
 * @version 1.0.0
 * @author Thomas Kwashnak
 */
public class TimeInput extends TextField {

    /**
     * The currently stored {@link Time} value
     * @since 1.0.0
     */
    private Time time;

    /**
     * Creates a new {@code TimeInput} object with a set Time of 0
     * @since 1.0.0
     */
    public TimeInput() {
        this(new Time());
    }

    /**
     * Creates a new {@code TimeInput} object with a given Time
     * @param time Initial Time value
     * @since 1.0.0
     */
    public TimeInput(Time time) {
        super();
        this.time = time;
        focusedProperty().addListener((e, o, n) -> {
            if (!e.getValue().booleanValue()) {
                parseText();
            }
        });
    }

    /**
     * Updates the text-box to the value provided from the current Time
     * @see Time#toString()
     * @since 1.0.0
     */
    private void updateText() {
        setText(time != null ? time.toString() : "");
    }

    /**
     * Returns the currently selected time. Note that each time the time is changed, it is a new value instead of
     * just modifying the initial {@code Time} object
     *
     * @return Current time specified. Returns {@code null} if no time is specified
     * @since 1.0.0
     */
    public Time getTime() {
        return time;
    }

    /**
     * Sets the current time and updates the text
     * @param time The new time to display
     * @see #updateText()
     * @since 1.0.0
     */
    public void setTime(Time time) {
        this.time = time;
        updateText();
    }

    /**
     * Parses the current text within the object into a {@code Time} object. Sets the time to null if
     * there is no text specified
     * @see Time#Time(String)
     * @since 1.0.0
     */
    private void parseText() {
        if (getText() == null || getText().contentEquals("")) {
            time = null;
        } else {
            time = new Time(getText());
        }
        updateText();
    }
}
