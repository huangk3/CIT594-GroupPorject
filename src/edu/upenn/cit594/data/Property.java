package edu.upenn.cit594.data;

public class Property extends SingleData{
	
	private Double marketValue;
	private Double totalLivableArea;
	
	
	public Property(String zipcodeIn, String marketValue, String totalLivableArea) {
		super(zipcodeIn);
		try {
			this.marketValue = Double.parseDouble(marketValue);
		}catch(NumberFormatException e) {
			this.marketValue = null;
			
		}
		try {
			this.totalLivableArea = Double.parseDouble(totalLivableArea);
		}catch(NumberFormatException e) {
			this.totalLivableArea = null;
			
		}
		
		// TODO Auto-generated constructor stub
	}

	

	public Double getMarketValue() {
		return marketValue;
	}

	public Double getTotalLivableArea() {
		return totalLivableArea;
	}

	
	
	
	
}
