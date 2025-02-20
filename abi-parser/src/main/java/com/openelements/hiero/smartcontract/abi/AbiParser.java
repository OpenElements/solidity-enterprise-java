package com.openelements.hiero.smartcontract.abi;

import com.openelements.hiero.smartcontract.abi.model.AbiModel;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public interface AbiParser {

    default @NonNull AbiModel parse(@NonNull URL url) throws AbiParserException {
        Objects.requireNonNull(url, "url");
        try {
            return parse(Path.of(url.toURI()));
        } catch (URISyntaxException e) {
            throw new AbiParserException("Error in converting url to uri: '" + url + "'", e);
        }
    }

    default @NonNull AbiModel parse(@NonNull Path abiPath) throws AbiParserException {
        Objects.requireNonNull(abiPath, "abiPath");
        try {
            return parse(Files.readString(abiPath));
        } catch (IOException e) {
            throw new AbiParserException("Error in reading abi data from file '" + abiPath + "'", e);
        }
    }


    default @NonNull AbiModel parse(@NonNull String abi) throws AbiParserException {
        Objects.requireNonNull(abi, "abi");
        return parse(new StringReader(abi));
    }

    @NonNull
    AbiModel parse(@NonNull Reader abiReader) throws AbiParserException;
}
