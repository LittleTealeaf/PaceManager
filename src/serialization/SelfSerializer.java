package serialization;

import java.io.*;

public interface SelfSerializer extends Serializable, SerialProcessable {

    default void serialize() throws IOException, ClassNotFoundException {
        serialize(getFile());
    }

    default void serialize(File file) throws IOException, ClassNotFoundException {
        setFile(file);
        preSerialization();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();
        postSerialization();
    }

    File getFile();

    void setFile(File file);

    default void deserialize(File file) throws IOException, ClassNotFoundException {
        setFile(file);
        preDeserialization();
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        from(object);
        postDeserialization();
    }

    void from(Object other);
}
