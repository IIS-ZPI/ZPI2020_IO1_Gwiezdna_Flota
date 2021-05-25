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
import zpiprojekt.nbp.url.URLCreator;

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
    public void testingRateTableStatistics(){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = LocalDateTime.now().minusMonths(1);
        String url = new URLCreator().setCurrency(Currency.getInstance("USD")).setDateFrom(weekAgo).setDateTo(now).create();
        try{
            RateTable table = NBPConnector.readJsonTable(url);
            double median = table.getMedian();
            System.out.println("median: "+median);
            List<Double> dominant = table.getDominant();
            System.out.println("dominant: "+dominant);
            System.out.println("std: "+table.getStandardDeviation());
            System.out.println("CoefficientOfVariation: "+table.getCoefficientOfVariation());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
