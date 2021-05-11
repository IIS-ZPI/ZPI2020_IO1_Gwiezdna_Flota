package zpiprojekt;
import java.util.Scanner;

public class UIClient {
    private static Scanner in = new Scanner(System.in);

    /**
     * Interfejs użytkownika: 
     * wybór akcji #50
     */
    public static void runUI(){
        System.out.println("Statystyki z danych NBP");
        System.out.println("Wybierz akcję: ");

        for(;;){
            System.out.println("Wpisz '1' aby przejść do zmian rozkładów miesięcznych i kwartalnych");
            System.out.println("Wpisz '2' aby przejść do miar statystycznych");
            System.out.println("Wpisz '3' aby przejść do wyznaczenie ilości sesji wzrostowych, spadkowych i bez zmian");

            String userIn = in.nextLine();
            int num = 0;
            try{
                num = Integer.parseInt(userIn);
            } catch(NumberFormatException e){
                System.out.println("Błąd!  Wpisano nieprawidłowe dane.");
                continue;
            }

            switch(num){
                case 1: actionDistributionChanges(); break;
                case 2: actionStatisticalMeasures(); break;
                case 3: actionSessions(); break;
                default: System.out.println("Błąd! Wpisano nieprawidłowe liczbę.");
            }
        }
    }

    /**
     * Interfejs użytkownika: 
     * rozkład zmian miesięcznych i kwartalnych #49
     */
    private static void actionDistributionChanges(){

    }
    
    /**
     * Interfejs użytkownika: 
     * miary statystyczne #48
     */
    private static void actionStatisticalMeasures(){

    }

    /**
     * Interfejs użytkownika: 
     * wyznaczenie ilości sesji wzrostowych, spadkowych i bez zmian #47
     */
    private static void actionSessions(){

    }
}