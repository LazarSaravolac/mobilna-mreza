package dao;

import model.Nalog;
import model.Poziv;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PozivDAO {

	
	public static boolean add(Connection conn,Poziv poziv) {
		PreparedStatement pstmt=null;
		try {
			String query="INSERT INTO poziv(pocetak,kraj,pozivaoc_id,primaoc_id) VALUES(?,?,?,?) ";
			pstmt=conn.prepareStatement(query);
			pstmt.setTimestamp(1, poziv.getPocetak());
			pstmt.setTimestamp(2, poziv.getKraj());
			pstmt.setInt(3, poziv.getPozivaoc().getId());
			pstmt.setInt(4, poziv.getPrimaoc().getId());
			return pstmt.executeUpdate()==1;
		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	
	public static Poziv get (Connection conn,Date pocetak,Date kraj) {
	
		Poziv poziv=null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query="SELECT n.sifra,n.brojTelefona ,sum(primaoc_id),sum(pozivaoc_id) FROM nalog n " +
					"LEFT JOIN poziv p ON n.id=p.primaoc_id " + 
					"Group by n.id " + 
					"WHERE pocetak>=? " + " AND WHERE kraj<=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, pocetak);
			pstmt.setDate(2, kraj);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String sifra=rset.getString(1);
				int brojT=rset.getInt(2);
				int sumaPrimaoca=rset.getInt(3);
				int sumaPozivaoca=rset.getInt(4);
				
			}
		

		}catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return poziv;
	}
	
	
	
	
	
	
	
	
	public static List<Poziv>getAllByNalogPrimaoc(Connection conn,Nalog primaoc){
		List<Poziv>pozivi=new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			 String query="SELECT id,pocetak,kraj,pozivaoc_id FROM poziv WHERE primaoc_id = " + primaoc.getId();
			 stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
				
			while (rset.next()) {
				int index = 1;
				int id=rset.getInt(index++);
				Timestamp pocetak=rset.getTimestamp(index++);
				Timestamp kraj=rset.getTimestamp(index++);
				int pozivaoc_id=rset.getInt(index++);
				Nalog pozivaoc=NalogDAO.getNalogByID(conn, pozivaoc_id);
				Poziv poziv=new Poziv(id,pocetak,kraj,pozivaoc,primaoc);
				pozivi.add(poziv);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return pozivi;
	}
	
	public static List<Poziv>getAllByNalogPozivaoc(Connection conn,Nalog pozivaoc){
		List<Poziv>pozivi=new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			 String query="SELECT id,pocetak,kraj,primaoc_id FROM poziv WHERE pozivaoc_id = " + pozivaoc.getId();
			 stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
				
			while (rset.next()) {
				int index = 1;
				int id=rset.getInt(index++);
				Timestamp pocetak=rset.getTimestamp(index++);
				Timestamp kraj=rset.getTimestamp(index++);
				int pozivaoc_id=rset.getInt(index++);
				Nalog primaoc=NalogDAO.getNalogByID(conn, pozivaoc_id);
				Poziv poziv=new Poziv(id,pocetak,kraj,pozivaoc,primaoc);
				pozivi.add(poziv);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return pozivi;
	}
	
	
	public static Poziv getPozivByID(Connection conn,int id) {
		Statement stmt=null;
		ResultSet rset=null;
		Poziv poziv=null;
		try {
			String query="SELECT pocetak,kraj,pozivaoc_id,primaoc_id FROM poziv WHERE id = " + id;
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			if(rset.next()) {
				int index=1;
				Timestamp pocetak=rset.getTimestamp(index++);
				Timestamp kraj=rset.getTimestamp(index++);
				int pozivaoc_id=rset.getInt(index++);
				int primaoc_id=rset.getInt(index++);
				Nalog pozivaoc=NalogDAO.getNalogByID(conn, pozivaoc_id);
				Nalog primaoc=NalogDAO.getNalogByID(conn, primaoc_id);
				poziv=new Poziv(id,pocetak,kraj,pozivaoc,primaoc);
				
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return poziv;
	}
}
