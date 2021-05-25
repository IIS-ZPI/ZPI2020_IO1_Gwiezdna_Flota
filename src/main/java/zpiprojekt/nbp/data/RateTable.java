package zpiprojekt.nbp.data;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class RateTable {
	public String table;
	public String currency;
	public String code;
	public ArrayList<Rate> rates;


	public double getMedian() {
		Collections.sort(rates);
		int len = rates.size();
		if (len % 2 != 0) {
			return rates.get((int) Math.ceil(len / 2.0)).mid;
		} else {
			double mid1 = rates.get((int) (len / 2.0)).mid;
			double mid2 = rates.get((int) (len / 2.0 + 1)).mid;
			return (mid1 + mid2) / 2;
		}
	}

	//	public Double getDominant() {
//		int occurrences = 0;
//		int maxOccurrences = 0;
//		double dominant = rates.get(0).mid;
//		double maxDominant = dominant;
//
//		Collections.sort(rates);
//		for (Rate rate : rates) {
//			if(dominant == rate.mid){
//				occurrences ++;
//			}
//			else{
//				if(maxOccurrences < occurrences){
//					maxDominant = dominant;
//					maxOccurrences = occurrences;
//				}
//				dominant = rate.mid;
//				occurrences = 1;
//			}
//		}
//
//		return maxDominant;
//	}
	public List<Double> getDominant() {
		HashMap<Double, Integer> ratesMap = new HashMap<>();
		for (Rate rate : rates) {
			Integer key = ratesMap.getOrDefault(rate.mid, 0);
			ratesMap.put(rate.mid, key+1);
		}
		Integer maxOccurrences = ratesMap.values().stream().max(Integer::compare).orElse(1);

		Map<Double,Integer> map2 = ratesMap.entrySet()
				.stream()
				.filter(map -> map.getValue().intValue() == maxOccurrences)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		if (ratesMap.size() == map2.size()){
			return null;
		}
		return new ArrayList<>(map2.keySet());

	}

	@Override
	public String toString() {
		return "Table: " + table + "\n" +
				"Currency: " + currency + "\n" +
				"code: " + code + "\n" +
				"rates:\n" + rates.toString();
	}
}
