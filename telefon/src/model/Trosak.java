package model;

import java.sql.Timestamp;

public class Trosak {

	private Timestamp pocetak;
	private Timestamp kraj;
	private int pozivaoc_id;
	public Trosak() {
		super();
	}
	public Trosak(Timestamp pocetak, Timestamp kraj, int pozivaoc_id) {
		super();
		this.setPocetak(pocetak);
		this.setKraj(kraj);
		this.setPozivaoc_id(pozivaoc_id);
	}
	public int getPozivaoc_id() {
		return pozivaoc_id;
	}
	public void setPozivaoc_id(int pozivaoc_id) {
		this.pozivaoc_id = pozivaoc_id;
	}
	public Timestamp getKraj() {
		return kraj;
	}
	public void setKraj(Timestamp kraj) {
		this.kraj = kraj;
	}
	public Timestamp getPocetak() {
		return pocetak;
	}
	public void setPocetak(Timestamp pocetak) {
		this.pocetak = pocetak;
	}
	
	
}
