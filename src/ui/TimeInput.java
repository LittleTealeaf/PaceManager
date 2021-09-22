package ui;

import data.Time;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.Date;

public class TimeInput extends TextField {

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
        setText(time != null ? time.toString() : "");
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
        updateText();
    }

    public void parseText() {
        if(getText() == null || getText().contentEquals("")) {
            time = null;
        } else {
            time = new Time(getText());
        }
        updateText();
    }
}
