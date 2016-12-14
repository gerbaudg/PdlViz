package org.opencompare.main;

import java.io.IOException;
import java.util.Scanner;

import org.opencompare.file.Fichier;

public class Main {

	static Fichier file;

	public static void main(String[] args) {
		try {
			file = new Fichier();
			Scanner sc = new Scanner(System.in);
			System.out.println("Donnez le nom du pcm à traiter :");
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
