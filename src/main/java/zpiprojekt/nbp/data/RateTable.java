package zpiprojekt.nbp.data;


import java.util.*;
import java.util.stream.Collectors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class RateTable {
	public String table;
	public String currency;
	public String code;
	public ArrayList<Rate> rates;




	@Override
	public String toString() {
		return "Table: " + table + "\n" +
				"Currency: " + currency + "\n" +
				"code: " + code + "\n" +
				"rates:\n" + rates.toString();
	}

	public HashMap<String, Double> getChangeTable(){
		Collections.sort(rates, new Comparator<Rate>() {
			@Override public int compare(Rate rate1, Rate rate2) {
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
		});
		LinkedHashMap<String, Double> hashMap = new LinkedHashMap<>();
		for(int i=1; i<rates.size(); i++){
			Double diff = (rates.get(i).mid - rates.get(i-1).mid) / rates.get(i -1).mid * 100;
			hashMap.put(rates.get(i).effectiveDate, Math.abs(diff));
		}
		return hashMap;
	}
}

