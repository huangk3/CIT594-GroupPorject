package edu.upenn.cit594.processor;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.datamanagement.ParkingJReader;
import edu.upenn.cit594.datamanagement.Reader;

public class CsvProcessor extends DataProcessor {
	

	
	
	public CsvProcessor(String populationPath) throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		
		super(populationPath);
	}

	@Override
	protected Reader createReader() {
		return new ParkingJReader();
		}
	
    
	
	
}

