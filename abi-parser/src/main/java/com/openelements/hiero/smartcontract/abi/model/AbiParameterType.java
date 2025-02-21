package com.openelements.hiero.smartcontract.abi.model;

public enum AbiParameterType {
    ADDRESS,
    STRING,
    BYTE32,
    BOOL,
    UINT256,
    TUPLE;

    public static AbiParameterType of(final String name) {
        return switch (name) {
            case "address" -> ADDRESS;
            case "string" -> STRING;
            case "bytes32" -> BYTE32;
            case "bool" -> BOOL;
            case "uint256" -> UINT256;
            default -> throw new IllegalArgumentException("Unknown value type: " + name);
        };
    }

    public String getCanonicalType() {
        return switch (this) {
            case ADDRESS -> "address";
            case STRING -> "string";
            case BYTE32 -> "bytes32";
            case BOOL -> "bool";
            case UINT256 -> "uint256";
            case TUPLE -> "tuple";
        };
    }

    public boolean isDynamic() {
        return switch (this) {
            case STRING, BYTE32 -> true;
            default -> false;
        };
    }
}
