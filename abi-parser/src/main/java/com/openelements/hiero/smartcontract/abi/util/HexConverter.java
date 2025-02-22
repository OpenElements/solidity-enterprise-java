package com.openelements.hiero.smartcontract.abi.util;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public class HexConverter {

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    @NonNull
    public static String bytesToHex(@NonNull final byte[] bytes) {
        //see https://stackoverflow.com/questions/9655181/java-convert-a-byte-array-to-a-hex-string
        Objects.requireNonNull(bytes, "bytes");
        final byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8).toLowerCase();
    }
}
