package interfaces;

public interface Copyable<T extends Copyable<T>> extends Cloneable {

    T copy();
}
