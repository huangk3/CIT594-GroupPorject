package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import edu.upenn.cit594.data.Data;
import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.datamanagement.ReaderUtility;

public class ParkingCReader implements Reader{
	public Data read(String filePath) throws ParseException {
		BufferedReader parkingIn;
		Data parkingData = null;
		try {parkingIn = new BufferedReader(new FileReader(filePath));
			
		    parkingData = CSVToData(parkingIn);
		    
		    return parkingData;
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return parkingData;
	}
	
	
	private Data CSVToData(BufferedReader parkingIn) throws IOException, ParseException {
		
		Data parkingData = new Data();
		
		ArrayList<Object> Parkings =  new ArrayList<Object>();
		
		String line;
		
		while((line = parkingIn.readLine())!= null) {
			
			//transform the string into parking
			Parking singleParking = ReaderUtility.readParkingCLine(line);
			
			Parkings.add(singleParking);
					
		}
		
		parkingData.setData(Parkings);
		
		return parkingData;
		
		
	}

}

