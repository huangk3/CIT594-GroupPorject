package edu.upenn.cit594.ui;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUtility {
	

public static boolean fileCheck(String Path) {
	
	File file = new File(Path);
	
	return (file.exists() && file.canRead());
	
}

public static String askCode() {
	
	String code = null;
	System.out.println("Please input a zipcode");
	Scanner scan = new Scanner(System.in);
	
	try {
		code = scan.nextLine();
		
	} catch (InputMismatchException e) {
	    System.out.println("Error: the input is not a valid String code");
	    System.exit(0);
	}
	  
	
	return code;


}

public static String getNameFromPath(String fullPath) {
	
	Path p = Paths.get(fullPath);
	String file = p.getFileName().toString();
	return file;
}


}
