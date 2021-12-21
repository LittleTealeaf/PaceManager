package interfaces;

public interface Numerical<T extends Numerical<T>> {

    T absolute();

    default T subtract(T other) {
        return add(other.negative());
    }

    T add(T other);

    T negative();
}
