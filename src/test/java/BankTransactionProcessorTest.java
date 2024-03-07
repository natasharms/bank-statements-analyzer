import org.example.BankTransactionProcessor;
import org.example.DataFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BankTransactionProcessorTest {

    private final DataFactory dataFactory = new DataFactory();
    private final BankTransactionProcessor transactionProcessor = new BankTransactionProcessor(dataFactory.mockCsvData());

    @Test
    public void shouldReturnTotalAmount(){

        double expected = 6000 + 2000 + 3000;
        double total = transactionProcessor.totalAmount();

        assertEquals(expected, total, 0.0);

    }

    @Test
    public void shouldReturnMonthlyTransaction(){

        var expected = 2;
        var actual = transactionProcessor.monthlyTransactions("January").size();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTotalInMonth(){

        var expected = -150;
        var actual = transactionProcessor.totalInMonth("January");

        assertEquals(expected, actual, 0.0);

    }
}
