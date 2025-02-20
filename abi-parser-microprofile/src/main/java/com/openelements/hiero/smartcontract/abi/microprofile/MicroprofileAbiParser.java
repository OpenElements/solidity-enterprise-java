package com.openelements.hiero.smartcontract.abi.microprofile;

import com.openelements.hiero.smartcontract.abi.AbiParser;
import com.openelements.hiero.smartcontract.abi.model.AbiEntry;
import com.openelements.hiero.smartcontract.abi.model.AbiEntryType;
import com.openelements.hiero.smartcontract.abi.model.AbiFunction;
import com.openelements.hiero.smartcontract.abi.model.AbiModel;
import com.openelements.hiero.smartcontract.abi.model.AbiParameter;
import com.openelements.hiero.smartcontract.abi.model.StateMutability;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue.ValueType;
import jakarta.json.stream.JsonParser;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.jspecify.annotations.NonNull;

public class MicroprofileAbiParser implements AbiParser {

    @Override
    public @NonNull AbiModel parse(@NonNull Reader abiReader) {
        final JsonParser parser = Json.createParser(abiReader);
        final JsonArray array = parser.getArray();
        final List<AbiEntry> entries = array.stream()
                .map(value -> {
                    if (value.getValueType() != ValueType.OBJECT) {
                        throw new IllegalArgumentException("Must be an JSON object");
                    }
                    return value.asJsonObject();
                }).map(jsonObject -> convertToAbiEntry(jsonObject))
                .toList();
        return new AbiModel(entries);
    }

    @NonNull
    private AbiEntry convertToAbiEntry(@NonNull JsonObject jsonObject) {
        final AbiEntryType type = AbiEntryType.of(jsonObject.getString("type"));
        if (type == AbiEntryType.CONSTRUCTOR) {
            final List<AbiParameter> input = new ArrayList<>();
            if (jsonObject.containsKey("input")) {
                jsonObject.getJsonArray("input").stream()
                        .map(jsonValue -> jsonValue.asJsonObject())
                        .map(value -> convertToAbiParameter(value))
                        .forEach(param -> input.add(param));
            }
            final StateMutability stateMutability = StateMutability.of(jsonObject.getString("stateMutability"));
            return new AbiFunction(type, null, input, List.of(), stateMutability);
        }
        throw new RuntimeException("Not yet implemented");
    }

    @NonNull
    private AbiParameter convertToAbiParameter(@NonNull JsonObject jsonObject) {
        return new AbiParameter(null, null, null, false);
    }
}
