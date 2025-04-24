package com.openelements.hiero.smartcontract.abi.simple;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.openelements.hiero.smartcontract.abi.AbiParser;
import com.openelements.hiero.smartcontract.abi.AbiParserException;
import com.openelements.hiero.smartcontract.abi.model.AbiModel;
import com.openelements.hiero.smartcontract.abi.simple.implementation.AbiTypeAdapterFactory;
import java.io.Reader;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

/**
 * A parser for ABI (Application Binary Interface) data using {@link Gson}.
 * Basic implementation of {@link AbiParser} interface.
 */
public class GsonAbiParser implements AbiParser {

    private final Gson gson;

    /**
     * Constructs a new {@code GsonAbiParser} instance.
     */
    public GsonAbiParser() {
        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(new AbiTypeAdapterFactory())
                .create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NonNull AbiModel parse(@NonNull final Reader abiReader) throws AbiParserException {
        Objects.requireNonNull(abiReader, "abiReader");
        try {
            return gson.fromJson(abiReader, AbiModel.class);
        } catch (Exception e) {
            throw new AbiParserException("Error in parsing abi", e);
        }
    }
}
