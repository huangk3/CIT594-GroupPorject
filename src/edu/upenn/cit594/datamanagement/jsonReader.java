package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Data;
import edu.upenn.cit594.data.tweet;

//this class is for reading JSON file and storing as tweets data format.
public class jsonReader implements Reader{
	
	
	
	public Data read(String filePath) throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		
		
	}
	
	
	//convert JSON format into tweet format
	private Data JSONtoData(JSONArray tweetArray) throws java.text.ParseException {
		
		
		
		
	}
	
	

}
