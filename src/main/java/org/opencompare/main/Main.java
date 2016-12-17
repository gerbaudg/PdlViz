package org.opencompare.main;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.opencompare.TraitementPcm;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.file.Fichier;
import org.opencompare.file.Fonctionnement;
import org.opencompare.file.FonctionnementInterface;

public class Main {

	static Fichier file;
	static FonctionnementInterface fonctionnement;
	static String pcm;

	public static void main(String[] args) throws IOException {
	//	try {
			file = new Fichier();
			Scanner sc = new Scanner(System.in);
			//PCM que l'on veut traiter en scanner, car application non dynamique
			System.out.println("Donnez le nom du pcm � traiter :");
			pcm = "pcms/"+sc.nextLine()+".pcm";

			file.setpcmPath(pcm);
	        // Load a PCM
	        File pcmFile = new File(pcm);
	        PCMLoader loader = new KMFJSONLoader();
	        PCM pcm = loader.load(pcmFile).get(0).getPcm();
	        //assertNotNull(pcm);
	        
	       int nbFeatures = pcm.getConcreteFeatures().size();
	       
	        // Execute
	        TraitementPcm traitement = new TraitementPcm();
	        traitement.clearAndSetVar(pcm);
			
			fonctionnement = new Fonctionnement(traitement);
			fonctionnement.getProducts();

			file.createHtml(nbFeatures);
			System.out.println("Le fichier html a ete cree.");
			file.createJS();
			System.out.println("Les fichiers js ont ete crees.");
			
			
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
	}

}
