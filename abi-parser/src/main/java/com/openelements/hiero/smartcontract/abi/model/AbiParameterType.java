package com.openelements.hiero.smartcontract.abi.model;

public enum AbiParameterType {
    ADDRESS,
    STRING,
    BYTE32,
    BOOL,
    UINT256,
    UINT,
    TUPLE;

    public static AbiParameterType of(final String name) {
        return switch (name) {
            case "address" -> ADDRESS;
            case "string" -> STRING;
            case "bytes32" -> BYTE32;
            case "bool" -> BOOL;
            case "uint256" -> UINT256;
            case "uint" -> UINT;
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
            case UINT -> "uint256";
        };
    }

    public boolean isDynamic() {
        return switch (this) {
            case STRING, BYTE32 -> true;
            default -> false;
        };
    }

    public int getFixedSize() {
        return switch (this) {
            case ADDRESS -> 20;
            case BOOL -> 1;
            case UINT256 -> 32;
            case UINT -> 32;
            case BYTE32 -> 32;
            default -> throw new IllegalStateException("No fixed size for type: " + this);
        };
    }
}
