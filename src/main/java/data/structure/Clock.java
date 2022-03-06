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
        this(clock == null ? 0 : clock.getTime());
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
    public IClock getAdd(IClock other) {
        return other == null ? null : getAdd(other.getTime());
    }

    @Override
    public IClock getAdd(int time) {
        return new Clock(this).add(time);
    }

    @Override
    public IClock getSubtract(IClock other) {
        return other == null ? null : getSubtract(other.getTime());
    }

    @Override
    public IClock getSubtract(int time) {
        return new Clock(this).subtract(time);
    }

    @Override
    public IClock getAbs() {
        return new Clock(this).abs();
    }

    @Override
    public IClock getElapsed(IClock other) {
        return other == null ? null : getSubtract(other).getAbs();
    }

    @Override
    public IClock add(IClock other) {
        return other == null ? this : add(other.getTime());
    }

    @Override
    public IClock add(int time) {
        this.time += time;
        return this;
    }

    @Override
    public IClock subtract(IClock other) {
        return other == null ? this : subtract(other.getTime());
    }

    @Override
    public IClock subtract(int time) {
        this.time -= time;
        return this;
    }

    @Override
    public IClock abs() {
        if (time < 0) {
            time = -time;
        }
        return this;
    }

    @Override
    public String asString() {
        //TODO: implement
        return "";
    }

    @Override
    public int hashCode() {
        return getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Clock)) {
            return false;
        }

        Clock clock = (Clock) o;

        return getTime() == clock.getTime();
    }
}
