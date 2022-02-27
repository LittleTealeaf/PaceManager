package api.data;

public interface IClock {

    String TIME = IClock.class.getName() + ":TIME";

    int getTime();

    void setTime();

    String asString();
}
