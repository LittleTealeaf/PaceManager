package data;

public class Time {

    private final long time;

    public Time() {
        time = 0;
    }

    public Time(long time) {
        this.time = time;
    }

    public static Time difference(Time start, Time end) {
        return new Time(end.getTime() - start.getTime());
    }

    public long getTime() {
        return time;
    }

}
