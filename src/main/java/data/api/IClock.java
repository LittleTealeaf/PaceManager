package data.api;

public interface IClock {

    int getTime();

    void setTime(int time);

    IClock getAdd(IClock other);

    IClock getAdd(int time);

    IClock getSubtract(IClock other);

    IClock getSubtract(int time);

    IClock getAbs();

    IClock getElapsed(IClock other);

    void add(IClock other);

    void add(int time);

    void subtract(IClock other);

    void subtract(int time);

    void abs();

    String asString();
}
