package com.openelements.hiero.smartcontract.abi.model;

import java.util.List;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public record AbiParameter(@NonNull String name, @NonNull AbiParameterType type, @NonNull List<AbiParameter> components,
                           boolean indexed) {
    
    public AbiParameter {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(components, "components");

        if (type != AbiParameterType.TUPLE && !components.isEmpty()) {
            throw new IllegalStateException(
                    "No component structure should be defined for parameter type '" + type + "'");
        }
    }

    public String getCanonicalType() {
        return type.getCanonicalType();
    }

}
