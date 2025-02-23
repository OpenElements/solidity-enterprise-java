package com.openelements.hiero.smartcontract.abi.test;

import com.openelements.hiero.smartcontract.abi.util.HexConverter;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HexConverterTests {

    @ParameterizedTest
    @MethodSource("provideArgumentsForHexToBytesTest")
    void hexToBytesTest(String hexInput, byte[] expectedBytes) {
        //given
        final String hex = "00";

        //when
        final byte[] bytes = HexConverter.hexToBytes(hex);

        //then
        Assertions.assertArrayEquals(new byte[]{0x0}, bytes);
    }

    private static Stream<Arguments> provideArgumentsForHexToBytesTest() {
        return Stream.of(
                Arguments.of("00", new byte[]{0x0}),
                Arguments.of("01", new byte[]{0x1})
        );
    }
}
