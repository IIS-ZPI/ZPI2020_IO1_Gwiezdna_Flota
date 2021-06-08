package zpiprojekt;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import zpiprojekt.nbp.data.RateTable;
import zpiprojekt.nbp.data.RatesStatistics;
import zpiprojekt.nbp.url.URLCreator;

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



    @Test
    public void testingRateTableStatistics(){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = LocalDateTime.now().minusMonths(1);
        String url = new URLCreator().setCurrency(Currency.getInstance("USD")).setDateFrom(weekAgo).setDateTo(now).create();
        try{
            RateTable table = NBPConnector.readJsonTable(url);
            double median = RatesStatistics.getMedian(table);
            System.out.println("median: "+median);
            List<Double> dominant = RatesStatistics.getDominant(table);
            System.out.println("dominant: "+dominant);
            System.out.println("std: "+RatesStatistics.getStandardDeviation(table));
            System.out.println("CoefficientOfVariation: "+RatesStatistics.getCoefficientOfVariation(table));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
