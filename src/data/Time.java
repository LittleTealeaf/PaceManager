package data;

import java.io.*;

public class Time implements Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = 42L;

    private transient long time;

    public Time() {
        time = 0;
    }

    public Time(long time) {
        this.time = time;
    }

    public Time(Time other) {
        time = other.time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTime(Time other) {
        time = other.time;
    }

    @Override
    public int hashCode() {
        return (int) (time ^ (time >>> 32));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Time time && time.time == this.time;
    }

    @Override
    public Time clone() {
        return new Time(this);
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeLong(time);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        time = in.readLong();
    }
}
