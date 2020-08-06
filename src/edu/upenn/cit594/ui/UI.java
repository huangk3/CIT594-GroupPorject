package edu.upenn.cit594.ui;

import java.text.ParseException;
import java.util.Map;
//import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import edu.upenn.cit594.processor.jsonProcessor;
import edu.upenn.cit594.processor.txtProcessor;

public class UI {
	
	private int indicator = -1;
	private String file;
	private String state;
	private TreeMap<String,Integer> fluLocation;
	
	//handle the input and verify if the input format is correct.
	public void inputHandler(String filePath, String statePath) {
		
		String pattern1 = ".*.json";
		
		String pattern2 = ".*.txt";
		
		String pattern3 = ".*.csv";
		
		
		if (Pattern.matches(pattern1,filePath)){
			
			file = filePath;
			//check file existence and permissions for read
			if(UIUtility.fileCheck(filePath)) {
				indicator = 0;
			}else {
				System.out.println("The json file provided can not be read or does not exist.");
			}
			
		}else if(Pattern.matches(pattern2,filePath)) {
			
			file = filePath;
			//check file existence and permissions for read
			if(UIUtility.fileCheck(filePath)) {
				indicator = 1;
			}else {
				System.out.println("The txt file provided can not be read or does not exist.");
			}
		
		}else {
			
			System.out.println("Please input right format of tweets.");
			indicator = -1;
		}
		
		if(Pattern.matches(pattern3, statePath)) {
			
			state = statePath;
			//check file existence and permissions for read
			if(UIUtility.fileCheck(statePath)) {
			}else {
				System.out.println("The csv file provided can not be read or does not exist.");
				indicator = -1;
			}
			
		}else {
			System.out.println("Please input right format of states location.");
			indicator = -1;
		}
		
		
		
		
		
		
	}
	
	//call the processor 
	public void call() throws ParseException {
		
		
		
		if (indicator == 0) {
			
			jsonProcessor js = new jsonProcessor(state);
			js.process(file);
			fluLocation = js.getFluLocation();
			
		}else {
			
			txtProcessor txt = new txtProcessor(state);
			txt.process(file);
			fluLocation = txt.getFluLocation();
		}
		
		
	};
	
	//get indicator
	public int getIndicator() {
		
		return indicator;
		
	}
	
	//print final output to the screen
	public void present() {
		
		 for (Map.Entry<String, Integer> entry : fluLocation.entrySet()) {
			 
			 System.out.println(entry.getKey() +  ": " +
                      entry.getValue());
			 
		 }
	              
		
	};
	
	

}
