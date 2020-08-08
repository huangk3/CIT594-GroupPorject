package edu.upenn.cit594.data;

public class Property extends SingleData{
	
	private Double marketValue;
	private Double totalLivableArea;
	private String zipcode;
	
	
	public Property(String zipcodeIn, String marketValue, String totalLivableArea) {
		super(zipcodeIn);
		this.marketValue = Double.parseDouble(marketValue);
		this.totalLivableArea = Double.parseDouble(totalLivableArea);
		// TODO Auto-generated constructor stub
	}

	

	public Double getMarketValue() {
		return marketValue;
	}

	public Double getTotalLivableArea() {
		return totalLivableArea;
	}

	public String getZipcode() {
		return zipcode;
	}
	
	
	
}
