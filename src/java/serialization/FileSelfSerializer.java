package serialization;

import java.io.IOException;
import java.io.Serializable;

public interface FileSelfSerializer<T extends Serializable> extends SelfSerializer<T>, Fileable {

    default void serialize() throws IOException, ClassNotFoundException {
        serialize(getFile());
    }
}
