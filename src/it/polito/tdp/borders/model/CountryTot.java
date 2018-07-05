package it.polito.tdp.borders.model;

public class CountryTot implements Comparable<CountryTot> {
	
	private int codiceCountry;
	private int totConfini;

	public CountryTot(int codiceCountry, int totConfini) {
		super();
		this.codiceCountry = codiceCountry;
		this.totConfini = totConfini;
	}
	public int getCodiceCountry() {
		return codiceCountry;
	}
	public void setCodiceCountry(int codiceCountry) {
		this.codiceCountry = codiceCountry;
	}
	public int getTotConfini() {
		return totConfini;
	}
	public void setTotConfini(int totConfini) {
		this.totConfini = totConfini;
	}
	@Override
	public int compareTo(CountryTot o) {

		return -(totConfini-o.totConfini);
	}

}
