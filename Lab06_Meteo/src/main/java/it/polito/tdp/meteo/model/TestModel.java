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
		
		
		m.calcolaSequenza(lista, new LinkedList<String>(), 0, 6);	
//		LocalDate dataDate=  LocalDate.of(2021,02,01);
//		
//		LocalDate newdate = dataDate.plusDays(1);
//		
//		System.out.println(newdate);
	
		

	}
	

}
