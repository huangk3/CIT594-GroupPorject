package edu.upenn.cit594.data;

public class Population {
	private String zipcode;
	private int population;
	
	public Population (String zipcode, int population) {
		this.zipcode = zipcode;
		this.population = population;
	}
	
	public String getZipcode() {
		return zipcode;
	}

	public int getPopulation() {
		return population;
	}
	
	
}
