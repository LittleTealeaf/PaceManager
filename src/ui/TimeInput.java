package ui;

import data.Time;
import javafx.scene.control.TextArea;

import java.util.Date;

public class TimeInput extends TextArea {

    private Time time;

    public TimeInput() {
        this(new Time());
    }

    public TimeInput(Time time) {
        super();
        this.time = time;

        focusedProperty().addListener((e,o,n) -> {
            if(!e.getValue().booleanValue()) {
                parseText();
            }
        });


    }

    public void updateText() {
        setText(time.toString());
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
        updateText();
    }

    public void parseText() {
        time = new Time(getText());
        updateText();
    }
}
