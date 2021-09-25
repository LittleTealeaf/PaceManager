package ui;

import data.Time;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;
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
     *
     * @since 1.0.0
     */
    private Time time;

    private List<TimeListener> timeListeners;

    /**
     * Creates a new {@code TimeInput} object with a set Time of 0
     *
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
        timeListeners = new ArrayList<>();
        focusedProperty().addListener((e, o, n) -> {
            if (!e.getValue().booleanValue()) {
                parseText();
            } else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        selectAll();
                    }
                });
            }
        });
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                parseText();
            }
        });
        setTime(time);
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
        Time oldValue = time;
        try {
            if (getText() == null || getText().contentEquals("")) {
                time = null;
            } else {
                time = new Time(getText());
            }
        } catch (Exception ignored) {}

        Time newValue = time;
        if (oldValue != newValue) {
            for (TimeListener listener : timeListeners) {
                listener.valueChanged(oldValue, newValue);
            }
        }

        updateText();
    }

    public List<TimeListener> getTimeListeners() {
        return timeListeners;
    }

    public void setTimeListeners(List<TimeListener> listeners) {
        this.timeListeners = listeners;
    }

    public void addTimeListener(TimeListener listener) {
        timeListeners.add(listener);
    }

    public interface TimeListener {
        void valueChanged(Time newValue, Time oldValue);
    }
}
