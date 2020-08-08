package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Property;

public class averageByMarketValue implements AverageCalculator{
	public Double calculateAverage (ArrayList<Property> properties) {
		if (properties == null) return 0.0;
		Double totalMarketValue = 0.0;
		int count = 0;
		for (Property p : properties ) {
			if (p.getMarketValue() > 0) {
				totalMarketValue += p.getMarketValue();
				count++;
			} 
		}
		
		if (count == 0) return 0.0;
		return totalMarketValue / count;
	}	

}
