package zpiprojekt;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import java.util.*;

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
        try{


            RatesStatistics ratesStatistics = new RatesStatistics(Currency.getInstance("USD"),2);
            double median = ratesStatistics.getMedian();
            System.out.println("median: "+median);
            List<Double> dominant = ratesStatistics.getDominant();
            System.out.println("dominant: "+dominant);
            System.out.println("std: "+ratesStatistics.getStandardDeviation());
            System.out.println("CoefficientOfVariation: "+ratesStatistics.getCoefficientOfVariation());

            System.out.println("\n\n");
            for (Map.Entry<String, String> entry : ratesStatistics.getAllStatistics().entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
