package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class BankStatementCSVParser implements BankStatementParser {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String CSV_DELIMITER = ",";

    @Override
    public List<BankTransactionData> parseFrom(String url) throws IOException {
        final Path path = Paths.get(url);
        List<String> lines = readAllLines(path);
        return parseLines(lines);
    }

    @Override
    public List<BankTransactionData> parseLines(List<String> lines) {
        final List<BankTransactionData> bankTransactionDataList = new ArrayList<>();

        for (final String line : lines) {
            String[] lineData = line.split(CSV_DELIMITER);

            LocalDate date = LocalDate.parse(lineData[0], DATE_FORMATTER);
            double value = Double.parseDouble(lineData[1]);
            String description = lineData[2];

            BankTransactionData bankTransactionData = new BankTransactionData(date, value, description);
            bankTransactionDataList.add(bankTransactionData);
        }

        return bankTransactionDataList;
    }
}

