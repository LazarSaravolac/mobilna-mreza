package utils;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class PomocnaKlasa {
public static SimpleDateFormat FORMAT_DATUMA = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
public static SimpleDateFormat DATUM = new SimpleDateFormat("yyyy-MM-dd");
	static Scanner sc = new Scanner(System.in);
	
	//citanje promenljive String
	public static String ocitajTekst(){
		String tekst = "";
		while(tekst == null || tekst.equals(""))
			tekst = sc.nextLine();
		
		return tekst;
	}
		
	//citanje promenljive integer
	public static int ocitajCeoBroj(){
		while (sc.hasNextInt()==false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		int ceoBroj = sc.nextInt();
		sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
		return ceoBroj;
	}
		
	//citanje promenljive double
	public static float ocitajRealanBroj(){

		while (sc.hasNextFloat()==false) {
			System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			sc.nextLine();
		}
		float realanBroj = sc.nextFloat();
		sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
		return realanBroj;
	}
		
	//citanje promenljive char
	public static char ocitajKarakter(){
		
		String text;
		while ( (text=sc.next())==null || text.length()!=1) {
			System.out.println("GRESKA - Pogresno unsesena vrednost za karakter, pokusajte ponovo: ");
			sc.nextLine();
		}
		char karakter = text.charAt(0);
		return karakter;
	}

	//citanje promenljive char
	public static char ocitajOdlukuOPotvrdi(String tekst){
		System.out.println("Da li zelite " + tekst + " [Y/N]:");
		char odluka = ' ';
		while( !(odluka == 'Y' || odluka == 'N') ){
			odluka = ocitajKarakter();
			if (!(odluka == 'Y' || odluka == 'N')) {
				System.out.println("Opcije su Y ili N");
			}
		}
		return odluka;
	}

	public static Timestamp ocitajDatum() {
		Timestamp datum = null;
		while (datum == null) {
			String tekst = sc.nextLine();
			try {
				java.util.Date utilDatum = FORMAT_DATUMA.parse(tekst);
				datum = new Timestamp(utilDatum.getTime());
			} catch (ParseException ex) {
				System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			}
		}
		return datum;
	}

	
	public static Date ocitajDatumPravi() {
		Date datum = null;
		while (datum == null) {
			String tekst = sc.nextLine();
			try {
				java.util.Date utilDatum = DATUM.parse(tekst);
				datum = new Date(utilDatum.getTime());
			} catch (ParseException ex) {
				System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
			}
		}
		return datum;
	}
	static boolean isInteger(String s){
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	static boolean isDouble(String s){
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	static boolean isBoolean(String s){
		try {
			Boolean.parseBoolean(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	static boolean isDatum(String s) {
		try {
			FORMAT_DATUMA.parse(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
