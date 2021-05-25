package zpiprojekt;

import java.util.Scanner;
import java.util.Currency;


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
            System.out.println("Wpisz '4' aby zakończyć program");

            String userIn = in.nextLine();
            switch (userIn) {
                case "1":
                    actionDistributionChanges();
                    break;
                case "2":
                    actionStatisticalMeasures();
                    break;
                case "3":
                    actionSessions();
                    break;
                case "4":
                    return;
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
        System.out.println("Podaj pierwszą walutę w formacie ISO 4217 zawierającą trzyliterowy kod np: \"PLN\": ");
        try {
            String userInput = in.nextLine();
            firstCurrency = Currency.getInstance(userInput);
        } catch (Exception e) {
            System.out.println("Niepoprawny kod!");
            return 1;
        }

        System.out.println("Podaj drugą walutę w formacie ISO 4217 zawierającą trzyliterowy kod np: \"PLN\": ");
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


    private static void showDataOptions() {
        System.out.println(
                "" + "1 - 1 tygodnie\n" + "2 - 2 tygodnie\n" + "3 - 1 miesiąc\n" + "4 - pół roku\n" + "5 - 1 rok\n");
    }
    /**
     * Interfejs użytkownika: 
     * miary statystyczne #48
     */
    private static int actionStatisticalMeasures(){
        Currency currency;
        System.out.println("Podaj walutę w formacie ISO 4217 zawierającą trzyliterowy kod np: \"PLN\": ");
        try {
            String userInput = in.nextLine();
            currency = Currency.getInstance(userInput);
        } catch (Exception e) {
            System.out.println("Niepoprawny kod!");
            return 1;
        }

        showDataOptions();

        String userInput = in.nextLine();
        int timeInterval = 0;
        try {
            timeInterval = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Błąd!  Wpisano nieprawidłowe dane.");
            return 2;
        }
        if (timeInterval < 1 || timeInterval > 5) {
            System.out.println("Zly przedział czasowy!");
            return 3;
        }

        // somefunction(firstCode, timeInterval);
        return 0;
    }


    /**
     * Interfejs użytkownika: wyznaczenie ilości sesji wzrostowych, spadkowych i bez
     * zmian #47
     */
    private static int actionSessions() {
        Currency currency;
        System.out.println("Podaj walutę w formacie ISO 4217 zawierającą trzyliterowy kod np: \"PLN\": ");
        try {
            String userInput = in.nextLine();
            currency = Currency.getInstance(userInput);
        } catch (Exception e) {
            System.out.println("Niepoprawny kod!");
            return 1;
        }   

        showDataOptions();

        String userInput = in.nextLine();
        int timeInterval = 0;
        try {
            timeInterval = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Błąd!  Wpisano nieprawidłowe dane.");
            return 2;
        }
        if (timeInterval != 1 && timeInterval != 2 && timeInterval != 3 && timeInterval != 4 && timeInterval != 5) {
            System.out.println("Zly przedział. Podaj jeszcze raz!");
            return 3;
        }

        // somefunction(firstCode, timeInterval);
        return 0;
    }
}