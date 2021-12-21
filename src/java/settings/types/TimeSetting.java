package settings.types;

import data.Time;
import interfaces.ITime;
import settings.Category;

public class TimeSetting extends Setting<Time> implements ITime {

    public TimeSetting(String name, Category... categories) {
        this(name, categories, new Time());
    }

    public TimeSetting(String name, Category[] categories, Time value) {
        super(name, categories, value);
    }

    public TimeSetting(String name, Category category, Time value) {
        super(name, category, value);
    }

    @Override
    public long getTime() {
        return get().getTime();
    }

    @Override
    public void setTime(Time other) {
        get().setTime(other);
    }

    @Override
    public void setTime(long time) {
        get().setTime(time);
    }

    @Override
    public Time absolute() {
        return get().absolute();
    }

    @Override
    public Time subtract(Time other) {
        return get().subtract(other);
    }

    @Override
    public Time add(Time other) {
        return get().add(other);
    }

    @Override
    public Time negative() {
        return get().negative();
    }
}
