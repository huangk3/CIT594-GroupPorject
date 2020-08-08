package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Data;


public interface Reader {

	public Data read(String filePath) throws FileNotFoundException, IOException, ParseException, java.text.ParseException;
	

}

