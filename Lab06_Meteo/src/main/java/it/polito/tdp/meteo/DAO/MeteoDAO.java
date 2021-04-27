package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data").toLocalDate(), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
		
		final String sql= "SELECT localita,data, umidita FROM situazione WHERE localita= ? and MONTH(data) = ?";
		
		List<Rilevamento> rilevamenti = new LinkedList<Rilevamento>(); 

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, localita);
			st.setInt(2, mese);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				

				Rilevamento r = new Rilevamento(rs.getString("Localita"),rs.getDate("Data").toLocalDate(), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public List<String> getAllLocalita() {
		
		final String sql= "SELECT DISTINCT localita FROM situazione";
		
		List<String> localita = new LinkedList<>(); 

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				localita.add(rs.getString("Localita"));
			}

			conn.close();
			return localita;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Map<String,Float> getUmiditaMediaLocalitaMese(int mese){
		
		final String sql = "SELECT localita, AVG(umidita) AS umiditaMed FROM situazione WHERE MONTH(data) = ? GROUP BY localita";
				
		Map<String, Float> mappa = new  TreeMap<>();
				
		try {
			Connection conn= ConnectDB.getConnection(); 
			PreparedStatement st= conn.prepareStatement(sql);
			
			st.setInt(1,mese);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
					mappa.put(rs.getString("localita"),rs.getFloat("umiditaMed"));
				}
				
			conn.close();
			return mappa;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}


