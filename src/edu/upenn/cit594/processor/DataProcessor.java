package edu.upenn.cit594.processor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.SingleData;

import java.math.BigDecimal;
public class DataProcessor {
	private HashMap<String, Integer> populationByZipcode;
	public DataProcessor (HashMap<String, Integer> populationByZipcode) {
		this.populationByZipcode = populationByZipcode;
	}
	
	//performs tasks 3 & 4;
	public int average (LinkedList<SingleData> properties, AverageCalculator averageCalculator) {
		return averageCalculator.calculateAverage(properties);
	}
    //performs task 5;
	public int totalResidentialMarketValuePerCapita (LinkedList<SingleData> properties, String zipcode) {
		if (populationByZipcode.containsKey(zipcode) == false || properties == null || properties.isEmpty() ) return 0;
		int population = populationByZipcode.get(zipcode);
		if (population == 0) return 0;				
		Double totalResidentialMarketValue = 0.0;						

		if (properties.peek() instanceof Property )	{
			ListIterator<SingleData> listIterator = properties.listIterator();
			while (listIterator.hasNext()) {
				Property s = (Property) listIterator.next();
				Double marketValue = s.getMarketValue();
				if ( marketValue > 0 ) {
					totalResidentialMarketValue += marketValue;
				} 
			}
		}			
		return (int) Math.floor(totalResidentialMarketValue / population) ;
	}
	
	//step 6: returns the rank of number of parking violation per 100 people for all zipcodes and average market value next to it.	
	public Map<String , BigDecimal> rankParkingViolationPer100People (TreeMap<String, LinkedList<SingleData>> parkingViolations) {
		TreeMap<String , BigDecimal> parkingViolationPer100People = new TreeMap<String , BigDecimal>();
		HashMap<String , Integer> parkingViolationCount = new HashMap<String , Integer>();
		//get the total number of parking violations for each zipcode;
		for (String zipcode1 : parkingViolations.keySet()) {
			//the zipcode must be in the population.txt
			if (populationByZipcode.containsKey(zipcode1)) {
				parkingViolationCount.put(zipcode1, parkingViolations.get(zipcode1).size());				
			}						
		}
		
		for (String zipcode2 : parkingViolationCount.keySet()) {
			int population = populationByZipcode.get(zipcode2);
			//ignore the zipcodes whose population equal to 0;
			if ( population > 0 ) {
				BigDecimal numberOfParkViolations = new BigDecimal(String.valueOf((parkingViolationCount.get(zipcode2) * 100.0) / population)).setScale(2, BigDecimal.ROUND_FLOOR);
				parkingViolationPer100People.put(zipcode2, numberOfParkViolations);
			}
			
		}
		//sort the parkingViolationPer100People by value (number of parking violation per 100 people);
		Map<String , BigDecimal> sortedMap = sortByValues(parkingViolationPer100People);
		return sortedMap;
	}
	
	//component function for step 6. Return Hashmap which stores the average market value for each zipcode;
	public HashMap<String , Integer> averageMarketValueByZipcode (TreeMap<String, LinkedList<SingleData>> properties) {
		HashMap<String , Integer> averageMarketValue = new HashMap<String , Integer>();
		HashMap<String , Double> totalMarketValue = new HashMap<String , Double>();
		HashMap<String , Integer> homeCount = new HashMap<String , Integer>();
		
		for (String zipcode : properties.keySet()) {
			LinkedList<SingleData> propertiesSubsettedByZipcode = properties.get(zipcode);
			if (propertiesSubsettedByZipcode.peek() instanceof Property )	{
				ListIterator<SingleData> listIterator = propertiesSubsettedByZipcode.listIterator();
				while (listIterator.hasNext()) { 
					Property p = (Property) listIterator.next();
					Double value = p.getMarketValue();
					if ( value > 0) {
						if (totalMarketValue.containsKey(zipcode)) {
							totalMarketValue.put(zipcode, totalMarketValue.get(zipcode)+value);
							homeCount.put(zipcode, homeCount.get(zipcode) + 1);
						} else {
							totalMarketValue.put(zipcode, value);
							homeCount.put(zipcode, 1);
						}

					}
				}
			}
			
		}
		
		for (String z : totalMarketValue.keySet()) {
			averageMarketValue.put(z, (int) Math.floor(totalMarketValue.get(z) / homeCount.get(z)));		
		}		
		return averageMarketValue;
	}
	
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
		Comparator<K> valueComparator = new Comparator<K>() {
			public int compare(K k1, K k2) {
				int compare = map.get(k1).compareTo(map.get(k2));
				if (compare == 0) 
					return 1;
				else 
					return compare;
			}
	    };
	 
	    Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
	    sortedByValues.putAll(map);
	    return sortedByValues;
	  }
}


