package edu.upenn.cit594;

import java.text.ParseException;

import edu.upenn.cit594.ui.UI;

public class Main {
	
	//ask user to input the input from using interface
	protected UI usercase = new UI();
	public static String logfileName;
	
	public void Runner(String filepath, String statePath) throws ParseException {
		
		//handle the input and see if the input is right.
		usercase.inputHandler(filepath, statePath);
		
		if (usercase.getIndicator() < 0) {
			
			System.exit(0);
		}
		
		//user case call the right class, in this process, the log file has been generated
		usercase.call();
		
		//present the data to the system output
		usercase.present();
		
	}
	



public static void main(String[] args) {
	
	if (args.length != 3) {
		System.out.println("Please specify two file path of flu tweets and states.csv and one log file name");
		System.exit(0);
	}
	
	
	String filePath = args[0];
	
	String statePath = args[1];
	
	logfileName = args[2];
	
	Main test = new Main();
	
	try {
		test.Runner(filePath, statePath);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}}
