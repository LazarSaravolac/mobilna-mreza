package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Nalog;
import model.NalogStatistika;
import model.Poziv;
import model.Trosak;

public class NalogDAO {

	public static List<NalogStatistika> getNalogStatistika(Connection conn){
	 List<NalogStatistika> nalogS=new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {String query="SELECT sifra,brojTelefona FROM Nalog";
		stmt=conn.createStatement();
		rset=stmt.executeQuery(query);
		while(rset.next()) {
			String sifra=rset.getString(1);
			int brojTelefona=rset.getInt(2);
			NalogStatistika n= new NalogStatistika(sifra,brojTelefona);
			nalogS.add(n);
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
	 
	 
	 return nalogS;
	}
	
	
	
	
	public static List<Nalog>getAll(Connection conn){
		List<Nalog>nalozi=new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query="SELECT * FROM nalog";
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			while(rset.next()) {
				int index=1;
				int id=rset.getInt(index++);
				String sifra=rset.getString(index++);
				int brojTelefona=rset.getInt(index++);
				int brojMinuta=rset.getInt(index++);
				double stanje=rset.getDouble(index++);
				Nalog nalog=new Nalog(id,sifra,brojTelefona,brojMinuta,stanje);
				List<Poziv>poziviPrimaoc=PozivDAO.getAllByNalogPrimaoc(conn, nalog);
				List<Poziv>poziviPozivaoca=PozivDAO.getAllByNalogPozivaoc(conn, nalog);
				nalog.getListaPozivaPozivaoca().addAll(poziviPozivaoca);
				nalog.getListaPozivaPrimaoca().addAll(poziviPrimaoc);
				nalozi.add(nalog);
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
		return nalozi;
	}
	
	public static boolean updateBrojMinuta(Connection conn,Nalog nalog) {
		PreparedStatement pstmt=null;
		try {
			String query="UPDATE nalog SET brojMinuta = ? WHERE id = ?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, nalog.getBrojMinuta());
			pstmt.setInt(2, nalog.getId());
			return pstmt.executeUpdate()==1;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean updateStanje(Connection conn,Nalog nalog) {
		PreparedStatement pstmt=null;
		try {
			String query="Update nalog SET stanje=? WHERE id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setDouble(1, nalog.getStanje());
			pstmt.setInt(2, nalog.getId());
			return pstmt.executeUpdate()==1;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	public static List<Trosak> getTrosak(Connection conn,Date pocetak,Date kraj){
		List<Trosak> trosak=new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {String query="SELECT pocetak,kraj,pozivaoc_id FROM poziv WHERE pocetak >?" + " AND kraj <?";
		stmt=conn.prepareStatement(query);
		stmt.setDate(1, pocetak);
		stmt.setDate(2, kraj);
		rset=stmt.executeQuery();
		while(rset.next()) {
			Timestamp pocetak1=rset.getTimestamp(1);
			Timestamp kraj1=rset.getTimestamp(2);
			int pozivaoc=rset.getInt(3);
			Trosak tr=new Trosak(pocetak1,kraj1,pozivaoc);
			trosak.add(tr);
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
		return trosak;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<Integer> getDolazeciPozivi(Connection conn,Date pocetak,Date kraj){
		List<Integer>dolazeci=new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String query="SELECT primaoc_id FROM poziv WHERE pocetak >?" + " AND kraj <?";
			stmt=conn.prepareStatement(query);
			stmt.setDate(1, pocetak);
			stmt.setDate(2, kraj);
			rset=stmt.executeQuery();
			while(rset.next()) {
				int broj=rset.getInt(1);
				dolazeci.add(broj);
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
		return dolazeci;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<Integer> getOdlazeciPozivi(Connection conn,Date pocetak,Date kraj){
		List<Integer>odlazeci=new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String query="SELECT pozivaoc_id FROM poziv WHERE pocetak >?" + " AND kraj <?";
			stmt=conn.prepareStatement(query);
			stmt.setDate(1, pocetak);
			stmt.setDate(2, kraj);
			rset=stmt.executeQuery();
			while(rset.next()) {
				int broj=rset.getInt(1);
				odlazeci.add(broj);
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
		return odlazeci;
	}
	
	
	public static Nalog getNalogByID(Connection conn,int id) {
		Statement stmt=null;
		ResultSet rset=null;
		Nalog nalog=null;
		try {
			String query="SELECT sifra,brojTelefona,brojMinuta,stanje FROM nalog WHERE id = " + id;
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			if(rset.next()) {
				int index=1;
				String sifra=rset.getString(index++);
				int brojTelefona=rset.getInt(index++);
				int brojMinuta=rset.getInt(index++);
				double stanje=rset.getDouble(index++);
				nalog=new Nalog(id,sifra,brojTelefona,brojMinuta,stanje);
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
		return nalog;
	}
	
	public static boolean add(Connection conn,Nalog nalog) {
		PreparedStatement pstmt=null;
		try {
			String query="INSERT INTO nalog(sifra,brojTelefona,brojMinuta,stanje)VALUES(?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			int index=1;
			pstmt.setString(index++, nalog.getSifra());
			pstmt.setInt(index++, nalog.getBrojTelefona());
			pstmt.setInt(index++, nalog.getBrojMinuta());
			pstmt.setDouble(index++, nalog.getStanje());
			return pstmt.executeUpdate()==1;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
