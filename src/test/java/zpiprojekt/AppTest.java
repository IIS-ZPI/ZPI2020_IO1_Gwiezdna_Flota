package zpiprojekt;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

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

}
