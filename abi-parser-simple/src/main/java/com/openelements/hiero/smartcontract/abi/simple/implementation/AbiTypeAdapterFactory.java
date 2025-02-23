package com.openelements.hiero.smartcontract.abi.simple.implementation;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.openelements.hiero.smartcontract.abi.model.AbiEntry;
import com.openelements.hiero.smartcontract.abi.model.AbiModel;
import com.openelements.hiero.smartcontract.abi.model.AbiParameter;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public class AbiTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(@NonNull final Gson gson, @NonNull final TypeToken<T> type) {
        Objects.requireNonNull(type, "type must not be null");
        if (type.getRawType().equals(AbiModel.class)) {
            return (TypeAdapter<T>) new AbiModelTypeAdapter(gson);
        }
        if (type.getRawType().equals(AbiEntry.class)) {
            return (TypeAdapter<T>) new AbiEntryTypeAdapter(gson);
        }
        if (type.getRawType().equals(AbiParameter.class)) {
            return (TypeAdapter<T>) new AbiParameterTypeAdapter(gson);
        }
        return null;
    }
}
