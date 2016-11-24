package org.opencompare;

import org.opencompare.api.java.PCM;

import com.fasterxml.jackson.databind.JsonNode;



public interface GenerationInterface {

	public void generateJSON(PCM pcm);
	
	public void afficherJSON(JsonNode json);
	
	public void choixDimension(JsonNode json);
	
	public JsonNode lireJSONParametres(String string);
	
	public boolean verifJSONgenere(JsonNode obj, PCM pcm);
}
