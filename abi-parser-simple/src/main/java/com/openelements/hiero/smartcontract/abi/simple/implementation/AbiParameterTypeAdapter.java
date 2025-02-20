package com.openelements.hiero.smartcontract.abi.simple.implementation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.openelements.hiero.smartcontract.abi.model.AbiParameter;
import com.openelements.hiero.smartcontract.abi.model.AbiParameterType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AbiParameterTypeAdapter extends BasicTypeAdapter<AbiParameter> {

    public AbiParameterTypeAdapter(Gson gson) {
        super(gson);
    }

    @Override
    public AbiParameter read(JsonReader in) throws IOException {
        final JsonObject parameterObject = readObject(in);

        final String name = getStringValue(parameterObject, "name")
                .orElseThrow(() -> new IllegalStateException("Parameter must have a name"));
        final AbiParameterType parameterType = getStringValue(parameterObject, "type")
                .map(AbiParameterType::of)
                .orElseThrow(() -> new IllegalStateException("Parameter must have a type"));
        List<AbiParameter> components = new ArrayList<>();
        getArray(parameterObject, "components").forEach(jsonElement -> {
            components.add(fromJson(jsonElement, AbiParameter.class));
        });
        final boolean indexed = getBooleanValue(parameterObject, "indexed").orElse(Boolean.FALSE);
        return new AbiParameter(name, parameterType, Collections.unmodifiableList(components), indexed);
    }
}
