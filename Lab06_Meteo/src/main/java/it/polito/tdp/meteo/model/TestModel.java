package it.polito.tdp.meteo.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		List<String> lista= new LinkedList<>(); 
		lista.add("Roma");
		lista.add("Milano");
		lista.add("Genova");
		
		LocalDate dataDate=  LocalDate.of(2021,03,01);
		
		m.calcolaSequenza(3,0,new LinkedList<String>(),0.0f,dataDate,lista, 6);	
//		
//		
//		LocalDate newdate = dataDate.plusDays(1);
//		
//		System.out.println(newdate);
	
		

	}
	

}
