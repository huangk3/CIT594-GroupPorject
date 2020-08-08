package edu.upenn.cit594.data;
//import java.util.regex.Pattern;
public class Property {
	private Double marketValue;
	private Double totalLivableArea;
	private String zipcode;
	
	public Property (String marketValue, String totalLivableArea, String zipcode) {
//		String decimalPattern = "([0-9]*)([.]?)([0-9]*)";
//		boolean matchMarketValue = Pattern.matches(decimalPattern, marketValue);
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
