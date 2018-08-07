package model;

public class NalogStatistika {

	private String sifra;
	private int brojTelefona;
	public NalogStatistika(String sifra, int brojTelefona) {
		super();
		this.sifra = sifra;
		this.brojTelefona = brojTelefona;
	}
	public NalogStatistika() {
		super();
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public int getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(int brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	
	
}
