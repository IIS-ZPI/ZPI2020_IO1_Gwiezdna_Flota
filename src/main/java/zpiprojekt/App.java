package zpiprojekt;

import zpiprojekt.nbp.url.URLCreator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;

public class App {
	public static void main(String[] args) throws IOException {

		LocalDateTime dateFrom = LocalDateTime.now().minusDays(45);
		LocalDateTime dateTo = LocalDateTime.now();

		URLCreator urlCreator = new URLCreator()
				.setCurrency(Currency.getInstance(Locale.UK))
				.setDateFrom(dateFrom)
				.setDateTo(dateTo);
        System.out.println(NBPConnector.readJsonTable(urlCreator.create()));

	}
}
