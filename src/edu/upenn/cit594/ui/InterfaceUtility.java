package edu.upenn.cit594.ui;

import java.io.File;

public class InterfaceUtility {
	

public static boolean fileCheck(String Path) {
	
	File file = new File(Path);
	
	return (file.exists() && file.canRead());
	
}

public static String askCode() {
	
	
	
	return null;
}

}
