package edu.upenn.cit594.data;

public abstract class SingleData {
	
	private String zipcode;
	public SingleData(String zipcodeIn) {
		
		this.zipcode = zipcodeIn;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	

}
