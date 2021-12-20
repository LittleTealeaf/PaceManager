package serialization;

import java.io.*;

@Deprecated
public class Serializer<T> {

    private final File file;
    private T object;

    public Serializer(File file) {
        this.file = file;
    }

    public Serializer(File file, T object) {
        this.file = file;
        this.object = object;
    }

    public static Serializable deserialize(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Serializable object = (Serializable) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

        if (object instanceof SerialProcessable serialProcessable) {
            serialProcessable.postDeserialization();
        }

        return object;
    }

    public static void serialize(File file, Serializable serializable) throws IOException {
        SerialProcessable serialProcessable = serializable instanceof SerialProcessable processable ? processable : null;
        if (serialProcessable != null) {
            serialProcessable.preSerialization();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.close();
        fileOutputStream.close();
        if (serialProcessable != null) {
            serialProcessable.postSerialization();
        }
    }
}
