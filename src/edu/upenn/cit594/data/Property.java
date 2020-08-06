package edu.upenn.cit594.data;

public class Property {
	private Double marketValue;
	private Double totalLivableArea;
	private String zipcode;
	
	public Property (String marketValue, String totalLivableArea, String zipcode) {
		this.marketValue = Double.parseDouble(marketValue);
		this.totalLivableArea = Double.parseDouble(totalLivableArea);
		this.zipcode = zipcode;
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
