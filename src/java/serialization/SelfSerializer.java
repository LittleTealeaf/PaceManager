package serialization;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public interface SelfSerializer<T extends SelfSerializer> extends Serializable {

    default void serialize(File file) throws IOException, ClassNotFoundException {
        new Serializer<SelfSerializer<T>>().serialize(this, file);
    }
}
