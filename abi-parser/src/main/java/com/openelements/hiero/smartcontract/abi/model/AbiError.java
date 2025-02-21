package com.openelements.hiero.smartcontract.abi.model;

import java.util.List;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public record AbiError(@NonNull String name, @NonNull List<AbiParameter> inputs) implements AbiEntry {

    public AbiError {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(inputs, "inputs");
    }

    @Override
    public AbiEntryType type() {
        return AbiEntryType.ERROR;
    }
}
