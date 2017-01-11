package org.opencompare.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencompare.TraitementPcm;
import org.opencompare.api.java.PCM;

public class Fonctionnement implements FonctionnementInterface {
	
	TraitementPcm traitement ;
	
	public Fonctionnement(TraitementPcm trait){
		traitement=trait;
	}

	@Override
	public Map<String, List<String[]>> genererRadarForProduct() {
		Map<String,List<String[]>>result = new HashMap<String, List<String[]>>();
		//Mettre Feature, liste des nom de feature ---> NON, appel a une autre fonction
		for (String stg : traitement.getProduits()){
			String key = stg;
			List<String[]>value =traitement.getProduitForRadar(stg);
			result.put(key, value);
		}
		return result;
	}

	@Override
	public Object genererChartForFeature(String nomFeature, String chartType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object genererChartForFeatures(String[] nomsFeature, String chartType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNombreFeature() {
		return traitement.getFeatures().size();
	}

	@Override
	public List<String> getFeatures() {
		return traitement.getFeatures();
	}

	@Override
	public int getNombreProduct() {
		return traitement.getProduits().size();
	}

	@Override
	public List<String> getProducts() {
		return traitement.getProduits();
	}

	@Override
	public List<String> getChartTypeForFeature(String nomFeature) {
		return traitement.getTypeGraph(nomFeature);
		
	}

}
