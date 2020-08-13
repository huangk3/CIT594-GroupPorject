package edu.upenn.cit594.processor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

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
	private HashMap<String, Integer> meanPropertyValue = new HashMap<String, Integer>();
	private HashMap<String, Integer> meanLivableArea = new HashMap<String, Integer>();
	private HashMap<String, Integer> residentialValuePerCapita = new HashMap<String, Integer>();
	private Map<String , Double> rankedParkingViolations = new TreeMap<String , Double>();
	
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
			int averageMarketValue = 0;
			zipCode = InterfaceUtility.askCode();
			if (meanPropertyValue.containsKey(zipCode))  {
				averageMarketValue = meanPropertyValue.get(zipCode);
			} else {
				averageByMarketValue averageByMarketValue = new averageByMarketValue();
				properties = fullProperty.get(zipCode);
				averageMarketValue = average(properties, averageByMarketValue);
				meanPropertyValue.put(zipCode, averageMarketValue);
			}

            System.out.println("The avaerage property market value for " + zipCode + " is $" + averageMarketValue);
		    break;
		  
		  case 4:
			//call UI to present an userInput
			int averageLivableArea = 0;
			zipCode = InterfaceUtility.askCode();
			if (meanLivableArea.containsKey(zipCode)) {
				averageLivableArea = meanLivableArea.get(zipCode);
			} else {
				averageByTotalLivableArea averageByTotalLivableArea = new averageByTotalLivableArea();
				properties = fullProperty.get(zipCode);
				averageLivableArea = average(properties, averageByTotalLivableArea);
				meanLivableArea.put(zipCode, averageLivableArea);
			}
            System.out.println("The avaerage livable area for " + zipCode + " is " + averageLivableArea +" sqft");
		    break;
		  
		  case 5:
			int totalResidentialMarketValuePerCapita;
			zipCode = InterfaceUtility.askCode();
			if (residentialValuePerCapita.containsKey(zipCode)){
				totalResidentialMarketValuePerCapita =  residentialValuePerCapita.get(zipCode);
			} else {
				properties = fullProperty.get(zipCode);
				totalResidentialMarketValuePerCapita = totalResidentialMarketValuePerCapita(properties,zipCode);
				residentialValuePerCapita.put(zipCode, totalResidentialMarketValuePerCapita);
			}
            System.out.println("The total residential market value per capita in " + zipCode + " is $" + totalResidentialMarketValuePerCapita);
		    break;
		  case 6:
			Map<String , Double> rankedParkingViolations = rankParkingViolationPer10kPeople();
			HashMap<String , Integer> averageMarketVal = averageMarketValueByZipcode();
			printRankOfParkingViolationPer10kPeople(rankedParkingViolations, averageMarketVal);
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
				if ( marketValue!= null && marketValue > 0 ) {
					totalResidentialMarketValue += marketValue;
				} 
			}
		}			
		return (int) Math.floor(totalResidentialMarketValue / population) ;
	}
	
	//step 6: returns the rank of number of parking violation per 100 people for all zipcodes and average market value next to it.	

	public void printRankOfParkingViolationPer10kPeople (Map<String , Double> rankedParkingViolation, HashMap<String , Integer> averageMarketValueByZipcode) {
		if (rankedParkingViolation == null || averageMarketValueByZipcode == null 
				|| rankedParkingViolation.isEmpty() || averageMarketValueByZipcode.isEmpty()) {
			System.out.println("The input parameter(s) are invalid or empty!");			
		} else {
			System.out.printf("%8s\t%40s\t%10s\t%30s\n", "Zipcode", "ParkingViolationCountPer10kPeople", "Population", "AveragePropertyMarketValue");

			Set<Entry<String, Double>> set = rankedParkingViolation.entrySet();
			Iterator<Entry<String, Double>> it = set.iterator();
			while(it.hasNext()) {
				Entry<String, Double> me = (Entry<String, Double>)it.next();
				String s = me.getKey();
				System.out.printf("%8s\t%40.2f\t%10d\t%30d\n", s, me.getValue(), populationByZipcode.get(s), averageMarketValueByZipcode.get(s));
			}
		}	
	}
	
	public Map<String , Double> rankParkingViolationPer10kPeople () {
		//check if rankedParkingViolations had already been calculated.
		if (rankedParkingViolations.isEmpty()) {
			Map<String , Double> parkingViolationPer10kPeople = new TreeMap<String , Double>();
			//get the total number of parking violations for each zipcode;
			for (String zipcode1 : fullParking.keySet()) {
				//the zipcode must be in the population.txt
				if (populationByZipcode.containsKey(zipcode1)) {
					int population = populationByZipcode.get(zipcode1);
					//ignore zipcode with population of 0;
					if ( population > 0 ) {
						int parkingViolations = fullParking.get(zipcode1).size();
//						System.out.println("vialation count for " + zipcode1 + " is " + parkingViolations);
						BigDecimal numberOfParkViolations = new BigDecimal(String.valueOf((parkingViolations * 10000.0) / population)).setScale(2, BigDecimal.ROUND_FLOOR);
//						System.out.println("vialation count per 10k for " + zipcode1 + " is " + numberOfParkViolations.doubleValue());
						parkingViolationPer10kPeople.put(zipcode1, numberOfParkViolations.doubleValue());
					}
				}
			}
			//sort the parkingViolationPer100People by value (number of parking violation per 10k people);
			rankedParkingViolations = sortByValues(parkingViolationPer10kPeople);
		}
		return rankedParkingViolations;
	}
	
	//component function for step 6. Return Hashmap which stores the average market value for each zipcode;
	public HashMap<String , Integer> averageMarketValueByZipcode () {
//		HashMap<String , Integer> averageMarketValue = new HashMap<String , Integer>();
//		HashMap<String , Double> totalMarketValue = new HashMap<String , Double>();
//		HashMap<String , Integer> homeCount = new HashMap<String , Integer>();
		
		for (String zipcode : fullProperty.keySet()) {
			if (meanPropertyValue.containsKey(zipcode) == false) {
				averageByMarketValue averageByMarketValue = new averageByMarketValue();
				int averageMarketValue = average(fullProperty.get(zipcode), averageByMarketValue);
				meanPropertyValue.put(zipcode, averageMarketValue);
			} 
		}
//			else {
//				LinkedList<SingleData> propertiesSubsettedByZipcode = fullProperty.get(zipcode);
//				if (propertiesSubsettedByZipcode.peek() instanceof Property )	{
//					ListIterator<SingleData> listIterator = propertiesSubsettedByZipcode.listIterator();
//					while (listIterator.hasNext()) { 
//						Property p = (Property) listIterator.next();
//						Double value = p.getMarketValue();
//						if ( value != null && value > 0) {
//							if (totalMarketValue.containsKey(zipcode)) {
//								totalMarketValue.put(zipcode, totalMarketValue.get(zipcode)+value);
//								homeCount.put(zipcode, homeCount.get(zipcode) + 1);
//							} else {
//								totalMarketValue.put(zipcode, value);
//								homeCount.put(zipcode, 1);
//							}
//
//						}
//					}
//				}
//			}			

		
//		for (String z : totalMarketValue.keySet()) {
//			averageMarketValue.put(z, (int) Math.floor(totalMarketValue.get(z) / homeCount.get(z)));		
//		}		
		return meanPropertyValue;
	}
	
	// sort (descending) the treemap by value instead of key  
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
		Comparator<K> valueComparator = new Comparator<K>() {
			public int compare(K k1, K k2) {
				int compare = map.get(k2).compareTo(map.get(k1));
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
