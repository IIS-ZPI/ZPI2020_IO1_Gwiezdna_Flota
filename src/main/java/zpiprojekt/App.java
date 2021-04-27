package zpiprojekt;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        System.out.println(NBPConnector.readJsonTable("http://api.nbp.pl/api/exchangerates/rates/a/gbp/last/10/?format=json"));
//        new NBPConnector("http://api.nbp.pl/api/exchangerates/rates/c/usd/2016-04-04/?format=json").readJsonTable();
//        System.out.println( NBPConnector.readJsonPrices("http://api.nbp.pl/api/cenyzlota/last/30/?format=json"));

    }
}
