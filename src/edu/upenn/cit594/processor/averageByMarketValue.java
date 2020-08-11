package edu.upenn.cit594.processor;

import java.util.LinkedList;
import java.util.ListIterator;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.SingleData;

public class averageByMarketValue implements AverageCalculator{
	public int calculateAverage (LinkedList<SingleData> properties) {
		if (properties == null || properties.isEmpty()) return 0;
		Double totalMarketValue = 0.0;
		int count = 0;
		ListIterator<SingleData> listIterator = properties.listIterator();
		while (listIterator.hasNext()) {
			SingleData s = listIterator.next();
			if (s instanceof Property) {
				Property p = (Property) s;
				Double marketValue = p.getMarketValue();
				if ( marketValue > 0 ) {
					totalMarketValue += marketValue;
					count++;
				} 
			}
		}		
		if (count == 0) return 0;
		return (int) Math.floor(totalMarketValue / count);
	}	

}