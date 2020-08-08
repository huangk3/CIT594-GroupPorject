package edu.upenn.cit594.data;

import java.util.LinkedList;
import java.util.TreeMap;

//this class is for storing the data
public class Data {
	private TreeMap<String, LinkedList<SingleData>> fullData = new TreeMap<String, LinkedList<SingleData>>();
	

	//this function is for getting all the data
	public TreeMap<String, LinkedList<SingleData>>  getData() {
		return fullData;
	}
    
	//set data
	public void setData(TreeMap<String, LinkedList<SingleData>> dataIn) {
		this.fullData = dataIn;
	}
	
		

}