import org.example.BankTransactionProcessor;
import org.example.DataFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BankTransactionProcessorTest {

    private final DataFactory dataFactory = new DataFactory();

    @Test
    public void shouldReturnTotalAmount(){

        BankTransactionProcessor transactionProcessor = new BankTransactionProcessor(dataFactory.mockCsvData());
        double expected = 6000 + 2000 + 3000;
        double total = transactionProcessor.totalAmount();

        assertEquals(expected, total, 0.0);

    }
}
