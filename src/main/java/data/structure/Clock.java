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

    public Clock(IClock clock) {
        this.time = clock.getTime();
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
    public IClock add(IClock other) {
        return new Clock(getTime() + other.getTime());
    }

    @Override
    public IClock subtract(IClock other) {
        return new Clock(getTime() - other.getTime());
    }

    @Override
    public IClock abs() {
        return new Clock(Math.abs(getTime()));
    }

    @Override
    public IClock absoluteDifference(IClock other) {
        return subtract(other).abs();
    }

    @Override
    public String asString() {
        //TODO: implement
        return "";
    }
}
