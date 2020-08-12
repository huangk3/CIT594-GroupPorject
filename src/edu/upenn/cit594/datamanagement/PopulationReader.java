package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Population;

public class PopulationReader {

	public Population read(String filePath)
			throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		// TODO Auto-generated method stub
		BufferedReader populationIn;
		Population populationData = null;
		try {populationIn = new BufferedReader(new FileReader(filePath));
			
		    populationData = TXTToData(populationIn);
		    
		    return populationData;
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return populationData;
	}
	
   private Population TXTToData(BufferedReader populationIn) throws IOException, ParseException {
		
		Population populationData = new Population();
		
		String line;
		
		while((line = populationIn.readLine())!= null) {
			
			//transform the string into parking
			
			String[] contents = line.split(" ");
			
			String zipcode = contents[0];
			
			int populationSize = Integer.parseInt(contents[1]);
			
			populationData.add(zipcode, populationSize);
					
		}
		
		
		return populationData;
		
		
	}
}
