package org.opencompare;

import java.io.File;
import java.io.IOException;

import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.GenerationInterface;


public class Traitement implements TraitementInterface {
	
	//Pcm use for traitement
	private PCM pcm;
	//Class we call when everything is ok
	private GenerationInterface json;
	
	
	public void pcmLoad(String files) {
		//Create file
        File pcmFile = new File(files);
        //Call Open compare API to load
        PCMLoader loader = new KMFJSONLoader();
        //Load file
        PCM pcm ;
		try {
			pcm = loader.load(pcmFile).get(0).getPcm();
		//	_logger.info("Pcm load succefully");
		    //Call the method that checks PCM's integrity
		    pcmVerify(pcm);
		} catch (IOException e) {
			//_logger.error("Pcm not found");
		}

	}

	public void pcmVerify(PCM pcm) {
        // We start by checking if products'name are not null
        for (Product product : pcm.getProducts()) {
        	try{
        		
        		if(product.getKeyContent()==null){
        	          throw new Exception();
        	     }
        	 }
        	 catch(Exception e){
        	      //Message 
        		// _logger.error("Un produit de la PCM est null. ");
        		//End of Programm
             	System.exit(0);
        	 }
        }
        // Then we check if features'name are not null
        for (Feature feature : pcm.getConcreteFeatures()){
        	try{
        		if(feature.getName()==null){
       	          throw new Exception();
        		}
       	     }catch(Exception e) {
       	    	 //Process message however you would like
       	    	 //_logger.error("Une caract�ristique de la PCM est null. ");
        		//End of Programm
             	System.exit(0);
       	     }
       	 }
       this.pcm = pcm; //save pcm (for test)

	}

	public void generateJson() {
        //_logger.info("Pcm valid");
        json = new Generation(this);
        //_logger.info("Launch generation");
        
        //Launch generation of json
		json.generateJSON(pcm);
		
	}

}
