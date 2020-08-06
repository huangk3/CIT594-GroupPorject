package edu.upenn.cit594.ui;

import java.io.File;

public class UIUtility {
	
	public static boolean fileCheck(String filepath) {
		
		File file = new File(filepath);
		
		return (file.exists() && file.canRead());
		
	}

}
