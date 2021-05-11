package zpiprojekt;

import java.util.Currency;
import java.util.Scanner;

public class UIClient {
    private static Scanner in = new Scanner(System.in);

    public static void runUI(){
        
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

    }

    /**
     * Interfejs użytkownika: 
     * wyznaczenie ilości sesji wzrostowych, spadkowych i bez zmian #47
     */
    private static void actionSessions(){

    }
}