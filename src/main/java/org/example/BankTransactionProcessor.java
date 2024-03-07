package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class BankTransactionProcessor {

    private final List<BankTransactionData> data;

    public BankTransactionProcessor(List<BankTransactionData> data) {
        this.data = data;
    }

    public Double totalAmount() {

        double total = 0;

        for (final BankTransactionData bankTransactionDataFile : data){

            if(bankTransactionDataFile.value()>0){
                total += bankTransactionDataFile.value();
            }

        }

        return total;
    }

    public List<BankTransactionData> monthlyTransactions(final String month){

        List<BankTransactionData> monthTransactions = new ArrayList<>();

        for (BankTransactionData bankTransactionDataFile : data){
            String dateString = bankTransactionDataFile.date().getMonth().name();

            if(dateString.equalsIgnoreCase(month)){
                monthTransactions.add(new BankTransactionData(bankTransactionDataFile.date(), bankTransactionDataFile.value(), bankTransactionDataFile.description()));
            }
        }

        return monthTransactions;
    }

    public Double totalInMonth(final String month){

        double total = 0;
        List<BankTransactionData> monthTransactions = new ArrayList<>(monthlyTransactions(month));

        for (BankTransactionData value : monthTransactions) {
            total += value.value();
        }

        return total;
    }

    public List<String> descriptionList (){
        List<String> stringList = new ArrayList<>();

        for (BankTransactionData bankTransactionDataFile : data){
            stringList.add(bankTransactionDataFile.description());
        }

        return stringList;

    }

    public List<String> mostCommonExpenses(){
        List<String> expensesList = descriptionList();

        Map<String, Long> occurrencesMap = expensesList.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        List<String> sortedExpenses = occurrencesMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();

        return sortedExpenses.stream()
                .distinct()
                .limit(10)
                .toList();
    }

    public double totalForCategory(final String category){
        double total = 0;

        for(final BankTransactionData bankTransactionData : data){
            if(bankTransactionData.description().equalsIgnoreCase(category)){
                total += bankTransactionData.value();
            }
        }

        return total;
    }

}

