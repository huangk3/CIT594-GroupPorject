package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Data;
import edu.upenn.cit594.data.Property;

public class PropertyReader implements Reader{

	@Override
	public Data read(String filePath)
			throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		// TODO Auto-generated method stub
		BufferedReader propertyIn;
		Data propertyData = null;
		try {propertyIn = new BufferedReader(new FileReader(filePath));
			
		    propertyData = CSVToData(propertyIn);
		    
		    return propertyData;
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return propertyData;
	}
	
   private Data CSVToData(BufferedReader propertyIn) throws IOException, ParseException {
		
		Data propertyData = new Data();
		
		String line = propertyIn.readLine();
		
		HashMap<String, Integer> Header = ReaderUtility.findHeader(line);
		
		while((line = propertyIn.readLine())!= null) {
			
			//transform the string into parking
			Property singleProperty = ReaderUtility.readPropertyLine(line, Header);
			
			propertyData = ReaderUtility.addData(propertyData, singleProperty);
					
		}
		
		return propertyData;
		
		
	}

	
	
	

}
