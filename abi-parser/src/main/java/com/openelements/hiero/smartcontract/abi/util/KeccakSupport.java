package com.openelements.hiero.smartcontract.abi.util;

import com.github.aelstad.keccakj.provider.Constants;
import com.github.aelstad.keccakj.provider.KeccakjProvider;
import java.security.MessageDigest;
import java.security.Security;

public class KeccakSupport {

    public synchronized static MessageDigest getMessageDigest() {
        if(Security.getProvider(Constants.PROVIDER) == null) {
            Security.addProvider(new KeccakjProvider());
        }
        try {
            return MessageDigest.getInstance(Constants.SHA3_256, Constants.PROVIDER);
        } catch (Exception e) {
            throw new RuntimeException("Error in Keccak provisioning", e);
        }
    }

    public static byte[] keccak256(byte[] data) {
        MessageDigest md = getMessageDigest();
        return md.digest(data);
    }
}
