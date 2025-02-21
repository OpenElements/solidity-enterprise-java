package com.openelements.hiero.smartcontract.abi.model;

import java.util.List;
import java.util.Objects;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record AbiEvent(@NonNull String name, @NonNull List<AbiParameter> inputs, boolean anonymous) implements AbiEntry{

    public AbiEvent {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(inputs, "inputs");
    }

    @Override
    public AbiEntryType type() {
        return AbiEntryType.EVENT;
    }
}
