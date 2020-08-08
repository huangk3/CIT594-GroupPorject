package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Data;
import edu.upenn.cit594.data.Population;

public class PopulationReader implements Reader{

	@Override
	public Data read(String filePath)
			throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		// TODO Auto-generated method stub
		BufferedReader populationIn;
		Data populationData = null;
		try {populationIn = new BufferedReader(new FileReader(filePath));
			
		    populationData = TXTToData(populationIn);
		    
		    return populationData;
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return populationData;
	}
	
   private Data TXTToData(BufferedReader populationIn) throws IOException, ParseException {
		
		Data populationData = new Data();
		
		String line;
		
		while((line = populationIn.readLine())!= null) {
			
			//transform the string into parking
			Population singlePopulation = ReaderUtility.readPopulationLine(line);
			
			populationData = ReaderUtility.addData(populationData, singlePopulation);
					
		}
		
		
		return populationData;
		
		
	}
}
