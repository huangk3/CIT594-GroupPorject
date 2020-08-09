package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Data;
import edu.upenn.cit594.data.Parking;


//this class is for reading JSON file and storing as tweets data format.
public class ParkingJReader implements Reader{
	
	
	
	public Data read(String filePath) throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		
		JSONParser parser = new JSONParser();
		
		//read JSON file
		JSONArray parkingArray = (JSONArray)parser.parse(new FileReader(filePath));
		
		Data parkingData = JSONToData(parkingArray);
		
		return parkingData;
	}
	
	
	//convert JSON format into data format
	private Data JSONToData(JSONArray parkingArray) throws java.text.ParseException {
		
		Data parkingData = new Data();
		
		Iterator<?> iter = parkingArray.iterator();
		
		while(iter.hasNext()) {
			
			
			//get the next JSON object
			JSONObject parking = (JSONObject) iter.next();
			
			Parking tempParking =  ReaderUtility.readParkingJLine(parking);
			
			if (tempParking != null) {
				parkingData = ReaderUtility.addData(parkingData, tempParking);	
			}
						
		}
		
		
		return parkingData;
		
		
	}
	
	

}
