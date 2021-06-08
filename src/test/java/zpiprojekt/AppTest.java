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
        String toWrite = "EUR\nPLN\n1\n";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 1);
    }

    @Test 
    public void testUIClientActionDistributionChanges005(){
        String toWrite = "EUR\nEUR\n1\n";
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
        String toWrite = "EUR\nUSD\n4353463N74";
        InputStream in = writeStringAsSystemIn(toWrite);
        UIClient.setScannerIn(in);
        int retVal = UIClient.actionDistributionChanges();

        assertTrue(retVal == 2);
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
    @Test
    public void testUIClientActionDistributionChanges009(){
        String toWrite = "EUR\nUSD\n\n";
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

        assertTrue(retVal == 3);
    }
    @Test 
    public void testUIClientActionStatisticalMeasures004(){
        String toWrite = "USD\n5\n";
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
