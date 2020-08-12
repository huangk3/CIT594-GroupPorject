package edu.upenn.cit594.ui;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUtility {
	

public static boolean fileCheck(String Path) {
	
	File file = new File(Path);
	
	return (file.exists() && file.canRead());
	
}

public static String askCode() {
	
	String code = null;
	System.out.println("Please input a code");
	Scanner scan = new Scanner(System.in);
	
	try {
		code = scan.nextLine();
		
	} catch (InputMismatchException e) {
	    System.out.println("Error: the input is not a valid String code");
	    System.exit(0);
	}
	  
	
	return code;


}

}
