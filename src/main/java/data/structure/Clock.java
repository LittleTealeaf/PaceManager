package data.structure;

import data.api.IClock;

public class Clock implements IClock {

    private int time;

    public Clock() {
        time = 0;
    }

    public Clock(int time) {
        this.time = time;
    }

    public Clock(IClock other) {
        this.time = other.getTime();
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String asString() {
        return "";
    }
}
