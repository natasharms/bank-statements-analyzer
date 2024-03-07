package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record BankTransactionData(LocalDate date, double value, String description) {

    @Override
    public String toString() {
        final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = formatDate.format(date);

        return "Bank Transactions: {" +
                "date = " + formattedDate +
                ", value = " + value +
                ", description = " + description +
                '}';
    }
}
