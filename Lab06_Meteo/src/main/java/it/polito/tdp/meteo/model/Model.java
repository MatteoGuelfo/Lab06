package it.polito.tdp.meteo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	private final static int nGiorni = 3;
	private List<Float> costi;
	private List<List<String>> sequenze;
	
	private MeteoDAO infoMeteo;

	public Model() {
		infoMeteo = new MeteoDAO();
		costi=new LinkedList<>();
		sequenze = new LinkedList<>();

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
		
		List<String> rit = new LinkedList<String>();
		String ritorno= "";
		
		int indice=0;
		
		if(mese>=10)
			calcolaSequenzaErrata(mese,0,rit,0.0f, LocalDate.parse("2013-"+mese+"-01"), infoMeteo.getAllLocalita());
		if(mese<10)
			calcolaSequenzaErrata(mese,0,rit,0.0f, LocalDate.parse("2013-0"+mese+"-01"), infoMeteo.getAllLocalita());
		
		float best =costi.get(0);
		
		for(Float f: costi) {
			if(f<best) {
				best=f;
				System.out.println(best);
				indice= costi.indexOf(f);
			}
		}
		
		for(String s:sequenze.get(indice)) {
			ritorno +=" "+s;
			
		}
		//System.out.println(rit);
		return ritorno;
	}
	
	private void calcolaSequenzaErrata(int mese, int livello, List<String> sequenza, float costo, LocalDate oggi, List<String> localita) {
		float costoSeq = costo +COST*livello; 
		LocalDate daData = oggi;
		
		if(livello==3) {
			costi.add(costo);
			sequenze.add(new LinkedList(sequenza));
			System.out.println(sequenza+ " con costo "+costo+" oggi è "+ oggi);
			return;
		}
		
		for(String l: localita ) {
			/*for(int cont=0; cont < 3; cont++) {
				Rilevamento ril= (Rilevamento) infoMeteo.getAllRilevamentiLocalitaMese(mese, l).toArray()[cont++];
				costoSeq +=ril.getUmidita();
				oggi= oggi.plusDays(1);
			}*/
			List <Rilevamento> rilList= new LinkedList<>( infoMeteo.getAllRilevamentiLocalitaMese(mese, l));
			while(oggi.isBefore(daData.plusDays(3))) {
				System.out.println(oggi+" ha un umidità di: "+ rilList.get(oggi.getDayOfMonth()).getUmidita());
				costoSeq +=rilList.get(oggi.getDayOfMonth()).getUmidita();
				oggi = oggi.plusDays(1);
				
			}
			
			sequenza.add(l);			
			List<String> nLoc = new LinkedList<>(localita);
			nLoc.remove(l);
			
			System.out.println(costoSeq);
			//System.out.println("livello: "+livello);
			System.out.println(sequenza+" "+ oggi+" "+costoSeq+ " umidita puntuale "+ livello);
			calcolaSequenzaErrata(mese,++livello,sequenza,costoSeq, oggi , nLoc ); 
			
			livello--;
			oggi= daData; 
			sequenza.remove(l);
		}
	}

	public void calcolaSequenza(List<String> localita, List<String> sequenza, int livello, int rip) {
		float costoParziale;
		
		if(livello==3 && sequenza.size() == 15 ) {
			System.out.println(sequenza+"\n");
			return;
		}
		
		for(String l : localita) {
			for(int j= rip ; j > 2 ; j--) {
				for(int i= 0 ; i < j ; i++) {
					sequenza.add(l);				
				}
				List<String> list = new LinkedList<>(localita);
				list.remove(l);	

				calcolaSequenza(list,sequenza, ++livello, rip);	
				
				livello--;
				for(int i= 0 ; i < rip ; i++) {
					sequenza.remove(l);
				}
				
			}
			
			}
		}
	
	public float costoTot(List<String> combo) {
		float costo= 0.0f; 
		
		
		
		
		return costo;
	}
	
	

}
