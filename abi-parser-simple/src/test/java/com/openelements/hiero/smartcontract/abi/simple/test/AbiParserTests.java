package com.openelements.hiero.smartcontract.abi.simple.test;

import com.openelements.hiero.smartcontract.abi.AbiParser;
import com.openelements.hiero.smartcontract.abi.model.AbiModel;
import com.openelements.hiero.smartcontract.abi.simple.GsonAbiParser;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AbiParserTests {

    @Test
    protected void test() throws Exception {
        //given
        final AbiParser parser = new GsonAbiParser();
        final URL abiFile = AbiParserTests.class.getClassLoader().getResource("test.json");

        //when
        final AbiModel model = parser.parse(abiFile);

        //then
        Assertions.assertNotNull(model);
    }
}
