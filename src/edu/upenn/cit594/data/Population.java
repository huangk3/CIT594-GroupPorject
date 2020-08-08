package edu.upenn.cit594.data;

public class Population extends SingleData{
	private int population;
	
	public Population(String zipcodeIn,int populationIn) {
		
		super(zipcodeIn);
		this.population = populationIn;
	}

	public int getPopulation() {
		return population;
	}
	
	
}
