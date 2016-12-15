package org.opencompare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.CSVExporter;
import org.opencompare.api.java.io.PCMLoader;

public class ExtractionDonnee {
	
	public static void main(String[] args) throws IOException {
	 
	        // Define a file representing a PCM to load
	        File pcmFile = new File("pcms/example.pcm");
	        int numberofFeatures =0;
	        // Create a loader that can handle the file format
	        PCMLoader loader = new KMFJSONLoader();
       
	        // Load the file
	        // A loader may return multiple PCM containers depending on the input format
	        // A PCM container encapsulates a PCM and its associated metadata
	        List<PCMContainer> pcmContainers = loader.load(pcmFile);

	        for (PCMContainer pcmContainer : pcmContainers) {

	            // Get the PCM
	            PCM pcm = pcmContainer.getPcm();

	            // Browse the cells of the PCM
	            for (Product product : pcm.getProducts()) {
	                for (Feature feature : pcm.getConcreteFeatures()) {
                       // i++;
	                    // Find the cell corresponding to the current feature and product
	                    Cell cell = product.findCell(feature);

	                    // Get information contained in the cell
	                    String content = cell.getContent();
	                    String rawContent = cell.getRawContent();
	                    Value interpretation = cell.getInterpretation();
                       numberofFeatures= pcm.getConcreteFeatures().size();
	                    // Print the content of the cell
	                // System.out.println("(" + product.getKeyContent() + ", " + feature.getName() + ") = " + content);
	                   
	                }
	            }
	            System.out.println("There is the number of features" + numberofFeatures); 

	            // Export the PCM container to CSV
	            //CSVExporter csvExporter = new CSVExporter();
	          // String csv = csvExporter.export(pcmContainer);

	           // Write CSV content to file
	          // Path outputFile = Files.createTempFile("oc-", ".csv");
	          //  Files.write(outputFile, csv.getBytes());
	          // System.out.println("PCM exported to " + outputFile);
	        }

	    }
	
}
