package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Poziv {

	protected int id;
	protected Timestamp pocetak;
	protected Timestamp kraj;
	protected Nalog pozivaoc;
	protected Nalog primaoc;
	public Poziv(int id, Timestamp pocetak, Timestamp kraj, Nalog pozivaoc, Nalog primaoc) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.pozivaoc = pozivaoc;
		this.primaoc = primaoc;
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
		Poziv other = (Poziv) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Poziv [id=" + id + ", pocetak=" + pocetak + ", kraj=" + kraj + ", pozivaoc=" + pozivaoc + ", primaoc="
				+ primaoc + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getPocetak() {
		return pocetak;
	}
	public void setPocetak(Timestamp pocetak) {
		this.pocetak = pocetak;
	}
	public Timestamp getKraj() {
		return kraj;
	}
	public void setKraj(Timestamp kraj) {
		this.kraj = kraj;
	}
	public Nalog getPozivaoc() {
		return pozivaoc;
	}
	public void setPozivaoc(Nalog pozivaoc) {
		this.pozivaoc = pozivaoc;
	}
	public Nalog getPrimaoc() {
		return primaoc;
	}
	public void setPrimaoc(Nalog primaoc) {
		this.primaoc = primaoc;
	}
	
	
}
