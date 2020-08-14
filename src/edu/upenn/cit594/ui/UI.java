package edu.upenn.cit594.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
//import java.util.HashMap;
import java.util.Set;

import java.util.regex.Pattern;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.CsvProcessor;
import edu.upenn.cit594.processor.DataProcessor;
import edu.upenn.cit594.processor.JsonProcessor;




public class UI {
	


	//input from users
	private  String fileFormat;
	private  String parkingPath;
	private  String propertyPath;
	private  String populationPath;
	public static String logFileName;
	private int inputNumber;
	
	
	//static variable for internal use
	private static String pattern1 = ".*.json";
	private static String pattern2 = ".*.csv";
	private static String pattern3 = ".*.txt";
	
	
	//output variable for processor use
	protected Logger l;
	
	//this indicator is for deciding which park reader
	private int indicator = -1;
	//-1: illegal input format
	//0: JSON parking format
	//1: csv parking format
	//2: reading finished
	
	//this indicator is for reading if parking and property have been read alreday
	private int[] readerIndicator;
	
	//create the processor calculate engine
	private DataProcessor processor;
	private Set<Integer> InputSet;
	
	
	
	//construct interface
	public UI(String[] args) {
		
		if (args.length != 5) {
			System.out.println("Please input right number of arguments");
			System.exit(0);
		}
		
		
		
		//set up variables
		fileFormat= args[0];
		parkingPath = args[1];
		propertyPath = args[2];
		populationPath = args[3];
		logFileName =  args[4];
		
		//set up logger
		l = Logger.getInstance();
		l.log(String.join(" ", args));
		
		
		//input set for identifying input numbers
		InputSet = new HashSet<Integer>();  
        // Add input number case into the set  
		InputSet.add(3); 
		InputSet.add(4); 
		InputSet.add(5); 
		InputSet.add(6); 
		
		//set up reading indicator
		readerIndicator = new int[2];
		readerIndicator[0] = 0;
		readerIndicator[1] = 0;
	}
	
	
	
	
	//handle the input and verify if the input format is correct.
	public void inputHandler() {
		
		//see if parking file match with json format
		if (Pattern.matches(pattern1,parkingPath) && fileFormat.contentEquals("json")){
			
			//check parking file existence and permissions for read
			if(InterfaceUtility.fileCheck(parkingPath)) {
				indicator = 0;
			}else {
				System.out.println("The parking json file provided can not be read or does not exist.");
				System.exit(0);
			}
	    //see if parking file match with csv format	
		}else if(Pattern.matches(pattern2,parkingPath) && fileFormat.contentEquals("csv")) {
			
			//check parking file existence and permissions for read
			if(InterfaceUtility.fileCheck(parkingPath)) {
				indicator = 1;
			}else {
				System.out.println("The parking csv file provided can not be read or does not exist.");
				System.exit(0);
			}
		
		}else {
			
			indicator = -1;
			System.out.println("Please input right format of parking");
			System.exit(0);
		}
		
		//check property file existence and permissions for read
		if(Pattern.matches(pattern2, propertyPath) && InterfaceUtility.fileCheck(propertyPath)) {}
			 else {
				indicator = -1;
				System.out.println("The property file provided can not be read or does not exist.");
				System.exit(0);
			}
		
		//check population file existence and permissions for read	
		if(Pattern.matches(pattern3, populationPath) && InterfaceUtility.fileCheck(populationPath)) {}
		 else {
			indicator = -1;
			System.out.println("The population file provided can not be read or does not exist.");
			System.exit(0);
		}
		
		
	}
	
	//call the processor which will call reading
	public void createProcessor() throws ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		
		
		
		//check if the indicator is valid
		if (indicator == 0) {
			
			 //logging
			 l.log(InterfaceUtility.getNameFromPath(populationPath));
			
			processor = new JsonProcessor(populationPath);
			
			indicator = 2;
			
			
			
			
		}else if(indicator == 1){
			
			//logging
			 l.log(InterfaceUtility.getNameFromPath(populationPath));
			
			processor = new CsvProcessor(populationPath);
			
			indicator = 2;
			
			 
			
		}else {
			
			System.out.println("The indicator is set to be negative");
			System.exit(0);
			
		}
		
		
		
		
	};
	
	public void callReader() throws FileNotFoundException, ParseException, IOException, org.json.simple.parser.ParseException {
		
		//check if we need to read parking
		if(inputNumber == 2 && readerIndicator[0] == 0 ) {
			//logging
			l.log(InterfaceUtility.getNameFromPath(parkingPath));
		    
			processor.readParking(parkingPath);
		    readerIndicator[0] = 1;
		    
		    
		}
		
		//check if we need to read Property
		if(InputSet.contains(inputNumber) && readerIndicator[1] == 0) {
			//logging
			l.log(InterfaceUtility.getNameFromPath(propertyPath));
			 
		    processor.readProperty(propertyPath);
		    readerIndicator[1] = 1;
		    
		    
		    
		    //check if we need to read Parking
		    if(inputNumber == 6 && readerIndicator[0] == 0) {
		    	//logging
				l.log(InterfaceUtility.getNameFromPath(parkingPath));
		    	
				processor.readParking(parkingPath);
			    readerIndicator[0] = 1; 
			    
		    }
		}
		
				
	}
	
	//get indicator
	public int getIndicator() {
		
		return indicator;
		
	}
	
	//print final output to the screen
	/*public void present() {
		
		System.out.println("The program finish reading");
		
		
	}*/



    //ask user to provide number
	public void getNumber() {
		
		System.out.println("Please input a number");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		try {
			
			inputNumber = Integer.parseInt(input);
			l.log(Integer.toString(inputNumber));
			
			if(inputNumber == 0) {
				System.out.println("The program is terminated by user");
				System.exit(0);
			}else if(inputNumber < 0 || inputNumber > 6) {
				System.out.println("Error: the input is not a valid number");
				System.exit(0);	
			}
			
		} catch (NumberFormatException e) {
		    System.out.println("Error: the input is not a valid number");
		    System.exit(0);
		}
		  
		
		// TODO Auto-generated method stub
		
	}




	public void calculate() {
		
		if(InputSet.contains(inputNumber) && inputNumber != 6) {
			
			//cover the case 3, 4, 5
			String zipCode = InterfaceUtility.askCode();
			//logging
			l.log(zipCode);
			//process
			processor.process(inputNumber, zipCode);
		
		}else {
			
			//cover the case 1, 2, 6
			processor.process(inputNumber);
		}
		
		
		
	}




	
	
	

}
