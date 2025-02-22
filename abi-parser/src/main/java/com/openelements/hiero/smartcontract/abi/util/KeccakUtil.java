package com.openelements.hiero.smartcontract.abi.util;

import org.bouncycastle.jcajce.provider.digest.Keccak;

public class KeccakUtil {

    public static byte[] keccak256(final byte[] data) {
        Keccak.DigestKeccak kecc = new Keccak.Digest256();
        kecc.update(data);
        return kecc.digest();
    }
}
