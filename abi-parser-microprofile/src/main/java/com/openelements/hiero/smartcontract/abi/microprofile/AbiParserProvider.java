package com.openelements.hiero.smartcontract.abi.microprofile;

import com.openelements.hiero.smartcontract.abi.AbiParser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.jspecify.annotations.NonNull;

@ApplicationScoped
public class AbiParserProvider {

    @NonNull
    @Produces
    @ApplicationScoped
    AbiParser createAbiParser() {
        return new MicroprofileAbiParser();
    }
}
