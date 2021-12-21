package data;

import interfaces.Copyable;
import interfaces.ITime;
import interfaces.Valuable;

import java.io.*;

public class Time implements Serializable, Cloneable, ITime, Valuable<Long>, Copyable<Time> {

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
    public Time clone() throws CloneNotSupportedException {
        Time clone;
        try {
            clone = (Time) super.clone();
            clone.setTime(this.time);
        } catch (CloneNotSupportedException exception) {
            clone = new Time(this);
        }
        return clone;
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeLong(time);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        time = in.readLong();
    }

    @Override
    public Time absolute() {
        return new Time(Math.abs(time));
    }

    @Override
    public Time add(Time other) {
        return new Time(time + other.time);
    }

    @Override
    public Time negative() {
        return new Time(-time);
    }

    public Long getValue() {
        return getTime();
    }

    public void setValue(Long value) {
        setTime(value);
    }

    @Override
    public Time copy() {
        return new Time(this);
    }
}
