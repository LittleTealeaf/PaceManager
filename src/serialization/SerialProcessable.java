package serialization;

import java.io.Serializable;

public interface SerialProcessable extends Serializable {

    default void preSerialization() {}

    default void postSerialization() {}

    default void postDeserialization() {}

    default void preDeserialization() {}
}
