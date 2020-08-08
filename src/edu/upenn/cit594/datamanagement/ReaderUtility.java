package edu.upenn.cit594.datamanagement;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import org.json.simple.JSONObject;

import edu.upenn.cit594.data.Data;
import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.SingleData;

public class ReaderUtility {
	
	//read the parking json file single line
	public static Parking readParkingJLine(JSONObject line) {
		
		String violationID = line.get("ticket_number").toString();
		String vehicleID = line.get("plate_id").toString();
		String timestamp = line.get("date").toString();
		String zipcode = line.get("zip_code").toString();
		String description = line.get("violation").toString();
		String fine = line.get("fine").toString();
		String state = line.get("state").toString();
		
		Parking singleParking = new Parking(zipcode, timestamp, fine, description, 
				vehicleID, state, violationID);
		
		
		return singleParking;

		
	}
	
	//read the parking csv file single line
	public static Parking readParkingCLine(String line) {
		
		String[] contents = line.split(",");
		
		
		String timestamp = contents[0];
		String fine = contents[1];
		String description = contents[2];
		String vehicleID = contents[3];
		String state = contents[4];
		String violationID = contents[5];
		String zipcode = contents[6];
		
		Parking singleParking = new Parking(zipcode, timestamp, fine, description, 
				vehicleID, state, violationID);
		
		
		return singleParking;
		
		
	}
	

	
	//read the property csv file first line
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
	
	
	//read the property csv file rest of lines
	public static Property readPropertyLine(String line, HashMap<String, Integer> header) {
		
        String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        
        String total = tokens[header.get("total_livable_area")];
        
        String value = tokens[header.get("market_value")];
        
        String zipcode = tokens[header.get("zip_code")].substring(0,5);
        
        Property singleProperty = new Property(zipcode, total, value);
		
		return singleProperty;
	}
	
	
	//add single line data to full data
	public static Data addData(Data existingData, SingleData singleData) {
		
		String tempCode = singleData.getZipcode();
		
		TreeMap<String, LinkedList<SingleData>> tempData = existingData.getData();
		
		if(tempData.containsKey(tempCode)) {
			
			LinkedList<SingleData> parkingList = tempData.get(tempCode);
			
			parkingList.add(singleData);
			
			tempData.put(tempCode, parkingList);
			
			
		}else {
			
			LinkedList<SingleData> parkingList = new LinkedList<SingleData>();
			
			parkingList.add(singleData);
			
			tempData.put(tempCode, parkingList);
			
		}
		
		existingData.setData(tempData);
		return existingData;
		
	}
	

}
