package com.openelements.hiero.smartcontract.abi.model;

import java.util.List;

public sealed interface AbiEntry permits AbiEvent, AbiError, AbiFunction {

    AbiEntryType type();

    String name();

    List<AbiParameter> inputs();
}
