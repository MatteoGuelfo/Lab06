package it.polito.tdp.meteo.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	private int nGiorni; 
	
	private MeteoDAO infoMeteo;

	public Model() {
		infoMeteo = new MeteoDAO();
		

	}

	// of course you can change the String output with what you think works best
	public String getUmiditaMedia(int mese) {
		StringBuilder sb = new StringBuilder();
		Map<String,Float> mappa = new TreeMap<>(infoMeteo.getUmiditaMediaLocalitaMese(mese));
		for(String s :mappa.keySet()) {
			sb.append(String.format("%-20s", s)); 
			sb.append(String.format("%-2f\n", mappa.get(s)));
		}
			
			
		
		return sb.toString();
	}
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		return "TODO!";
	}
	
	private List<String> calcolaSequenza(int mese, int livello, List<String> sequenza, float costo ) {
		float costoSeq; 
	
		//List
		
		return null;
	}
	

}
