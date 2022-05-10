package org.tealeaf.pacemanager.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hildan.fxgson.FxGson;
import org.hildan.fxgson.FxGsonBuilder;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class JsonWrapper {

    public static final Gson gson;

    static {
        gson = FxGson.coreBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
    }
}
