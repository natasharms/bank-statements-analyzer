import org.example.BankStatementCSVParser;
import org.example.BankStatementParser;
import org.example.BankTransactionData;
import org.example.DataFactory;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();
    private final DataFactory dataFactory = new DataFactory();


    @Test
    public void shouldParseFile() {

        final List<BankTransactionData> expected = dataFactory.mockCsvData();

        List<String> csvData = new ArrayList<>();
        csvData.add("30-01-2017,-100,Deliveroo");
        csvData.add("30-01-2017,-50,Tesco");
        csvData.add("01-02-2017,6000,Salary");
        csvData.add("02-02-2017,2000,Royalties");
        csvData.add("02-02-2017,-4000,Rent");
        csvData.add("03-02-2017,3000,Tesco");
        csvData.add("05-02-2017,-30,Cinema");
        final List<BankTransactionData> result = statementParser.parseLines(csvData);

        Assert.assertEquals(expected.get(0), result.get(0));
        Assert.assertEquals(expected.get(1), result.get(1));
        Assert.assertEquals(expected.get(2), result.get(2));
        Assert.assertEquals(expected.get(3), result.get(3));
        Assert.assertEquals(expected.get(4), result.get(4));
        Assert.assertEquals(expected.get(5), result.get(5));
        Assert.assertEquals(expected.get(6), result.get(6));
    }


}
