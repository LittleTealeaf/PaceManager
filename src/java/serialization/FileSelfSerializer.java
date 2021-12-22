package serialization;

import java.io.IOException;

public interface FileSelfSerializer<T extends FileSelfSerializer> extends SelfSerializer<T>, Fileable {

    default void serialize() throws IOException, ClassNotFoundException {
        serialize(getFile());
    }
}
