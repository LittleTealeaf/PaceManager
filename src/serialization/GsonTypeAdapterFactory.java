package serialization;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import interfaces.JsonProcessable;

import java.io.IOException;

public class GsonTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, typeToken);

        return new TypeAdapter<T>() {
            public void write(JsonWriter out, T value) throws IOException {
                if (value instanceof JsonProcessable processable) {
                    processable.preSerialization();
                    delegate.write(out, value);
                    processable.postSerialization();
                } else {
                    delegate.write(out, value);
                }
            }

            public T read(JsonReader in) throws IOException {
                T obj = delegate.read(in);
                if (obj instanceof JsonProcessable processable) {
                    processable.postDeserialization();
                }
                return obj;
            }
        };
    }
}
