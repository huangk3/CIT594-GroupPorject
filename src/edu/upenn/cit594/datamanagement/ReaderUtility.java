package edu.upenn.cit594.datamanagement;


import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONObject;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;

public class ReaderUtility {
	
	public static Parking readParkingJLine(JSONObject line) {
		
		String violationID = line.get("ticket_number").toString();
		String vehicleID = line.get("plate_id").toString();
		String timestamp = line.get("date").toString();
		String zipcode = line.get("zip_code").toString();
		String description = line.get("violation").toString();
		String fine = line.get("fine").toString();
		String state = line.get("state").toString();
		
		Parking singleParking = new Parking(timestamp, fine, description, 
				vehicleID, state, violationID, zipcode);
		
		
		return singleParking;

		
	}
	
	public static Parking readParkingCLine(String line) {
		
		String[] contents = line.split(",");
		
		
		String timestamp = contents[0];
		String fine = contents[1];
		String description = contents[2];
		String vehicleID = contents[3];
		String state = contents[4];
		String violationID = contents[5];
		String zipcode = contents[6];
		
		Parking singleParking = new Parking(timestamp, fine, description, 
				vehicleID, state, violationID, zipcode);
		
		
		return singleParking;
		
		
	}
	
	public static Population readPopulationLine(String line) {
		
		String[] contents = line.split(" ");
		
		String zipcode = contents[0];
		
		int populationSize = Integer.parseInt(contents[1]);
		
		Population singlePopulation = new Population(zipcode, populationSize);
		
		
		return singlePopulation;
	}
	
	
	public static HashMap<String, Integer> findHeader(String line) {
		
		String[] contents = line.split(",");
		
		HashMap<String,Integer> HeaderFound = new HashMap<String,Integer>();
		
		String[] Header = {"total_livable_area","market_value","building_code","zip_code"};
		
		for(int i = 0 ; i <contents.length; i++) {
			if(Arrays.asList(Header).contains(contents[i])) {
				
				HeaderFound.put(contents[i], i);
				
			}
		}
		
		return HeaderFound;
		
	}
	
	
	
	public static Property readPropertyLine(String line, HashMap<String, Integer> header) {
		
		
		
		return null;
	}

}
