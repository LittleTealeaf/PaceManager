package serialization;

import java.io.*;

public class Serializer<T extends Serializable> {

    public void serialize(T serializable, File file) throws IOException {
        if (serializable instanceof Fileable fileable) {
            fileable.setFile(file);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public T deserialize(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

        if (object instanceof Fileable fileable) {
            fileable.setFile(file);
        }
        return (T) object;
    }
}
