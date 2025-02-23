package com.openelements.hiero.smartcontract.abi.simple.implementation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.openelements.hiero.smartcontract.abi.model.AbiEntry;
import com.openelements.hiero.smartcontract.abi.model.AbiEntryType;
import com.openelements.hiero.smartcontract.abi.model.AbiFunction;
import com.openelements.hiero.smartcontract.abi.model.AbiParameter;
import com.openelements.hiero.smartcontract.abi.model.StateMutability;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jspecify.annotations.NonNull;

public class AbiEntryTypeAdapter extends BasicTypeAdapter<AbiEntry> {

    public AbiEntryTypeAdapter(Gson gson) {
        super(gson);
    }

    @Override
    public AbiEntry read(@NonNull final JsonReader in) throws IOException {
        final JsonObject abiEntryObject = readObject(in);
        final AbiEntryType type = getStringValue(abiEntryObject, "type")
                .map(AbiEntryType::of)
                .orElseThrow(() -> new IllegalArgumentException("AbiEntry must have a type"));
        if (type == AbiEntryType.CONSTRUCTOR) {
            final List<AbiParameter> inputs = new ArrayList<>();
            getArray(abiEntryObject, "inputs").forEach(jsonElement -> {
                inputs.add(fromJson(jsonElement, AbiParameter.class));
            });
            final List<AbiParameter> outputs = new ArrayList<>();
            getArray(abiEntryObject, "outputs").forEach(jsonElement -> {
                outputs.add(fromJson(jsonElement, AbiParameter.class));
            });
            final StateMutability stateMutability = getStringValue(abiEntryObject, "stateMutability")
                    .map(StateMutability::of)
                    .orElseThrow(() -> new IllegalArgumentException("AbiEntry must have a stateMutability"));
            return new AbiFunction(type, null, Collections.unmodifiableList(inputs),
                    Collections.unmodifiableList(outputs), stateMutability);
        }
        if (type.isCompatibleWithFunction()) {
            final String name = getStringValue(abiEntryObject, "name")
                    .orElseThrow(() -> new IllegalArgumentException("AbiEntry must have a name"));
            final List<AbiParameter> inputs = new ArrayList<>();
            getArray(abiEntryObject, "inputs").forEach(jsonElement -> {
                inputs.add(fromJson(jsonElement, AbiParameter.class));
            });
            final List<AbiParameter> outputs = new ArrayList<>();
            getArray(abiEntryObject, "outputs").forEach(jsonElement -> {
                outputs.add(fromJson(jsonElement, AbiParameter.class));
            });
            final StateMutability stateMutability = getStringValue(abiEntryObject, "stateMutability")
                    .map(StateMutability::of)
                    .orElseThrow(() -> new IllegalArgumentException("AbiEntry must have a stateMutability"));
            return new AbiFunction(type, name, Collections.unmodifiableList(inputs),
                    Collections.unmodifiableList(outputs), stateMutability);
        }
        throw new IllegalArgumentException("Unsupported AbiEntry type: " + type);
    }
}
