import org.example.BankTransactionData;
import org.example.BankTransactionProcessor;
import org.example.DataFactory;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class BankTransactionProcessorTest {

    private final DataFactory dataFactory = new DataFactory();
    private final BankTransactionProcessor transactionProcessor = new BankTransactionProcessor(dataFactory.mockCsvData());

    @Test
    public void shouldReturnTotalAmount() {
        double expected = 6000 + 2000 + 3000;
        double total = transactionProcessor.totalAmount();
        assertEquals(expected, total, 0.0);
    }

    @Test
    public void shouldReturnMonthlyTransaction() {
        int expected = 2;
        int actual = transactionProcessor.monthlyTransactions("January").size();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTotalInMonth() {
        double expected = -150;
        double actual = transactionProcessor.totalInMonth("January");
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void shouldReturnTotalForCategory() {
        double expected = 2950;
        double actual = transactionProcessor.totalForCategory("Tesco");
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void shouldReturnMostCommonExpenses() {
        List<String> expected = Arrays.asList("Tesco", "Deliveroo", "Cinema", "Rent");
        List<String> actual = transactionProcessor.mostCommonExpenses();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCategoriesWithMostExpenses() {
        List<Map.Entry<String, Double>> expected = Arrays.asList(
                new AbstractMap.SimpleEntry<>("Rent", -4000.0),
                new AbstractMap.SimpleEntry<>("Deliveroo", -100.0),
                new AbstractMap.SimpleEntry<>("Cinema", -30.0),
                new AbstractMap.SimpleEntry<>("Tesco", 2950.0)
        );
        List<Map.Entry<String, Double>> actual = transactionProcessor.categoriesWithMostExpenses();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTotalAmountOfExpensesEachMonth() {
        Map<String, Double> expected = new HashMap<>();
        expected.put("JANUARY", -150.0);
        expected.put("FEBRUARY", 6970.0);
        Map<String, Double> actual = transactionProcessor.expensesByMonth();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnListOfExpensesEachMonth() {
        Map<String, List<BankTransactionData>> expected = new HashMap<>();

        List<BankTransactionData> januaryTransactions = Arrays.asList(
                new BankTransactionData(LocalDate.of(2017, 1, 30), -100.0, "Deliveroo"),
                new BankTransactionData(LocalDate.of(2017, 1, 30), -50.0, "Tesco")
        );
        expected.put("JANUARY", januaryTransactions);

        List<BankTransactionData> februaryTransactions = Arrays.asList(
                new BankTransactionData(LocalDate.of(2017, 2, 1), 6000.0, "Salary"),
                new BankTransactionData(LocalDate.of(2017, 2, 2), 2000.0, "Royalties"),
                new BankTransactionData(LocalDate.of(2017, 2, 2), -4000.0, "Rent"),
                new BankTransactionData(LocalDate.of(2017, 2, 3), 3000.0, "Tesco"),
                new BankTransactionData(LocalDate.of(2017, 2, 5), -30.0, "Cinema")
        );
        expected.put("FEBRUARY", februaryTransactions);

        Map<String, List<BankTransactionData>> actual = transactionProcessor.listOfExpensesByMonth();

        assertEquals(expected, actual);
    }
}
