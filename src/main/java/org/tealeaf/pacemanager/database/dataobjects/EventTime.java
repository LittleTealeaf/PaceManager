package org.tealeaf.pacemanager.database.dataobjects;

import java.util.function.Function;

public class EventTime {
    private long time;

    public EventTime() {
        time = 0;
    }

    public boolean parse(String string) {
        return false;
    }

    public EventTime subtract(EventTime other) {
        return newEventTime(time - other.getTime());
    }

    protected EventTime newEventTime(long time) {
        return new EventTime(){{
            setTime(time);
        }};
    }

    @Override
    public String toString() {
        return Long.toString(time);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    public void modify(Function<Long,Long> function) {
        setTime(function.apply(time));
    }
}
