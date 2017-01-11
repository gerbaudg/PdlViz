package org.opencompare.file;

import java.util.List;
import java.util.Map;

import org.opencompare.api.java.PCM;

public interface FonctionnementInterface {
	//Pour chaque produit genere les donnees pour faire un chart de type radar
	//labels : labels, -> nom des features
	//datasets : dataS -> donnée pour un produit
	//GENERE juste dataS  -> en pourcentage (pour lmiter l'echelle)
	public Map<String,List<String[]>> genererRadarForProduct();
	
	
	//Pour une feature genere les donnees pour faire un chart d'un certain type
	public Object genererChartForFeature(String nomFeature,String chartType);
	//Pour plusieures features genere les donnees pour faire un chart multiple d'un certain type
	public Object genererChartForFeatures(String[]nomsFeature,String chartType);
	
	//Nombre de feature
	public int getNombreFeature();
	//Les noms des features
	public List<String> getFeatures();
	//Nombre de produit
	public int getNombreProduct();
	//Les noms des produits
	public List<String> getProducts();
	
	//Return les types de chart possible pour une certaine feature
	public List<String> getChartTypeForFeature(String nomFeature);

}
