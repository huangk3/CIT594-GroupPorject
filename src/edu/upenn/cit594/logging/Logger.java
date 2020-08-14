package edu.upenn.cit594.logging;

import java.io.File;
import java.io.PrintWriter;

import edu.upenn.cit594.ui.UI;

public class Logger {
	
	private PrintWriter out;
	//private String filename = "log.txt";
	
	public Logger(String filename) {
		
		try { 
			
			out = new PrintWriter(new File(filename));
			System.out.println(out == null);}
		catch(Exception e) {}
		
	}
	
	
	private static Logger instance = new Logger(UI.logFileName);
	
	public static Logger getInstance() {
		
		return instance;}
	
	
	public void log(String msg) {
		//logging
		String systemTime = Long.toString(System.currentTimeMillis());
		System.out.println(systemTime + " " + msg);
		//System.out.println(out == null);
		out.println(systemTime + " " + msg);
		out.flush();
	}
	
}
