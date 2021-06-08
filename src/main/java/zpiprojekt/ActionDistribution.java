package zpiprojekt;

import zpiprojekt.nbp.data.RateTable;
import zpiprojekt.nbp.url.URLCreator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Map;

public class ActionDistribution {
     // 3 -> miesiac 4 -> kwartal


   static public Map<String, Double> getDistribution(Currency firstCurrency, int timeInterval) throws IOException {
        LocalDateTime dateTo = LocalDateTime.now();
        LocalDateTime dateFrom = null;
        if (timeInterval == 3)
            dateFrom = dateTo.minusMonths(1);
        else if (timeInterval == 4)
            dateFrom = dateTo.minusMonths(3);
        String firstCurrencyURL = new URLCreator().setCurrency(firstCurrency).setDateFrom(dateFrom).setDateTo(dateTo).create();
        RateTable rateTable = NBPConnector.readJsonTable(firstCurrencyURL);
        return rateTable.getChangeTable();


    }

}

