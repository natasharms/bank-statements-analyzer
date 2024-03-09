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
        System.out.println("\nMonthly Total: " + bankTransactionProcessor.totalInMonth("January"));
        System.out.println("\nMost Common Expenses: " + bankTransactionProcessor.mostCommonExpenses());
        System.out.println("\nAll transactions in January: " + bankTransactionProcessor.monthlyTransactions("January"));
        System.out.println("\nTotal for Tesco: " + bankTransactionProcessor.totalForCategory("Tesco"));
        System.out.println("\nCategories with more expenses: " + bankTransactionProcessor.categoriesWithMostExpenses());
        System.out.println("\nExpenses by month = " + bankTransactionProcessor.expensesByMonth());
        System.out.println("\nList of expenses by month = " + bankTransactionProcessor.listOfExpensesByMonth());

    }


}