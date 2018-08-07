package model;

import java.util.ArrayList;
import java.util.List;

public class Nalog {

	protected int id;
	protected String sifra;
	protected int brojTelefona;
	protected int brojMinuta;
	protected double stanje;
	//
	//vrv fali lista
	List<Poziv>listaPozivaPrimaoca=new ArrayList<>();
	List<Poziv>listaPozivaPozivaoca=new ArrayList<>();
	
	
	public List<Poziv> getListaPozivaPrimaoca() {
		return listaPozivaPrimaoca;
	}

	public void setListaPozivaPrimaoca(List<Poziv> listaPozivaPrimaoca) {
		this.listaPozivaPrimaoca = listaPozivaPrimaoca;
	}

	public List<Poziv> getListaPozivaPozivaoca() {
		return listaPozivaPozivaoca;
	}

	public void setListaPozivaPozivaoca(List<Poziv> listaPozivaPozivaoca) {
		this.listaPozivaPozivaoca = listaPozivaPozivaoca;
	}

	public Nalog(int id, String sifra, int brojTelefona, int brojMinuta, double stanje) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.brojTelefona = brojTelefona;
		this.brojMinuta = brojMinuta;
		this.stanje = stanje;
	}
	
	public Nalog(String sifra, int brojTelefona, int brojMinuta, double stanje) {
		super();
		this.id=0;
		this.sifra = sifra;
		this.brojTelefona = brojTelefona;
		this.brojMinuta = brojMinuta;
		this.stanje = stanje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nalog other = (Nalog) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");	
		StringBuilder pozivaoc = new StringBuilder();
		for(Poziv  pozivi:listaPozivaPozivaoca) {
			pozivaoc.append(pozivi);
		}
		
		StringBuilder primaoc=new StringBuilder();
		for(Poziv poz:listaPozivaPrimaoca) {
			primaoc.append(newLine);
			primaoc.append(poz);
		}
		
		return "Nalog [id=" + id + ", sifra=" + sifra + ", brojTelefona=" + brojTelefona + ", brojMinuta=" + brojMinuta
				+ ", stanje=" + stanje + "]" + pozivaoc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getBrojMinuta() {
		return brojMinuta;
	}
	public void setBrojMinuta(int brojMinuta) {
		this.brojMinuta = brojMinuta;
	}

	public double getStanje() {
		return stanje;
	}

	public void setStanje(double stanje) {
		this.stanje = stanje;
	}
	
	
	
	
	
	
}
