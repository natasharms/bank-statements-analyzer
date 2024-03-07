package org.example;

import java.io.IOException;
import java.util.List;

public interface BankStatementParser {

    List<BankTransactionData> parseFrom(String url) throws IOException;

    List<BankTransactionData> parseLines(List<String> lines);

}

