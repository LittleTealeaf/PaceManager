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

    IClock add(IClock other);

    IClock add(int time);

    IClock subtract(IClock other);

    IClock subtract(int time);

    IClock abs();

    String asString();
}
