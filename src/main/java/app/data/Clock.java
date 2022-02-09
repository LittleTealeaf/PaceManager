package app.data;

import api.data.IClock;

/**
 * @author Thomas Kwashnak
 */
public class Clock implements IClock {

    private final int time;

    public Clock() {
        time = 0;
    }

    public Clock(int time) {
        this.time = time;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public String asString() {
        return "";
    }

    @Override
    public IClock getAdd(IClock other) {
        return new Clock(time + other.getTime());
    }

    @Override
    public IClock getSubtract(IClock other) {
        return new Clock(time - other.getTime());
    }

    @Override
    public IClock getNegative() {
        return new Clock(-time);
    }
}
