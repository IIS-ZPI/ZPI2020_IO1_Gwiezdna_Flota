package zpiprojekt.nbp.data;


import java.util.*;
import java.util.stream.Collectors;

import java.text.ParseException;
import java.text.SimpleDateFormat;


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

}

