package com.openelements.hiero.smartcontract.abi.model;

import com.openelements.hiero.smartcontract.abi.util.HexConverter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.jcajce.provider.digest.Keccak;
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
        final List<String> canonicalParameterTypes = inputs.stream().map(AbiParameter::getCanonicalType).toList();
        return name + "(" + String.join(",", canonicalParameterTypes) + ")";
    }

    @NonNull
    public byte[] createEventSignatureHash() {
        final String eventSignature = createEventSignature();
        Keccak.DigestKeccak kecc = new Keccak.Digest256();
        kecc.update(eventSignature.getBytes(StandardCharsets.UTF_8));
        return kecc.digest();
    }

    @NonNull
    public String createEventSignatureHashAsHex() {
        final byte[] eventSignatureHash = createEventSignatureHash();
        return "0x" + HexConverter.bytesToHex(eventSignatureHash);
    }
}
