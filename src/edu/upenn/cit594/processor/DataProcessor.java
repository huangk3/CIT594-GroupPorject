package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.data.Property;

public class DataProcessor {
	private HashMap<String, Integer> populationByZipcode = new HashMap<String, Integer> ();
	
	public ArrayList<Property> filterPropertyByZipcode (ArrayList<Property> properties, String zipcode) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>() ;
		for (Property p : properties) {
			if (p.getZipcode().equals(zipcode)) {
				filteredProperties.add(p);
			}
		}
		
		return filteredProperties;
	}
	
	public Double average (ArrayList<Property> properties, AverageCalculator averageCalculator) {
		return averageCalculator.calculateAverage(properties);
	}

	public int totalResidentialMarketValuePerCapita (ArrayList<Property> properties, String zipcode) {
		if (populationByZipcode.containsKey(zipcode) == false || populationByZipcode.get(zipcode) == 0 
			|| properties == null || properties.isEmpty() ) return 0;
		int population = populationByZipcode.get(zipcode);
		Double totalResidentialMarketValue = 0.0;
		for (Property p : properties) {
			totalResidentialMarketValue += p.getMarketValue();
		}
		return (int) Math.round(totalResidentialMarketValue / population) ;
	}
	
	public HashMap<String, Integer> getPopulationByZipcode() {
		return populationByZipcode;
	}

	public void setPopulationByZipcode(HashMap<String, Integer> populationByZipcode) {
		this.populationByZipcode = populationByZipcode;
	}
	
	
}
