package app.database;

import api.database.ITimeInstance;

/**
 * @author Thomas Kwashnak
 */
public class TimeInstance implements ITimeInstance {

    private int time;

    public TimeInstance() {
        time = 0;
    }

    public TimeInstance(int time) {
        this.time = time;
    }

    public TimeInstance(ITimeInstance other) {
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
        return null;
    }

    @Override
    public void add(int time) {
        this.time += time;
    }

    @Override
    public ITimeInstance getNegative() {
        return new TimeInstance(-time);
    }

    @Override
    public void absolute() {
        time = Math.abs(time);
    }

    @Override
    public ITimeInstance getAbsolute() {
        return new TimeInstance(Math.abs(time));
    }
}
