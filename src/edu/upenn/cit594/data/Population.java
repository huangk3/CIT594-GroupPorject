package edu.upenn.cit594.data;

import java.util.HashMap;

public class Population {
    private HashMap<String, Integer> populationByZipcode;
	
	public Population () {
		populationByZipcode = new HashMap<String, Integer> ();
	}
	
	public void add (String zipcode, int population) {
		populationByZipcode.put(zipcode, population);
	}
	
	public int getPopulation(String zipcode) {
		return populationByZipcode.get(zipcode);
	}
	
	public HashMap<String, Integer> getPopulation() {
		return populationByZipcode;
	}
	
	
}
