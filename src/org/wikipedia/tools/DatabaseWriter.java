package org.wikipedia.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.wikipedia.miner.model.Wikipedia;
import org.wikipedia.miner.util.text.CustomProcessorChain;

public class DatabaseWriter {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//connect to database
		Wikipedia wikipedia = new Wikipedia("localhost", "wikiminer", "root", "123456") ;
		
		String language = "english";
		InputStream is = DatabaseWriter.class.getResourceAsStream("/config/language.conf");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			language = br.readLine();
			br.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
						
		//load cvs files
		File dataDirectory = new File(args[0]) ;
		wikipedia.getDatabase().loadData(dataDirectory, true) ;
						
		//prepare text processors
		wikipedia.getDatabase().prepareForTextProcessor(new CustomProcessorChain(language)) ;
						
		//cache definitions (only worth doing if you will be using them a lot - will take a day or so)
		// wikipedia.getDatabase().summarizeDefinitions() ;

	}

}
