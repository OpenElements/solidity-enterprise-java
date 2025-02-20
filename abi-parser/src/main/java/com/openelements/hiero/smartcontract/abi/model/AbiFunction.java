package com.openelements.hiero.smartcontract.abi.model;

import java.util.List;
import java.util.Objects;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record AbiFunction(@NonNull AbiEntryType type, @Nullable String name, @NonNull List<AbiParameter> inputs,
                          @NonNull List<AbiParameter> outputs,
                          @NonNull StateMutability stateMutability) implements AbiEntry {

    public AbiFunction {
        Objects.requireNonNull(type, "type");
        if (!type.isCompatibleWithFunction()) {
            throw new IllegalArgumentException("type must be compatible for a function");
        }
        if (type == AbiEntryType.FUNCTION) {
            if (name == null) {
                throw new IllegalArgumentException("name must be provided for a function");
            }
        }
        if (type == AbiEntryType.CONSTRUCTOR || type == AbiEntryType.RECEIVE || type == AbiEntryType.FALLBACK) {
            if (name != null) {
                throw new IllegalArgumentException("name must not be provided for a type '" + type + "'");
            }
            if (!outputs.isEmpty()) {
                throw new IllegalArgumentException("output must be empty for type " + type + "'");
            }
        }
        if (type == AbiEntryType.RECEIVE || type == AbiEntryType.FALLBACK) {
            if (!inputs.isEmpty()) {
                throw new IllegalArgumentException("inouts must be empty for type " + type + "'");
            }
        }

        Objects.requireNonNull(inputs, "inputs");
        Objects.requireNonNull(outputs, "outputs");
        Objects.requireNonNull(stateMutability, "stateMutability");

    }
}
