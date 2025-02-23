package com.openelements.hiero.smartcontract.abi.model;

import java.util.List;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public record AbiModel(@NonNull List<AbiEntry> entries) {

    public AbiModel {
        Objects.requireNonNull(entries, "entries");
    }

    @NonNull
    public List<AbiFunction> getFunctions() {
        return getEntriesOfType(AbiFunction.class);
    }

    @NonNull
    public List<AbiEvent> getEvents() {
        return getEntriesOfType(AbiEvent.class);
    }

    @NonNull
    public List<AbiError> getErrors() {
        return getEntriesOfType(AbiError.class);
    }

    @NonNull
    private <T extends AbiEntry> List<T> getEntriesOfType(@NonNull final Class<T> type) {
        Objects.requireNonNull(type, "type must not be null");
        return entries.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .toList();
    }
}
