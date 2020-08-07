package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Property;

public class DataProcessor {
	
	
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
	
	
}
