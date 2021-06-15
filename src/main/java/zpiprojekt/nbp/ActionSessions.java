package zpiprojekt.nbp;

import zpiprojekt.NBPConnector;
import zpiprojekt.nbp.data.Rate;
import zpiprojekt.nbp.data.RateTable;
import zpiprojekt.nbp.url.URLCreator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Currency;

public class ActionSessions {
    private Currency currency;
    private LocalDateTime dateFrom;
    private int growthSession;
    private int decreaseSession;
    private int unchangedSession;
    public ActionSessions(Currency currency, int interval)
    {
        this.currency = currency;
        intervelToDate(interval);
    }
    private void intervelToDate(int interval)
    {
        dateFrom = LocalDateTime.now();
        switch (interval)
        {
            case 1:
                dateFrom = dateFrom.minusWeeks(1);
                break;
            case 2:
                dateFrom = dateFrom.minusWeeks(2);
                break;
            case 3:
                dateFrom = dateFrom.minusMonths(1);
                break;
            case 4:
                dateFrom = dateFrom.minusMonths(6);
                break;
            case 5:
                dateFrom = dateFrom.minusYears(1);
                break;
        }
    }
    private void calculate() throws IOException {
        String url = new URLCreator().setCurrency(currency).setDateFrom(dateFrom).setDateTo(LocalDateTime.now()).create();
        RateTable table = NBPConnector.readJsonTable(url);
        Double prevValue = table.rates.get(0).mid;
        growthSession = 0;
        decreaseSession = 0;
        unchangedSession = 0;
        for(Rate i : table.rates)
        {
            if (i == table.rates.get(0))
                continue;
            if(prevValue < i.mid)
                growthSession++;
            else if(prevValue > i.mid)
                decreaseSession++;
            else
                unchangedSession++;
            prevValue = i.mid;
        }
    }
    public int getGrowthSessions() throws IOException {
        calculate();
        return growthSession;
    }
    public int getDecreaseSessions() throws IOException {
        calculate();
        return decreaseSession;
    }
    public int getUnchangedSessions() throws IOException {
        calculate();
        return unchangedSession;
    }
}
