package com.openelements.hiero.smartcontract.abi.model;

import java.util.stream.Stream;

public enum AbiEntryType {
    FUNCTION,
    EVENT,
    CONSTRUCTOR,
    RECEIVE,
    FALLBACK;

    public static AbiEntryType of(String name) {
        return switch (name) {
            case "function" -> FUNCTION;
            case "event" -> EVENT;
            case "constructor" -> CONSTRUCTOR;
            case "receive" -> RECEIVE;
            case "fallback" -> FALLBACK;
            default -> throw new IllegalArgumentException("Unknown ABI entry type: " + name);
        };
    }

    public boolean isCompatibleWithFunction() {
        return Stream.of(FUNCTION, CONSTRUCTOR, RECEIVE, FALLBACK)
                .anyMatch(type -> this == type);
    }
}
