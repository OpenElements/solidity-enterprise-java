package com.openelements.hiero.smartcontract.abi.model;

import com.openelements.hiero.smartcontract.abi.util.HexConverter;
import com.openelements.hiero.smartcontract.abi.util.KeccakSupport;
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
    public String createEventSignature() {
        if(!anonymous) {
            throw new IllegalStateException("Cannot create anonymous topic for named event");
        }
        final List<String> canonicalParameterTypes = inputs.stream().map(AbiParameter::getCanonicalType).toList();
        return name + "(" + String.join(",", canonicalParameterTypes) + ")";
    }

    @NonNull
    public byte[] createEventSignatureHash() {
        final String eventSignature = createEventSignature();
        return KeccakSupport.keccak256(eventSignature.getBytes(StandardCharsets.UTF_8));
    }

    @NonNull
    public String createEventSignatureHashAsHex() {
        final byte[] eventSignatureHash = createEventSignatureHash();
        return HexConverter.bytesToHex(eventSignatureHash);
    }
}
