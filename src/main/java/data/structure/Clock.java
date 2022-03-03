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
    public IClock add(IClock other) {
        return new Clock(getTime() + other.getTime());
    }

    @Override
    public IClock subtract(IClock other) {
        return new Clock(getTime() - other.getTime());
    }

    @Override
    public IClock difference(IClock other) {
        return new Clock(Math.abs(getTime() - other.getTime()));
    }

    @Override
    public String asString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clock)) return false;

        Clock clock = (Clock) o;

        return getTime() == clock.getTime();
    }

    @Override
    public int hashCode() {
        return getTime();
    }
}
