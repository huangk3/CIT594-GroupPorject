package edu.upenn.cit594;

import java.text.ParseException;

import edu.upenn.cit594.ui.UI;

public class Main {
	
	//ask user to input the input from using interface
	protected static UI usercase;
	public static String logFileName;
	
	public void Runner() throws ParseException {
		
		//handle the input number and see if the input number is right format
		usercase.getNumber();
		
		//user case call the right class, in this process, the log file has been generated
		usercase.call();
		
		//present the data to the system output
		usercase.present();
		
	}
	



public static void main(String[] args) throws ParseException {
	
	//setting up the user interface;
	usercase = new UI(args);
	
	//sign name to log file
	logFileName = args[4];
	
	//verify if the input is valid
	usercase.inputHandler();
	
	//Run the program and ask user to give input
	Main test = new Main();
	
	while(true) {
		
		test.Runner();
		
	}
	
	
   }
}
