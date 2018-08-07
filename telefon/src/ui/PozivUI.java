package ui;

import java.sql.Date;
import java.sql.Timestamp;

import dao.NalogDAO;
import dao.PozivDAO;
import model.Nalog;
import model.Poziv;
import utils.PomocnaKlasa;

public class PozivUI {

	
	
	public static void dodajPoziv() {
		System.out.println("Unesi sifru primaoca");
		int sifraPrimaoca=PomocnaKlasa.ocitajCeoBroj();
		Nalog primaoc=NalogDAO.getNalogByID(ApplicationUI.getConn(), sifraPrimaoca);
		System.out.println("Unesi sifru pozivaoca");
		int sifraPozivaoca= PomocnaKlasa.ocitajCeoBroj();
		Nalog pozivaoc=NalogDAO.getNalogByID(ApplicationUI.getConn(), sifraPozivaoca);
		System.out.println("unesi pocetak ");
		Timestamp pocetak=PomocnaKlasa.ocitajDatum();
		System.out.println("unesi kraj ");
		Timestamp kraj=PomocnaKlasa.ocitajDatum();
		long poc=pocetak.getTime();
		long kr=kraj.getTime();
		long razlika=kr-poc;
		int razlikaInt=(int)(razlika/(1000*60));
		System.out.println("razlika " + razlika);
		System.out.println("razlikaInt " + razlikaInt);
		pozivaoc.setBrojMinuta(pozivaoc.getBrojMinuta()-razlikaInt);
		NalogDAO.updateBrojMinuta(ApplicationUI.getConn(), pozivaoc);
		
		if(pozivaoc.getBrojMinuta()<0) {
			pozivaoc.setStanje(pozivaoc.getStanje()-9*razlikaInt);
			NalogDAO.updateStanje(ApplicationUI.getConn(), pozivaoc);
			pozivaoc.setBrojMinuta(0);
			NalogDAO.updateBrojMinuta(ApplicationUI.getConn(), pozivaoc);
		}
		Poziv poziv=new Poziv(0,pocetak,kraj,pozivaoc,primaoc);
		PozivDAO.add(ApplicationUI.getConn(), poziv);
				
	}
}
