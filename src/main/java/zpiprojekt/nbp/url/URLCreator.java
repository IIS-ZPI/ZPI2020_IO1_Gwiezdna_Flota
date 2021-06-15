package zpiprojekt.nbp.url;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

//table a ->   avg rates
//table c ->   buy/sale rates
public class URLCreator {
	private final static String NBP_INIT_URL = "http://api.nbp.pl/api/exchangerates/rates/a/";
	private final static String FORMAT_JSON = "/?format=json";
	private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

	//	private StringBuffer url;
	private Currency currency;
	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;

	public URLCreator setCurrency(Currency currency) {
		if(currency.getCurrencyCode().equals("PLN"))
			throw new IllegalArgumentException("PLN not available!");
		this.currency = currency;
		return this;
	}

	@Override
	public String toString() {
		return NBP_INIT_URL + currency + "/" +
				FORMATTER.format(dateFrom) + "/" +
				FORMATTER.format(dateTo);
	}

	public URLCreator setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
		return this;
	}

	public URLCreator setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
		return this;
	}

	public String create() {
		return this.toString() + FORMAT_JSON;
	}
}
