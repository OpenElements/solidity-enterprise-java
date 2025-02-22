package com.openelements.hiero.smartcontract.abi.test;

import com.openelements.hiero.smartcontract.abi.model.AbiEvent;
import com.openelements.hiero.smartcontract.abi.model.AbiParameter;
import com.openelements.hiero.smartcontract.abi.model.AbiParameterType;
import com.openelements.hiero.smartcontract.abi.util.HexConverter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AbiEventTests {

    @Test
    void testEventSignatureCreation() {
        // Given
        final List<AbiParameter> inputs = List.of(
                new AbiParameter("identifier_", AbiParameterType.STRING, List.of(), false)
        );
        final AbiEvent event = new AbiEvent("HashAdded", inputs, false);


        // When
        String signature = event.createEventSignature();
        String hashAsHex = event.createEventSignatureHashAsHex();

        // Then
        Assertions.assertEquals("HashAdded(string)", signature);
        Assertions.assertEquals("0xe0c9f5e6f5abddac86dac0e02afc9f3fda7b7fc6d9454a13c51fcb28621e1e5f", hashAsHex);
    }

    @Test
    void testEventSignatureCreation2() {
        // Given
        final List<AbiParameter> inputs = List.of(
                new AbiParameter("count", AbiParameterType.UINT, List.of(), false)
        );
        final AbiEvent event = new AbiEvent("MissingVerificationCountUpdated", inputs, false);


        // When
        String signature = event.createEventSignature();
        String hashAsHex = event.createEventSignatureHashAsHex();

        // Then
        Assertions.assertEquals("MissingVerificationCountUpdated(uint256)", signature);
        Assertions.assertEquals("0x271219bdbb9b91472a5df68ef7a9d3f8de02f3c27b93a35306f888acf081ea60", hashAsHex);
    }

}
