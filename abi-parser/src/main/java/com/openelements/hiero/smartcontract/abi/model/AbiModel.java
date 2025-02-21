package com.openelements.hiero.smartcontract.abi.model;

import java.util.List;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public record AbiModel(@NonNull List<AbiEntry> entries) {

    public AbiModel {
        Objects.requireNonNull(entries, "entries");
    }

    public List<AbiFunction> getFunctions() {
        return getEntriesOfType(AbiFunction.class);
    }

    public List<AbiEvent> getEvents() {
        return getEntriesOfType(AbiEvent.class);
    }

    public List<AbiError> getErrors() {
        return getEntriesOfType(AbiError.class);
    }

    private <T extends AbiEntry> List<T> getEntriesOfType(Class<T> type) {
        return entries.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .toList();
    }
}
