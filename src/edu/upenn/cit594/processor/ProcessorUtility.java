package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
//import java.util.ListIterator;
import java.util.TreeMap;

import edu.upenn.cit594.data.Parking;
//import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.SingleData;

public class ProcessorUtility {

	public static void TotalPopulation(HashMap<String, Integer> populationByZipcode) {
		Integer totalPop = 0;
		
		for(Map.Entry<String,Integer> entry : populationByZipcode.entrySet()) {
    		
				//Map.Entry<String, float[]> pair = (Map.Entry<String, float[]>) it.next();
    	    
				//System.out.println(entry.getValue()[0]+ " , " + entry.getValue()[1]);
			    totalPop = totalPop + entry.getValue();
    		
				//System.out.println("Distance: " + tempDist);
    
    			
    		}
		
		System.out.println("The total population is" + totalPop);
    	
    	}
		


	public static void TotalFinesPerCapita(TreeMap<String, LinkedList<SingleData>> fullParking, HashMap<String, Integer> populationByZipcode) {
		
		
		LinkedList<SingleData> parkings;
		for(Map.Entry<String,LinkedList<SingleData>> entry : fullParking.entrySet()) {
			
			Double totalFine = 0.0;
			parkings = entry.getValue();
			for (Iterator<SingleData> i = parkings.iterator(); i.hasNext();) {
				
				 Parking onePark = (Parking)i.next();
				 totalFine = totalFine + onePark.getFine();
		      }
			String code = entry.getKey();
			double finePerCapita = totalFine/populationByZipcode.get(code);
			System.out.println(code + " " + finePerCapita);
		}
		
	}
}
	
	
	