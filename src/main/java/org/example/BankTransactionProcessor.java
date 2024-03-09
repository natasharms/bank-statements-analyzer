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
            if(bankTransactionDataFile.date().getMonth().name().equalsIgnoreCase(month)){
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
            if(bankTransactionDataFile.value()<0){
                stringList.add(bankTransactionDataFile.description());
            }
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

    public List<Map.Entry<String, Double>> categoriesWithMostExpenses(){
        List<String> expensesList = descriptionList();
        Map<String, Double> expensesMap = new LinkedHashMap<>();

        for (String category : expensesList) {
            expensesMap.put(category, totalForCategory(category));
        }

        List<Map.Entry<String, Double>> list = new ArrayList<>(expensesMap.entrySet());
        list.sort(Map.Entry.comparingByValue());

        return list;
    }

    public Map<String, Double> expensesByMonth(){
        Map<String, Double> byMonth = new HashMap<>();

        Set<String> monthsSet = new HashSet<>();
        for(BankTransactionData bankTransactionData : data){
            monthsSet.add(bankTransactionData.date().getMonth().name());
        }

        for(String month : monthsSet){
            double total = totalInMonth(month);
            byMonth.put(month, total);
        }

        return byMonth;
    }

    public Map<String, List<BankTransactionData>> listOfExpensesByMonth(){
        Set<String> monthsSet = new HashSet<>();
        Map<String, List<BankTransactionData>> byMonth = new HashMap<>();

        for(BankTransactionData bankTransactionData : data){
            monthsSet.add(bankTransactionData.date().getMonth().name());
        }

        for(String month : monthsSet){
            List<BankTransactionData> total = monthlyTransactions(month);
            byMonth.put(month, total);
        }

        return byMonth;
    }

}

