package org.example;

import java.io.IOException;
import java.util.List;

public class BankStatementAnalyzer {
    public void analyze(final BankStatementParser bankStatementParser) throws IOException {

        final String RESOURCES = "src/main/resources/bank-data-simple.csv";
        final List<BankTransactionData> data = bankStatementParser.parseFrom(RESOURCES);
        final BankTransactionProcessor bankTransactionProcessor = new BankTransactionProcessor(data);
        summary(bankTransactionProcessor);

    }

    private static void summary(final BankTransactionProcessor bankTransactionProcessor){
        System.out.println("Total earned: " + bankTransactionProcessor.totalAmount());
        System.out.println("Monthly Total: " + bankTransactionProcessor.totalInMonth("January"));
        System.out.println("Most Common Expenses: " + bankTransactionProcessor.mostCommonExpenses());
        System.out.println("All transactions in January: " + bankTransactionProcessor.monthlyTransactions("January"));
        System.out.println("Total by category: " + bankTransactionProcessor.totalForCategory("Tesco"));
    }


}