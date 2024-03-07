package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataFactory {

    public List<BankTransactionData> mockCsvData(){
        List<BankTransactionData> csvData = new ArrayList<>();
        csvData.add(new BankTransactionData(LocalDate.of(2017, 1, 30), -100, "Deliveroo"));
        csvData.add(new BankTransactionData(LocalDate.of(2017, 1, 30), -50, "Tesco"));
        csvData.add(new BankTransactionData(LocalDate.of(2017, 2, 1), 6000, "Salary"));
        csvData.add(new BankTransactionData(LocalDate.of(2017, 2, 2), 2000, "Royalties"));
        csvData.add(new BankTransactionData(LocalDate.of(2017, 2, 2), -4000, "Rent"));
        csvData.add(new BankTransactionData(LocalDate.of(2017, 2, 3), 3000, "Tesco"));
        csvData.add(new BankTransactionData(LocalDate.of(2017, 2, 5), -30, "Cinema"));

        return csvData;
    }

}
