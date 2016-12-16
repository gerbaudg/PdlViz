package org.opencompare;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.file.Fonctionnement;
import org.opencompare.file.FonctionnementInterface;

public class testTraitementPcm {

	@Test
	public void test()  throws IOException {

        // Load a PCM
        File pcmFile = new File("pcms/Erasmus.pcm");
        PCMLoader loader = new KMFJSONLoader();
        PCM pcm = loader.load(pcmFile).get(0).getPcm();
        assertNotNull(pcm);
        

        // Execute
        TraitementPcm traitement = new TraitementPcm();
        traitement.clearAndSetVar(pcm);
        //traitement.printLocal();
        
        
        FonctionnementInterface fonctionnement = new Fonctionnement(traitement);
        System.out.println("PCM avec "+fonctionnement.getNombreFeature()+" features et "+
        fonctionnement.getNombreProduct()+" produits");
        System.out.println("FEATURES : ");
        for (String feat : fonctionnement.getFeatures()){
        	System.out.println(feat);
        }
//        for (String prod : fonctionnement.getProducts()){
//        	System.out.println(prod);
//        }
	}

}
