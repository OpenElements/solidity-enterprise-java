package com.openelements.hiero.smartcontract.abi.model;

import com.openelements.hiero.smartcontract.abi.util.HexConverter;
import com.openelements.hiero.smartcontract.abi.util.KeccakUtil;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public record AbiEvent(@NonNull String name, @NonNull List<AbiParameter> inputs, boolean anonymous) implements AbiEntry{

    public AbiEvent {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(inputs, "inputs");
    }

    @Override
    public AbiEntryType type() {
        return AbiEntryType.EVENT;
    }

    @NonNull
    public List<AbiParameter> getIndexedInputParameters() {
        return inputs.stream().filter(AbiParameter::indexed).toList();
    }

    @NonNull
    public List<AbiParameter> getNonIndexedInputParameters() {
        return inputs.stream().filter(parameter -> !parameter.indexed()).toList();
    }

    @NonNull
    private String createEventSignature() {
        final List<String> canonicalParameterTypes = inputs.stream().map(AbiParameter::getCanonicalType).toList();
        return name + "(" + String.join(",", canonicalParameterTypes) + ")";
    }

    @NonNull
    private byte[] createEventSignatureHash() {
        final String eventSignature = createEventSignature();
        return KeccakUtil.keccak256(eventSignature.getBytes(StandardCharsets.UTF_8));
    }

    @NonNull
    public String createEventSignatureHashAsHex() {
        final byte[] eventSignatureHash = createEventSignatureHash();
        return "0x" + HexConverter.bytesToHex(eventSignatureHash);
    }
}
