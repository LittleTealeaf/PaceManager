package serialization;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public interface SelfSerializer<T extends Serializable> extends Serializable, Fileable {

    default void serialize() throws IOException, ClassNotFoundException {
        serialize(getFile());
    }

    default void serialize(File file) throws IOException, ClassNotFoundException {
        new Serializer<SelfSerializer>().serialize(this, file);
    }
}
