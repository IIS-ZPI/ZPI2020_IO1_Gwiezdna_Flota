package zpiprojekt;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Scanner;

import org.junit.Test;
import zpiprojekt.nbp.ActionSessions;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    private int testingUserInput(String inputText){
        InputStream in = new ByteArrayInputStream(inputText.getBytes());

        // Make backup of System.in
        InputStream systemInBackup = System.in;

        System.setIn(in);
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        sc.close();

        // Restore original System.in
        System.setIn(systemInBackup);

        return inputText.compareTo(nextLine);
    }

    @Test
    public void testingUserInputText(){
        String[] words = {"ads", "dabv", "12332t", "dfvhheeyut", "zxvvbcmn"};

        for(int i = 0; i < words.length; ++i)
            assertTrue(testingUserInput(words[i]) == 0);
    }
    @Test
    public void testingActionSessions() throws IOException {
        LocalDateTime weekAgo = LocalDateTime.now().minusWeeks(1);
        LocalDateTime dayAgo = LocalDateTime.now().minusDays(1);
        ActionSessions sessions = new ActionSessions(Currency.getInstance("USD"),weekAgo);
        System.out.println("Growth sessions: " + sessions.getGrowthSessions());
        System.out.println("Decreasing sessions: " + sessions.getDecreaseSessions());
        System.out.println("Unchanged sessions: " + sessions.getUnchangedSessions());
    }



    private InputStream writeStringAsSystemIn(String toWrite){
        // Create new InputStream that write string
        InputStream in = new ByteArrayInputStream(toWrite.getBytes());
        return in;
    }   

    /**
    * * * * * * * * * * * * * * * * * * * * * * * * *
    * UIClient.actionDistributionChanges
    * * * * * * * * * * * * * * * * * * * * * * * * *
    */
    @Test 
    public void testUIClientActionDistributionChanges001(){
        String toWrite = "3413t2af";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 1);
    }

    @Test 
    public void testUIClientActionDistributionChanges002(){
        String toWrite = "EUR\nUSD\n1";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 0);
    }

    @Test 
    public void testUIClientActionDistributionChanges003(){
        String toWrite = "PLN";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 1);
    }

    @Test 
    public void testUIClientActionDistributionChanges004(){
        String toWrite = "EUR\nPLN";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 1);
    }

    @Test 
    public void testUIClientActionDistributionChanges005(){
        String toWrite = "EUR\nEUR";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 1);
    }

    @Test 
    public void testUIClientActionDistributionChanges006(){
        String toWrite = "EUR\nPLN";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 1);
    }

    @Test 
    public void testUIClientActionDistributionChanges007(){
        String toWrite = "EUR\nUSD\n0";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 3);
    }

    @Test 
    public void testUIClientActionDistributionChanges008(){
        String toWrite = "EUR\nUSD\n435346374";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 2);
    }


    /**
    * * * * * * * * * * * * * * * * * * * * * * * * *
    * UIClient.actionStatisticalMeasures
    * * * * * * * * * * * * * * * * * * * * * * * * *
    */
    @Test 
    public void testUIClientActionStatisticalMeasures001(){
        String toWrite = "PLN";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionStatisticalMeasures();

        assertTrue(retVal == 1);
    }

    @Test 
    public void testUIClientActionStatisticalMeasures002(){
        String toWrite = "USD\n1";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionStatisticalMeasures();

        assertTrue(retVal == 0);
    }

    @Test 
    public void testUIClientActionStatisticalMeasures003(){
        String toWrite = "USD\n-1";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionStatisticalMeasures();

        assertTrue(retVal == 2);
    }
    @Test 
    public void testUIClientActionStatisticalMeasures004(){
        String toWrite = "USD\n5";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionStatisticalMeasures();

        assertTrue(retVal == 0);
    }

    @Test 
    public void testUIClientActionStatisticalMeasures005(){
        String toWrite = "USD\n13456";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionStatisticalMeasures();

        assertTrue(retVal == 3);
    }

    @Test 
    public void testUIClientActionStatisticalMeasures006(){
        String toWrite = "";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionStatisticalMeasures();

        assertTrue(retVal == 1);
    }


    /**
    * * * * * * * * * * * * * * * * * * * * * * * * *
    * UIClient.actionSessions
    * * * * * * * * * * * * * * * * * * * * * * * * *
    */
    @Test 
    public void testUIClientActionSessions001(){
        String toWrite = "PLN";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionSessions();

        assertTrue(retVal == 1);
    }

    @Test 
    public void testUIClientActionSessions002(){
        String toWrite = "USD\n1";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionSessions();

        assertTrue(retVal == 0);
    }

    @Test 
    public void testUIClientActionSessions003(){
        String toWrite = "USD\n-1";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionSessions();

        assertTrue(retVal == 3);
    }
    @Test 
    public void testUIClientActionSessions004(){
        String toWrite = "USD\n5";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionSessions();

        assertTrue(retVal == 0);
    }

    @Test 
    public void testUIClientActionSessions005(){
        String toWrite = "USD\n13456";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionSessions();

        assertTrue(retVal == 3);
    }

    @Test 
    public void testUIClientActionSessions006(){
        String toWrite = "";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionSessions();

        assertTrue(retVal == 1);
    }
}
