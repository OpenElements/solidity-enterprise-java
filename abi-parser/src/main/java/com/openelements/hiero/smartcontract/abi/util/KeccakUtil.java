package com.openelements.hiero.smartcontract.abi.util;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.jspecify.annotations.NonNull;

public class KeccakUtil {

    public static byte[] keccak256(@NonNull final byte[] data) {
        final Keccak.DigestKeccak kecc = new Keccak.Digest256();
        kecc.update(data);
        return kecc.digest();
    }
}
