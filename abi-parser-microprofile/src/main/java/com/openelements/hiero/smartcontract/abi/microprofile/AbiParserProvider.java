package com.openelements.hiero.smartcontract.abi.microprofile;

import com.openelements.hiero.smartcontract.abi.AbiParser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.jspecify.annotations.NonNull;

/**
 * CDI provider for the {@link AbiParser} implementation using MicroProfile JSON-P.
 */
@ApplicationScoped
public class AbiParserProvider {

    @NonNull
    @Produces
    @ApplicationScoped
    AbiParser createAbiParser() {
        return new MicroprofileAbiParser();
    }
}
