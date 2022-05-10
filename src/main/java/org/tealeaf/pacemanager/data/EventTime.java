package org.tealeaf.pacemanager.data;

public record EventTime(long time) {

    @Override
    public String toString() {
        return Long.toString(time);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EventTime eventTime && eventTime.time == time;
    }
}
