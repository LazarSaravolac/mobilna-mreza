package ui;

import java.sql.Connection;
import java.sql.DriverManager;

import utils.PomocnaKlasa;

public class ApplicationUI {

	private static Connection conn;
	
	public static Connection getConn() {
		return conn;
	}





	static {
		// otvaranje konekcije, jednom na pocetku aplikacije
		try {
			// ucitavanje MySQL drajvera
			Class.forName("com.mysql.jdbc.Driver");
			// otvaranje konekcije
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/telefon?useSSL=false", 
					"root", 
					"root");
		} catch (Exception ex) {
			System.out.println("Neuspela konekcija na bazu!");
			ex.printStackTrace();

			// kraj aplikacije
			System.exit(0);
		}
	}
	
	public static void meni() {
		System.out.println("1. unesi nov nalog");
		System.out.println("2. prikaz naloga sa svim pozivima");
		System.out.println("3. dodavanje poziva");
		System.out.println("4. statistika");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	int odluka=-1;
	while(odluka!=0) {
		meni();
		System.out.println("Unesi opciju");
		odluka=PomocnaKlasa.ocitajCeoBroj();
		switch(odluka) {
		case 0:
			System.out.println("Izadji iz programa");
			break;
		case 1:
			NalogUI.dodajNalog();
			break;
		case 2:
			NalogUI.prikazNaloga();
			break;
		case 3:
			PozivUI.dodajPoziv();
			break;
		case 4:
			NalogUI.statistickiPrikazNaloga();
			break;
		}
	}
	}

}
