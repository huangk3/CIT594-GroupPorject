package edu.upenn.cit594.processor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.SingleData;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.ui.InterfaceUtility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
public abstract class DataProcessor {
	protected Reader reader;
	private HashMap<String, Integer> populationByZipcode;
	private TreeMap<String, LinkedList<SingleData>> fullProperty;
	private TreeMap<String, LinkedList<SingleData>> fullParking;
	
	//store answer for reducing calculation
	private int totalPopulation = 0;
	private TreeMap<String,Double> FinesPerCapita;
	
	
	//setting up the processor by reading population and property
	public DataProcessor(String propertyPath, String populationPath) throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		
		PopulationReader populationRd = new PopulationReader();
		populationByZipcode = populationRd.read(populationPath).getPopulation();
		PropertyReader propertyRd = new PropertyReader();		
		fullProperty = propertyRd.read(propertyPath).getData();	
		//create reader
		reader = createReader();
		
	}
	
	
	protected abstract Reader createReader();
	
	
	
	public void readParking(String ParkingPath) throws java.text.ParseException, FileNotFoundException, IOException, ParseException {
		
	
		fullParking = reader.read(ParkingPath).getData();
		
	}
	
	
	
	public void process(int inputNumber) {
		String zipCode;
		LinkedList<SingleData> properties;
		
		
		
		switch (inputNumber) {
		  case 1:
			
			//check if the answer has been calculated
			if (totalPopulation != 0) {
			   System.out.println("The total population is " + totalPopulation);
			}else{
	
		       totalPopulation = ProcessorUtility.TotalPopulation(populationByZipcode);} 
		    break;
		  
		  case 2:
			
			//check if the answer has been calculated
			if (FinesPerCapita != null) {
				ProcessorUtility.TotalFinesPerCapita(FinesPerCapita);
			}
			else {
				FinesPerCapita =ProcessorUtility.TotalFinesPerCapita(fullParking, populationByZipcode);}
		    break;
		  
		  case 3:
			//call UI to present an userInput
			zipCode = InterfaceUtility.askCode();
			averageByMarketValue averageByMarketValue = new averageByMarketValue();
			properties = fullProperty.get(zipCode);
			average(properties, averageByMarketValue);
		    break;
		  
		  case 4:
			//call UI to present an userInput
			zipCode = InterfaceUtility.askCode();
			averageByTotalLivableArea averageByTotalLivableArea = new averageByTotalLivableArea();
			properties = fullProperty.get(zipCode);
			average(properties, averageByTotalLivableArea);
		    break;
		  
		  case 5:
			zipCode = InterfaceUtility.askCode();
			properties = fullProperty.get(zipCode);
			totalResidentialMarketValuePerCapita(properties,zipCode);
		    break;
		  case 6:
			rankParkingViolationPer100People();
		    break;
		}
		
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
	public Map<String , BigDecimal> rankParkingViolationPer100People () {
		TreeMap<String , BigDecimal> parkingViolationPer100People = new TreeMap<String , BigDecimal>();
		HashMap<String , Integer> parkingViolationCount = new HashMap<String , Integer>();
		//get the total number of parking violations for each zipcode;
		for (String zipcode1 : fullParking.keySet()) {
			//the zipcode must be in the population.txt
			if (populationByZipcode.containsKey(zipcode1)) {
				parkingViolationCount.put(zipcode1, fullParking.get(zipcode1).size());				
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
	public HashMap<String , Integer> averageMarketValueByZipcode () {
		HashMap<String , Integer> averageMarketValue = new HashMap<String , Integer>();
		HashMap<String , Double> totalMarketValue = new HashMap<String , Double>();
		HashMap<String , Integer> homeCount = new HashMap<String , Integer>();
		
		for (String zipcode : fullProperty.keySet()) {
			LinkedList<SingleData> propertiesSubsettedByZipcode = fullProperty.get(zipcode);
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