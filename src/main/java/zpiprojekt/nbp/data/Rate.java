package zpiprojekt.nbp.data;

public class Rate implements Comparable<Rate>{
	public String no;
	public String effectiveDate;
	public Double mid;
	public Double bid;
	public Double ask;

	@Override
	public String toString() {
		return "no: "+no+"\n"+
				"effectiveDate: "+effectiveDate+"\n"+
				"mid: "+mid+"\n"+
				"bid: "+bid+"\n"+
				"ask: "+ask+"\n\n";
	}

	@Override
	public int compareTo(Rate rate) {
		return Double.compare(this.mid,rate.mid);
	}
}
