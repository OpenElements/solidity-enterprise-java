package com.openelements.hiero.smartcontract.abi.model;

import java.util.List;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public record AbiModel(@NonNull List<AbiEntry> entries) {

    public AbiModel {
        Objects.requireNonNull(entries, "entries");
    }
}
