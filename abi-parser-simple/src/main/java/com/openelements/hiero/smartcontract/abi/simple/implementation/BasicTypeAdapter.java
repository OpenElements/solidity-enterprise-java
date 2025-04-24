package com.openelements.hiero.smartcontract.abi.simple.implementation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import org.jspecify.annotations.NonNull;

/**
 * Abstract class for Gson adapters that read and write JSON objects.
 * @param <T> the type of the object to be read or written
 */
public abstract class BasicTypeAdapter<T> extends TypeAdapter<T> {

    private final Gson gson;

    private final TypeAdapter<JsonObject> objectTypeAdapter;

    private final TypeAdapter<JsonArray> arrayTypeAdapter;

    public BasicTypeAdapter(@NonNull final Gson gson) {
        this.gson = Objects.requireNonNull(gson, "gson");
        objectTypeAdapter = gson.getAdapter(JsonObject.class);
        arrayTypeAdapter = gson.getAdapter(JsonArray.class);
    }

    protected JsonObject readObject(@NonNull final JsonReader in) throws IOException {
        return objectTypeAdapter.read(in);
    }

    protected JsonArray readArray(@NonNull final JsonReader in) throws IOException {
        return arrayTypeAdapter.read(in);
    }

    protected <T> T fromJson(@NonNull final JsonElement json, @NonNull final Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    @NonNull
    protected Gson getGson() {
        return gson;
    }

    @NonNull
    protected TypeAdapter<JsonObject> getObjectTypeAdapter() {
        return objectTypeAdapter;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @NonNull
    protected JsonArray getArray(@NonNull final JsonObject jsonObject, @NonNull final String key) {
        Objects.requireNonNull(jsonObject, "jsonObject");
        Objects.requireNonNull(key, "key");
        if (jsonObject.has(key)) {
            final JsonElement jsonElement = jsonObject.get(key);
            if (jsonElement.isJsonArray()) {
                return jsonElement.getAsJsonArray();
            }
            final JsonArray array = new JsonArray();
            array.add(jsonElement);
        }
        return new JsonArray();
    }

    @NonNull
    protected Optional<String> getStringValue(@NonNull final JsonObject jsonObject, @NonNull final String key) {
        Objects.requireNonNull(jsonObject, "jsonObject");
        Objects.requireNonNull(key, "key");
        if (jsonObject.has(key)) {
            final JsonElement jsonElement = jsonObject.get(key);
            if (jsonElement.isJsonNull()) {
                return Optional.empty();
            }
            return Optional.of(jsonObject.get(key).getAsString());
        }
        return Optional.empty();
    }

    @NonNull
    protected Optional<Boolean> getBooleanValue(@NonNull final JsonObject jsonObject, @NonNull final String key) {
        Objects.requireNonNull(jsonObject, "jsonObject");
        Objects.requireNonNull(key, "key");
        if (jsonObject.has(key)) {
            final JsonElement jsonElement = jsonObject.get(key);
            if (jsonElement.isJsonNull()) {
                return Optional.empty();
            }
            return Optional.of(jsonObject.get(key).getAsBoolean());
        }
        return Optional.empty();
    }

}
