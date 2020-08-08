package edu.upenn.cit594.ui;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
//import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.Data;
import edu.upenn.cit594.data.SingleData;


public class UI {
	
	//input from users
	private  String fileFormat;
	private  String parkingPath;
	private  String propertyPath;
	private  String populationPath;
	private int inputNumber;
	
	
	//static variable for internal use
	private static String pattern1 = ".*.json";
	private static String pattern2 = ".*.csv";
	private static String pattern3 = ".*.txt";
	
	
	//output variable for processor use
	//-1: illegal input format
	//0: JSON parking format
	//1: csv parking format
	//2: reading finished
	private int indicator = -1;
	
	
	
	//construct interface
	public UI(String[] args) {
		
		if (args.length != 5) {
			System.out.println("Please input right number of arguments");
			System.exit(0);
		}
		
		//set up the variables
		fileFormat= args[0];
		parkingPath = args[1];
		propertyPath = args[2];
		populationPath = args[3];
		
		
	}
	
	
	
	
	//handle the input and verify if the input format is correct.
	public void inputHandler() {
		
		if (Pattern.matches(pattern1,parkingPath) && fileFormat.contentEquals("json")){
			
			//check file existence and permissions for read
			if(UIUtility.fileCheck(parkingPath)) {
				indicator = 0;
			}else {
				System.out.println("The parking json file provided can not be read or does not exist.");
				System.exit(0);
			}
			
		}else if(Pattern.matches(pattern2,parkingPath) && fileFormat.contentEquals("csv")) {
			
			//check file existence and permissions for read
			if(UIUtility.fileCheck(parkingPath)) {
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
		
		if(Pattern.matches(pattern2, propertyPath) && UIUtility.fileCheck(propertyPath)) {}
			 else {
				indicator = -1;
				System.out.println("The property file provided can not be read or does not exist.");
				System.exit(0);
			}
			
		if(Pattern.matches(pattern3, propertyPath) && UIUtility.fileCheck(populationPath)) {}
		 else {
			indicator = -1;
			System.out.println("The population file provided can not be read or does not exist.");
			System.exit(0);
		}
		
		
	}
	
	//call the processor which will call reading
	public void call() throws ParseException {
		
		
		//check if the indicator is valid
		if (indicator == 0) {
			
			//ParkingJReader being used inside
			jsonProcessor js = new jsonProcessor(propertyPath, populationPath);
			js.process(parkingPath);
			indicator = 2;
			
			
		}else if(indicator == 1){
			
			//ParkingCReader being used inside
			csvProcessor csv = new csvProcessor(propertyPath, populationPath);
			csv.process(parkingPath);
			indicator = 2;
			
		}else {
			
			System.out.println("The indicator is set to be negative");
			System.exit(0);
			
		}
		
		
	};
	
	//get indicator
	public int getIndicator() {
		
		return indicator;
		
	}
	
	//print final output to the screen
	public void present() {
		
		
	}



    //ask user to provide number
	public void getNumber() {
		
		System.out.println("Please input a number");
		Scanner sc = new Scanner(System.in);
		
		try {
			inputNumber = sc.nextInt();
			if(inputNumber == 0) {
				System.out.println("The program is terminated by user");
				System.exit(0);
			}else if(inputNumber < 0 || inputNumber > 6) {
				System.out.println("Error: the input is not a valid number");
				System.exit(0);	
			}
			
		} catch (InputMismatchException e) {
		    System.out.println("Error: the input is not a valid number");
		    System.exit(0);
		}
		  
		sc.close();
		// TODO Auto-generated method stub
		
	}




	
	
	

}
