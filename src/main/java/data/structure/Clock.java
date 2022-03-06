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
        return new Clock(getTime() + other.getTime());
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
    public void add(IClock other) {
        this.time += other.getTime();
    }

    @Override
    public void add(int time) {
        this.time += time;
    }

    @Override
    public void subtract(IClock other) {
        this.time -= other.getTime();
    }

    @Override
    public void subtract(int time) {
        this.time += time;
    }

    @Override
    public void abs() {
        if (time < 0) {
            time = -time;
        }
    }

    @Override
    public String asString() {
        //TODO: implement
        return "";
    }
}
