package edu.upenn.cit594.processor;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.datamanagement.ParkingJReader;
import edu.upenn.cit594.datamanagement.Reader;

public class JsonProcessor extends DataProcessor {
	

	
	
	public JsonProcessor(String propertyPath, String populationPath) throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		
		super(propertyPath, populationPath);
	}

	@Override
	protected Reader createReader() {
		return new ParkingJReader();
		}
	
    
	
	
}
