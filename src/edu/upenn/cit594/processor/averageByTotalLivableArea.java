package edu.upenn.cit594.processor;

import java.util.LinkedList;
import java.util.ListIterator;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.SingleData;
//obtain the average property livable area in a property list; 
public class averageByTotalLivableArea implements AverageCalculator {
	public int calculateAverage (LinkedList<SingleData> properties) {
		if (properties == null || properties.isEmpty()) return 0;
		Double totalLivableArea = 0.0;
		int count = 0;
		ListIterator<SingleData> listIterator = properties.listIterator();
		while (listIterator.hasNext()) {
			SingleData s = listIterator.next();
			if (s instanceof Property) {
				Property p = (Property) s;
				Double livableArea = p.getTotalLivableArea();
				if ( livableArea > 0 ) {
					totalLivableArea += livableArea;
					count++;
				} 
			}
		}
		if (count == 0) return 0;		
		return (int) Math.floor(totalLivableArea / count);
	}
}