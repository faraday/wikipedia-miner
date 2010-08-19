package org.wikipedia.miner.util.text;

import java.io.InputStream;

public class CustomProcessorChain extends TextProcessorChain {
	
	public CustomProcessorChain(String language) {        
		super();
		
        InputStream is = CustomProcessorChain.class.getResourceAsStream("/config/"+language+"Stopwords.txt");

        try {
        	TextProcessor stem = new CaseFolder();
            this.addTextProcessor(stem);
	        stem = new StopwordRemover(is);
	        this.addTextProcessor(stem);
	    	stem = new SnowballStemmer(language);
	    	this.addTextProcessor(stem);
        }
        catch(Exception e){
        	System.err.println("Cannot create CustomProcessorChain!");
        	e.printStackTrace();
        }
	}
}
