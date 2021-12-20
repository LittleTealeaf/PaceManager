package interfaces;

@Deprecated
public interface JsonProcessable {

    default void preSerialization() {}

    default void postSerialization() {}

    default void postDeserialization() {}
}
