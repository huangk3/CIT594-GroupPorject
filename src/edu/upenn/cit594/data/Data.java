package edu.upenn.cit594.data;

import java.util.ArrayList;

//this class is for storing the data
public class Data {
	
	private ArrayList<Object> fullData = new ArrayList<Object>();;
	
	//this function is for selecting data based on certain index
	public ArrayList<Object> getData(ArrayList<Integer> selected) {
		return  DataUtility.Select(fullData, selected);
	}

	//this function is for getting all the data
	public ArrayList<Object> getData() {
		return fullData;
	}
    
	//set data
	public void setData(ArrayList<Object> dataIn) {
		this.fullData = dataIn;
	}
	
	
	

}