package com.openelements.hiero.smartcontract.abi.model;

public enum StateMutability {
    PURE,
    VIEW,
    PAYABLE,
    NON_PAYABLE;

    public static StateMutability of(final String name) {
        return switch (name) {
            case "pure" -> PURE;
            case "view" -> VIEW;
            case "payable" -> PAYABLE;
            case "nonpayable" -> NON_PAYABLE;
            default -> throw new IllegalArgumentException("Unknown state mutability: " + name);
        };
    }
}
