package zpiprojekt.nbp.data;

import java.util.ArrayList;

public class RateTable {
	public String table;
	public String currency;
	public String code;
	public ArrayList<Rate> rates;

	@Override
	public String toString() {
		return "Table: "+table+"\n"+
				"Currency: "+currency+"\n"+
				"code: "+code+"\n"+
				"rates:\n"+rates.toString();
	}
}
