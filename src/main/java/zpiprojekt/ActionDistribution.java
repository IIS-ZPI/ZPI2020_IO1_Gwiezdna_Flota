package zpiprojekt;

import zpiprojekt.nbp.data.Rate;
import zpiprojekt.nbp.data.RateTable;
import zpiprojekt.nbp.url.URLCreator;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class ActionDistribution {
   static LinkedHashMap<Range, Integer> map = new LinkedHashMap();

   static public LinkedList<Double> getDistribution(Currency firstCurrency, Currency secondCurrency, int timeInterval) throws IOException {
        LocalDateTime dateTo = LocalDateTime.now();
        LocalDateTime dateFrom = null;
        if (timeInterval == 1)
            dateFrom = dateTo.minusMonths(1);
        else if (timeInterval == 2)
            dateFrom = dateTo.minusMonths(3);
        String firstCurrencyURL = new URLCreator().setCurrency(firstCurrency).setDateFrom(dateFrom).setDateTo(dateTo).create();
        String secondCurrencyURL = new URLCreator().setCurrency(secondCurrency).setDateFrom(dateFrom).setDateTo(dateTo).create();
        RateTable firstTable = NBPConnector.readJsonTable(firstCurrencyURL);
        RateTable secondTable = NBPConnector.readJsonTable(secondCurrencyURL);
        Comparator<Rate> comparator = new Comparator<Rate>() {
            @Override
            public int compare(Rate rate1, Rate rate2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = format.parse(rate1.effectiveDate);
                    date2 = format.parse(rate2.effectiveDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return date1.compareTo(date2);
            }
        };
        LinkedList<Double> currencyList = new LinkedList<>();
       firstTable.rates.sort(comparator);
       secondTable.rates.sort(comparator);
       for(int i=0; i<firstTable.rates.size(); i++){
           currencyList.add(firstTable.rates.get(i).mid / secondTable.rates.get(i).mid);
       }

       LinkedList<Double> differenceList = new LinkedList<>();
       for(int i=1; i<currencyList.size(); i++){
           differenceList.add(currencyList.get(i) - currencyList.get(i - 1));
       }
       double start = Collections.min(differenceList);
       double end = Collections.max(differenceList);
       double step = (end - start) / 10;
       List<Range> rangeList = new LinkedList<>();
       for(int i=0; i<10; i++){
           rangeList.add(new Range(start, start + step));
           start = start + step;
       }

      for(Double value: differenceList){
          for(Range range: rangeList){
              if(range.contains(value)){
                  int returnedValue = map.getOrDefault(range, 0);
                  map.put(range, returnedValue + 1);
              }
          }
      }
      System.out.println(map);
       return currencyList;

    }

    static public void saveToFile(String filename, Currency firstCurrency, Currency secondCurrency, LinkedHashMap<String, Double> firstMap, LinkedHashMap<String, Double> secondMap) throws IOException {
        FileWriter csvFileWrite = new FileWriter(filename);
        csvFileWrite.append("Date");
        csvFileWrite.append(",");
        csvFileWrite.append(firstCurrency.getCurrencyCode());
        csvFileWrite.append(",");
        csvFileWrite.append(secondCurrency.getCurrencyCode());
        csvFileWrite.append("/n");

    }


    public static void main(String[] args) throws IOException {
       getDistribution(Currency.getInstance("EUR"), Currency.getInstance("USD"), 1);
    }
}


class Range{
    double start;
    double end;
    public Range(double start, double end){
        this.start = start;
        this.end = end;
    }

    public boolean contains(double x){
        return x >= start && x < end;
    }
    @Override
    public String toString(){
        return "(" + start + ", " + end + ")";
    }
}
