package interfaces;

import data.Time;

public interface ITime extends Numerical<Time> {

    long getTime();

    void setTime(Time other);

    void setTime(long time);
}
