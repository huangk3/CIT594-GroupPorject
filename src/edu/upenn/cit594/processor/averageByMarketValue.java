package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Property;

public class averageByMarketValue implements AverageCalculator{
	public int calculateAverage (ArrayList<Property> properties) {
		if (properties == null) return 0;
		int totalMarketValue = 0;
		int count = 0;
		for (Property p : properties ) {
			if (p.getMarketValue() > 0) {
				totalMarketValue += p.getMarketValue();
				count++;
			} 
		}
		return totalMarketValue / count;
	}	

}
