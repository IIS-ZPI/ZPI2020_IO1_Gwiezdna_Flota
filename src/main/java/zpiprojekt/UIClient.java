package zpiprojekt;

import java.util.Scanner;

import java.util.Currency;
import java.util.Scanner;

public class UIClient {
    private static Scanner in = new Scanner(System.in);

    /**
     * Interfejs użytkownika: wybór akcji #50
     */
    public static void runUI() {
        System.out.println("Statystyki z danych NBP");
        System.out.println("Wybierz akcję: ");

        for (;;) {
            System.out.println("Wpisz '1' aby przejść do zmian rozkładów miesięcznych i kwartalnych");
            System.out.println("Wpisz '2' aby przejść do miar statystycznych");
            System.out.println("Wpisz '3' aby przejść do wyznaczenie ilości sesji wzrostowych, spadkowych i bez zmian");

            String userIn = in.nextLine();
            int num = 0;
            try {
                num = Integer.parseInt(userIn);
            } catch (NumberFormatException e) {
                System.out.println("Błąd!  Wpisano nieprawidłowe dane.");
                continue;
            }

            switch (num) {
                case 1:
                    actionDistributionChanges();
                    break;
                case 2:
                    actionStatisticalMeasures();
                    break;
                case 3:
                    actionSessions();
                    break;
                default:
                    System.out.println("Błąd! Wpisano nieprawidłowe liczbę.");
            }
        }
    }

    /**
     * Interfejs użytkownika: rozkład zmian miesięcznych i kwartalnych #49 Wartość
     * zwracana: 0 - gdy wszystkie wprowadzone dane są poprawne 
     * 1 - gdy wprowadzono niepoprawny kod waluty
     * 2 - gdy podano nieprawidlowy przedzial czasowy, np. string abc zamiast liczby
     * 3 - gdy podano nieprawidlowa liczbe oznaczajaca przedzial czasowy
     */
    private static int actionDistributionChanges() {
        Currency firstCurrency;
        Currency secondCurrency;
        System.out.println("Podaj pierwsza walute [PLN, USD]");
        try {
            String userInput = in.nextLine();
            firstCurrency = Currency.getInstance(userInput);
        } catch (Exception e) {
            System.out.println("Niepoprawny kod waluty!");
            return 1;
        }

        System.out.println("Podaj pierwsza walute [PLN, USD]");
        try {
            String userInput = in.nextLine();
            secondCurrency = Currency.getInstance(userInput);
        } catch (Exception e) {
            System.out.println("Niepoprawny kod!");
            return 1;
        }

        System.out.println("Podaj przedział czasowy \n 1 - miesiac \n 2 - kwartał");
        String userInput = in.nextLine();
        int timeInterval = 0;
        try {
            timeInterval = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Błąd!  Wpisano nieprawidłowe dane.");
            return 2;
        }

        if (timeInterval != 1 && timeInterval != 2) {
            System.out.println("Zly przedział. Podaj jeszcze raz!");
            return 3;
        }

        // somefunction(firstCode, secondCode, timeInterval);
        return 0;
    }

    /**
     * Interfejs użytkownika: 
     * miary statystyczne #48
     */
    private static void actionStatisticalMeasures(){
        String currency;
    }

    private static void showDataOptions() {
        System.out.println(
                "" + "1 - 1 tygodnie\n" + "2 - 2 tygodnie\n" + "3 - 1 miesiąc\n" + "4 - pół roku\n" + "5 - 1 rok\n");
    }

    /**
     * Interfejs użytkownika: wyznaczenie ilości sesji wzrostowych, spadkowych i bez
     * zmian #47
     */
    private static void actionSessions() {

    }
}