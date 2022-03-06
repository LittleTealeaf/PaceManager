package data.api;

public interface IClock {

    int getTime();

    void setTime(int time);

    IClock add(IClock other);

    IClock subtract(IClock other);

    IClock abs();

    IClock absoluteDifference(IClock other);

    String asString();
}
