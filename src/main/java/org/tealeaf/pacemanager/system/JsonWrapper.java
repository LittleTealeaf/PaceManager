package org.tealeaf.pacemanager.system;

import com.google.gson.Gson;
import org.hildan.fxgson.FxGson;

import java.lang.reflect.Modifier;

public class JsonWrapper {

    public static final Gson gson;

    static {
        gson = FxGson.coreBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
    }
}
