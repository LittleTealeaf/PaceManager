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
    public IClock getAdd(IClock other) {
        return new Clock(this).add(other);
    }

    @Override
    public IClock getAdd(int time) {
        return new Clock(getTime() + time);
    }

    @Override
    public IClock getSubtract(IClock other) {
        return new Clock(getTime() - other.getTime());
    }

    @Override
    public IClock getSubtract(int time) {
        return new Clock(getTime() - time);
    }

    @Override
    public IClock getAbs() {
        return new Clock(Math.abs(getTime()));
    }

    @Override
    public IClock getElapsed(IClock other) {
        return getSubtract(other).getAbs();
    }

    @Override
    public IClock add(IClock other) {
        this.time += other.getTime();
        return this;
    }

    @Override
    public IClock add(int time) {
        this.time += time;
        return this;
    }

    @Override
    public IClock subtract(IClock other) {
        this.time -= other.getTime();
        return this;
    }

    @Override
    public IClock subtract(int time) {
        this.time += time;
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
}
