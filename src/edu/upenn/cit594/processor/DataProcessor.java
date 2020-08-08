package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Property;
import java.math.BigDecimal;
public class DataProcessor {
	private HashMap<String, Integer> populationByZipcode;
	public DataProcessor (HashMap<String, Integer> populationByZipcode) {
		this.populationByZipcode = populationByZipcode;
	}
	
	public ArrayList<Property> filterPropertyByZipcode (ArrayList<Property> properties, String zipcode) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>() ;
		for (Property p : properties) {
			if (p.getZipcode().equals(zipcode)) {
				filteredProperties.add(p);
			}
		}
		
		return filteredProperties;
	}
	
	public int average (ArrayList<Property> properties, AverageCalculator averageCalculator) {
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
		return (int) Math.floor(totalResidentialMarketValue / population) ;
	}
	
	//step 6: returns the rank of number of parking violation per 100 people with the zipcode and average market value next to it.
	
	public TreeMap<String , BigDecimal> rankParkingViolationPer100People (ArrayList<Parking> parkingViolations) {
		TreeMap<String , BigDecimal> parkingViolationPer100People = new TreeMap<String , BigDecimal>();
		HashMap<String , Integer> parkingViolationCount = new HashMap<String , Integer>();
		
		for (Parking p : parkingViolations) {
			String zipCode = p.getZipcode(); 
			if ( parkingViolationCount.containsKey(zipCode) ) {
				parkingViolationCount.put(zipCode, parkingViolationCount.get(zipCode)+1);
			} else {
				parkingViolationCount.put(zipCode, 1);
			}
		}
		
		for (String z : parkingViolationCount.keySet()) {
			if (populationByZipcode.containsKey(z)) {
				int population = populationByZipcode.get(z);
				if ( population > 0 ) {
					BigDecimal numberOfParkViolations = new BigDecimal(String.valueOf((parkingViolationCount.get(z) * 100.0) / population)).setScale(2, BigDecimal.ROUND_FLOOR);
					parkingViolationPer100People.put(z, numberOfParkViolations);
				}
			}
			
		}
		
		return parkingViolationPer100People;
	}
	
	//return Hashmap which stores the average market value for each zipcode;
	public HashMap<String , Integer> averageMarketValueByZipcode (ArrayList<Property> properties) {
		HashMap<String , Integer> averageMarketValue = new HashMap<String , Integer>();
		HashMap<String , Double> totalMarketValue = new HashMap<String , Double>();
		HashMap<String , Integer> homeCount = new HashMap<String , Integer>();
		for (Property p : properties) {
			String zipCode = p.getZipcode();
			if (zipCode != null && zipCode.isEmpty() == false && p.getMarketValue() > 0) {
				if ( totalMarketValue.containsKey(zipCode) ) {
					totalMarketValue.put(zipCode, totalMarketValue.get(zipCode)+p.getMarketValue());
					homeCount.put(zipCode, homeCount.get(zipCode) + 1);
				} else {
					totalMarketValue.put(zipCode, p.getMarketValue());
					homeCount.put(zipCode, 1);
				}
			}
		}
		
		for (String z : totalMarketValue.keySet()) {
			if (populationByZipcode.containsKey(z) && homeCount.get(z) > 0) {
				averageMarketValue.put(z, (int) Math.floor(totalMarketValue.get(z) / homeCount.get(z)));
			}			
		}		
		return averageMarketValue;
	}
	
	
}


