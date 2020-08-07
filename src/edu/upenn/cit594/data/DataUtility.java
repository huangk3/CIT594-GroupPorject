package edu.upenn.cit594.data;

import java.util.ArrayList;
//import java.util.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat; 



public class DataUtility {
	
	
	//this function is to select certain tweets based on the index being given
	public static <E> ArrayList<E> Select(ArrayList<E> data, ArrayList<Integer> index){
		
		ArrayList<E> selected = new ArrayList<E>();
		
		for (int i = 0; i<index.size(); i++) {
			
			selected.add(data.get(index.get(i)));
		}
		
		return selected;
		
	}
	
	
	
}

