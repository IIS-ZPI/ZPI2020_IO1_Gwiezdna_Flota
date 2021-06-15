package zpiprojekt.nbp.data;

import zpiprojekt.NBPConnector;
import zpiprojekt.nbp.url.URLCreator;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class RatesStatistics {

	private Currency currency;
	private RateTable table;
	private LocalDateTime  dateFrom;
	public RatesStatistics(Currency currency,int interval)throws IOException{
		this.currency = currency;
		intervalToDate(interval);
		String url = new URLCreator().setCurrency(this.currency).setDateFrom(dateFrom).setDateTo(LocalDateTime.now()).create();
		this.table = NBPConnector.readJsonTable(url);
	}
	private void intervalToDate(int interval)
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

	public  double getMedian() {
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

	public  DoubleSummaryStatistics getStatistics() {
		return table.rates.stream()
				.mapToDouble((x) -> x.mid)
				.summaryStatistics();
	}

	public  List<Double> getDominant() {
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

	public  double getStandardDeviation() {
		double avg = getStatistics().getAverage();
		double std = 0.0;
		for (Rate rate : table.rates) {
			std += Math.pow(rate.mid - avg, 2);
		}
		return Math.sqrt(std / table.rates.size());
	}

	public double getCoefficientOfVariation() {
		return getStandardDeviation() * getStatistics().getAverage();
	}

	public Map<String, String> getAllStatistics() {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.####",otherSymbols);
		Map<String, String> statsMap = new HashMap<>();
		statsMap.put("mediana", df.format(getMedian()));
		StringBuilder dominant = new StringBuilder();
		for (Double d :getDominant()){
			dominant.append(df.format(d)).append(" ");
		}
		if (dominant.toString().isEmpty())
			dominant.append("brak");
		statsMap.put("dominanta", dominant.toString());
		statsMap.put("odchylenie standardowe", df.format(getStandardDeviation()));
		statsMap.put("Współczynnik zmienności", df.format(getCoefficientOfVariation()));
		return statsMap;
	}
}
