package zpiprojekt.nbp.data;

import java.util.*;
import java.util.stream.Collectors;

public class RatesStatistics {
	public static double getMedian(RateTable table) {
		Collections.sort(table.rates);
		int len = table.rates.size();
		if (len % 2 != 0) {
			return table.rates.get((int) Math.ceil(len / 2.0)).mid;
		} else {
			double mid1 = table.rates.get((int) (len / 2.0)).mid;
			double mid2 = table.rates.get((int) (len / 2.0 + 1)).mid;
			return (mid1 + mid2) / 2;
		}
	}

	public static DoubleSummaryStatistics getStatistics(RateTable table) {
		return table.rates.stream()
				.mapToDouble((x) -> x.mid)
				.summaryStatistics();
	}

	public static List<Double> getDominant(RateTable table) {
		HashMap<Double, Integer> ratesMap = new HashMap<>();
		for (Rate rate : table.rates) {
			Integer key = ratesMap.getOrDefault(rate.mid, 0);
			ratesMap.put(rate.mid, key + 1);
		}
		Integer maxOccurrences = ratesMap.values().stream().max(Integer::compare).orElse(1);

		Map<Double, Integer> map2 = ratesMap.entrySet()
				.stream()
				.filter(map -> map.getValue().intValue() == maxOccurrences)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		if (ratesMap.size() == map2.size()) {
			return new ArrayList<>();
		}
		return new ArrayList<>(map2.keySet());
	}

	public static double getStandardDeviation(RateTable table) {
		double avg = getStatistics(table).getAverage();
		double std = 0.0;
		for (Rate rate : table.rates) {
			std += Math.pow(rate.mid - avg, 2);
		}
		return Math.sqrt(std / table.rates.size());
	}

	public static double getCoefficientOfVariation(RateTable table) {
		return getStandardDeviation(table) * getStatistics(table).getAverage();
	}

	public static Map<String, String> getAllStatistics(RateTable table) {
		Map<String, String> statsMap = new HashMap<>();
		statsMap.put("mediana", String.valueOf(getMedian(table)));
		StringBuilder str = new StringBuilder();
		String dominant = getDominant(table).toString()
				.replace("[", " ")
				.replace("]", " ");

		statsMap.put("dominanta", dominant);
		statsMap.put("odchylenie standardowe", String.valueOf(getStandardDeviation(table)));
		statsMap.put("Współczynnik zmienności", String.valueOf(getCoefficientOfVariation(table)));
		return statsMap;
	}
}
