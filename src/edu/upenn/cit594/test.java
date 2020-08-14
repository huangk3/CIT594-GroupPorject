package edu.upenn.cit594;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class test {
	
	public static void main(String[] args) throws FileNotFoundException {
		double finePerCapita =0.0;
		System.out.format("%.4f%n", finePerCapita);
		String systemTime = Long.toString(System.currentTimeMillis());
		System.out.println(systemTime + String.join(" ", args));
		PrintWriter out = new PrintWriter(new File("log.txt"));
		System.out.println(out == null);
	}

}
