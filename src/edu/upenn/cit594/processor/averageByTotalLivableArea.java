package edu.upenn.cit594.processor;

import java.util.ArrayList;

import edu.upenn.cit594.data.Property;

//obtain the average property livable area in a property list; 
public class averageByTotalLivableArea implements AverageCalculator {
	public Double calculateAverage (ArrayList<Property> properties) {
		if (properties == null) return 0;
		Double totalLivableArea = 0;
		int count = 0;
		for (Property p : properties ) {
			if ( p.getTotalLivableArea() > 0 ) {
				totalLivableArea += p.getMarketValue();
				count++;
			} 
		}
		if (count == 0) return 0;		
		return totalLivableArea / count;
	}
}
