package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class BankTransactionProcessor {

    private final List<BankTransactionData> data;

    public BankTransactionProcessor(List<BankTransactionData> data) {
        this.data = data;
    }

    public Double totalAmount() {
        return data.stream()
                .filter(bankTransactionData -> bankTransactionData.value() > 0)
                .mapToDouble(BankTransactionData::value)
                .sum();
    }

    public List<BankTransactionData> monthlyTransactions(String month) {
        return data.stream()
                .filter(bankTransactionData -> bankTransactionData.date().getMonth().name().equalsIgnoreCase(month))
                .collect(Collectors.toList());
    }

    public double totalInMonth(String month) {
        return monthlyTransactions(month).stream()
                .mapToDouble(BankTransactionData::value)
                .sum();
    }

    public List<String> descriptionList (){
        return data.stream()
                .filter(bankTransactionData -> bankTransactionData.value() < 0)
                .map(BankTransactionData::description)
                .collect(Collectors.toList());
    }

    public List<String> mostCommonExpenses(){
        return descriptionList().stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .distinct()
                .limit(10)
                .collect(Collectors.toList());
    }

    public double totalForCategory(final String category){
        return data.stream()
                .filter(bankTransactionData -> bankTransactionData.description().equalsIgnoreCase(category))
                .mapToDouble(BankTransactionData::value)
                .sum();
    }

    public List<Map.Entry<String, Double>> categoriesWithMostExpenses(){
        return descriptionList().stream()
                .collect(Collectors.toMap(
                        category -> category,
                        this::totalForCategory,
                        Double::sum,
                        LinkedHashMap::new))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
    }

    public Map<String, Double> expensesByMonth(){
        return data.stream()
                .collect(Collectors.groupingBy(
                        bankTransactionData -> bankTransactionData.date().getMonth().name(),
                        Collectors.summingDouble(BankTransactionData::value)));
    }

    public Map<String, List<BankTransactionData>> listOfExpensesByMonth(){
        return data.stream()
                .collect(Collectors.groupingBy(
                        bankTransactionData -> bankTransactionData.date().getMonth().name(),
                        LinkedHashMap::new,
                        Collectors.toList()));
    }
}

