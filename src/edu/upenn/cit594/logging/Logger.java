package edu.upenn.cit594.logging;

import java.io.File;
import java.io.PrintWriter;

public class Logger {
	private PrintWriter out;
	private Logger (String filename) {
		try { out = new PrintWriter (new File(filename));} 
		catch (Exception e) {e.printStackTrace();}
	}
	//singleton instance
	private static Logger instance (String filename) {
		return new Logger(filename);
	} 
	
	public static Logger getInstance (String filename) {
		return instance (filename);
	}
	
	public void log(String msg) {
		out.println(msg);
		out.flush();
	}
	
}
