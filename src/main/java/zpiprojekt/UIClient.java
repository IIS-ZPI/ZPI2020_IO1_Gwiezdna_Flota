package zpiprojekt;
import java.util.Scanner;

import java.util.Currency;
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
        String firstCode;
        String secondCode;
        int timeInterval;
        Currency test;
        while(true) {
            System.out.println("Podaj pierwsza walute [PLN, USD]");
            firstCode = in.nextLine();
            try {
                test = Currency.getInstance(firstCode);
                break;
            } catch (Exception e) {
                System.out.println("Niepoprawny kod ! Podaj jeszcze raz");
            }

        }

        while (true){
            System.out.println("Podaj druga walute");
            secondCode = in.nextLine();
            try {
                test = Currency.getInstance(secondCode);
                break;
            } catch (Exception e) {
                System.out.println("Niepoprawny kod !Podaj jeszcze raz");
            }

        }
        System.out.println("Podaj przedział czasowy \n 1 - miesiac \n 2 - kwartał");
        while (true){
            try {
                timeInterval = in.nextInt();
                if( timeInterval != 1 && timeInterval != 2){
                    System.out.println("Zly przedział. Podaj jeszcze raz !");
                }
                else
                    break;
            } catch (Exception e) {
                System.out.println("Niepoprawne dane ! Podaj jeszcze raz !");
                in.nextLine();
            }

        }
        timeInterval += 2;

        // somefunction(firstCode, secondCode, timeInterval);

        }
    
    /**
     * Interfejs użytkownika: 
     * miary statystyczne #48
     */
    private static void actionStatisticalMeasures(){
        String currency;
    }
    private static void showDataOptions()
    {
        System.out.println("" +
                "1 - 1 tygodnie\n" +
                "2 - 2 tygodnie\n" +
                "3 - 1 miesiąc\n" +
                "4 - pół roku\n" +
                "5 - 1 rok\n"
        );
    }


    /**
     * Interfejs użytkownika: 
     * wyznaczenie ilości sesji wzrostowych, spadkowych i bez zmian #47
     */
    private static void actionSessions(){

    }
}