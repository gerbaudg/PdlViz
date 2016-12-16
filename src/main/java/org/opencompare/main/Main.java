package org.opencompare.main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

import org.opencompare.file.Fichier;

public class Main {

	static Fichier file;

	public static void main(String[] args) {
		
		FilenameFilter javaFilter = new FilenameFilter() { 
			public boolean accept(File arg0, String arg1) { 
				return arg1.endsWith(".pcm"); 
			} 
		}; 

		File repertoire = new File("pcms"); 
		String[] children = repertoire.list(javaFilter); 
		for(int i=0;i<children.length;i++){ 
			System.out.println(children[i].substring(0,children[i].lastIndexOf(".pcm")));
		} 

		
		try {
			file = new Fichier();
			Scanner sc = new Scanner(System.in);
			System.out.println("Donnez le nom du pcm � traiter :");
			String pcm = sc.nextLine();

			file.setpcmPath(pcm);

			file.createHtml();
			System.out.println("Le fichier html a ete cree.");
			file.createJS();
			System.out.println("Le fichier js a ete cree.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}