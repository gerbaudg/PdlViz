package org.opencompare;

import java.io.IOException;

import org.opencompare.api.java.PCM;

public interface TraitementInterface {
	/**
	 * Charger un fichier pcm dans l'objet pcm (permettra de récuperer les données (features et tout le reste)
	 * @param files: chemin du fichier
	 */
	public void pcmLoad(String files) throws IOException;

	public void pcmVerify(PCM pcm);
	
	public void generateJson();
}
