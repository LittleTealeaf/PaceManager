package org.tealeaf.pacemanager.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.reflect.Modifier.TRANSIENT;

public class GsonWrapper {
    public static final Gson gson;

    static {
        gson = new GsonBuilder().excludeFieldsWithModifiers(TRANSIENT).create();
    }

    public static void write(Object object, File file) throws IOException {
        FileWriter writer =  new FileWriter(file);
        gson.toJson(object,writer);
        writer.close();
    }

    static class ObservableAdapterFactory implements TypeAdapterFactory {

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            if(SimpleStringProperty.class.isAssignableFrom(type.getRawType())) {
                return new TypeAdapter<T>() {
                    @Override
                    public void write(JsonWriter out, T value) throws IOException {
                        out.value(((StringProperty) value).get());
                    }

                    @Override
                    public T read(JsonReader in) throws IOException {
                        return (T) new TypeToken<T>() {}.getRawType().cast(new SimpleStringProperty(in.nextString()));
                    }
                };


            } else if(ObjectProperty.class.isAssignableFrom(type.getRawType())) {

            }

            return null;
        }
    }
}
