package ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.NalogDAO;
import dao.PozivDAO;
import model.Nalog;
import model.NalogStatistika;
import model.Poziv;
import model.Trosak;
import utils.PomocnaKlasa;

public class NalogUI {

	
	
	
	
	public static Nalog pronadji(int id) {
		Nalog nalog=NalogDAO.getNalogByID(ApplicationUI.getConn(), id);
		return nalog;
	}
	
	public static Nalog pronadji() {
		System.out.println("Unesi id");
		int id=PomocnaKlasa.ocitajCeoBroj();
		Nalog nalog=pronadji(id);
//		if(nalog==null) {
//			System.out.println("Nalog sa " + id + " ne postoji");
//		}
		return nalog;
	}
	
	
	public static void prikazNaloga() {
		List<Nalog>nalozi=NalogDAO.getAll(ApplicationUI.getConn());
		System.out.println("Unesi id naloga");
		int id=PomocnaKlasa.ocitajCeoBroj();
		Nalog nalog=pronadji(id);
		if(nalog!=null) {
			System.out.println(nalog);
		}
		System.out.println("Korisnik kao pozivaoc");
		List<Poziv> poziv=PozivDAO.getAllByNalogPozivaoc(ApplicationUI.getConn(), nalog);
		for(Poziv poz:poziv) {
			System.out.println(poz);
		}
		System.out.println();
		System.out.println("Korisnik kao primaoc");
		List<Poziv> po=PozivDAO.getAllByNalogPrimaoc(ApplicationUI.getConn(), nalog);
		for(Poziv p:po) {
			System.out.println(p);
		}
//		for(Nalog nal:nalozi) {
//			if(nal.equals(nalog))
//			System.out.println(nal);
//		}
//		
	}
	
	
	public static void statistickiPrikazNaloga() {
		Map<Integer, Integer> OdlazeciPozivi = new HashMap<>();
		Map<Integer, Integer> DolazeciPozivi = new HashMap<>();	
		System.out.println("UNESITE OPSEG");
		System.out.print("pocetak:");
		Date pocetak = PomocnaKlasa.ocitajDatumPravi();
		System.out.println(pocetak);
		System.out.print("kraj:");
		Date kraj = PomocnaKlasa.ocitajDatumPravi();
		System.out.println(kraj);
		List<Integer>odlazeci=NalogDAO.getOdlazeciPozivi(ApplicationUI.getConn(),pocetak,kraj);
		List<NalogStatistika>nalogStatistika=NalogDAO.getNalogStatistika(ApplicationUI.getConn());
		List<Integer>dolazeci=NalogDAO.getDolazeciPozivi(ApplicationUI.getConn(),pocetak,kraj);
		List<Trosak>trosak=NalogDAO.getTrosak(ApplicationUI.getConn(),pocetak,kraj);
		List<Integer>samTrosak=new ArrayList<>();
		Map<Integer, Integer> TrosakID = new HashMap<>();	
		for(int i=1;i<6;i++) {
			OdlazeciPozivi.put(i, 0);
			DolazeciPozivi.put(i, 0);
		}
//		for(int i=0;i<5;i++) {
//			TrosakID.put(i, 0);
//		}
		for(Trosak tr:trosak) {
			long razlika=tr.getKraj().getTime()-tr.getPocetak().getTime();
			int razlikaInt=(int)(razlika/(1000*60));
			samTrosak.add(razlikaInt);
			TrosakID.put(tr.getPozivaoc_id(),razlikaInt*9);
			if(TrosakID.get(tr.getPozivaoc_id()) != null) {
				int e=TrosakID.get(tr.getPozivaoc_id());
				int s=razlikaInt*9;
				int t=e+s;
				TrosakID.put(tr.getPozivaoc_id(), t);
			}
		}
		
		
		Map<Integer, Integer> troosak = new HashMap<>();	
		
		for(int i:TrosakID.keySet()) {
			System.out.println(i);
		}
		System.out.println("TrosakID");
		
		
		for(int s:samTrosak) {
			System.out.println(s);
		}
		System.out.println("samTrosak");
		for(int i=0;i<5;i++) {
			troosak.put(i, 0);
		}
		for(int sifraTrosak:TrosakID.values()) {
			System.out.println(sifraTrosak);
//			int brojT=troosak.get(sifraTrosak);
//			brojT++;
//			troosak.put(sifraTrosak, brojT);
		}
		System.out.println("TrosakId vrednost");
		for(int sifraDolazeci:dolazeci) {
			int brojD=DolazeciPozivi.get(sifraDolazeci);
			brojD++;
			DolazeciPozivi.put(sifraDolazeci, brojD);
		}
		
		for(int sifraOdlazeci:odlazeci) {
			int brojO=OdlazeciPozivi.get(sifraOdlazeci);
			brojO++;
//			System.out.println(brojO);
			OdlazeciPozivi.put(sifraOdlazeci, brojO);
			
		}
		
		System.out.println("================================================");
		System.out.printf("%-7s %-16s   %10s  %10s %10s", "sifra", "brojTelefona", "Odlazeci", "Dolazeci","Trosak");
		System.out.println("\n====  =================  ==========  ==========");
		
		
		
		
		for(int i=1;i<6;i++) {
			int odlazeciBroj=OdlazeciPozivi.get(i);
			int dolazeciBroj=DolazeciPozivi.get(i);
//			int trosakInt=TrosakID.get(i);
			System.out.printf("%-7s %-16s   %5s  %10s %10s \n", nalogStatistika.get(i-1).getSifra(),
					nalogStatistika.get(i-1).getBrojTelefona(), odlazeciBroj, dolazeciBroj,TrosakID.get(i) + " dinara");
//			System.out.println(odlazeciBroj);
		}
		
	}
	
	
	public static void dodajNalog() {
		
			double stanje=0;
			System.out.println("Unesi sifru naloga");
			String sifra=PomocnaKlasa.ocitajTekst();
			System.out.println("Unesi broj telefona");
			int brojTelefona=PomocnaKlasa.ocitajCeoBroj();
			System.out.println("Unesi broj besplatnih minuta");
			int brojMinuta=PomocnaKlasa.ocitajCeoBroj();
			while(brojMinuta<0) {
				System.out.println("Broj besplatnih minuta ne sme biti manji od nula");
				brojMinuta=PomocnaKlasa.ocitajCeoBroj();
			}
			Nalog nalog=new Nalog(sifra,brojTelefona,brojMinuta,stanje);
			NalogDAO.add(ApplicationUI.getConn(), nalog);
			
		
	}
	
	
	
}
