package org.example;

import java.io.IOException;

public class BankStatementAnalyzer {

    public void analyze(final BankStatementParser bankStatementParser) throws IOException {

        final String RESOURCES = "src/main/resources/bank-data-simple.csv";
        final BankTransactionProcessor bankTransactionProcessor = new BankTransactionProcessor(bankStatementParser.parseFrom(RESOURCES));
        printSummary(bankTransactionProcessor);

    }

    private static void printSummary(final BankTransactionProcessor bankTransactionProcessor){
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