package org.opencompare;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Generation implements GenerationInterface {

	private JsonNode jsonObj;
	
	private ObjectMapper mapper ;
	
	private Traitement traitmnt;
	
	public Generation(Traitement t) {
		mapper = new ObjectMapper();
		traitmnt = t;
	}
	
	public void generateJSON(PCM pcm) {
		/*For each product we add an object on JSON, during the first iteration we create a Filters Object and Dimensions Object too*/
		// _logger.setLevel(Level.OFF); //disable log
		
		//True= we must create list of filters in JSON, false: list of filters was already create
		boolean filters_ = true;
		//Type of filter
		String strTtypeFilter_;
		//Object to contains filters
		//JSONObject objfilterJSON_ = new JSONObject();
		
		//For each product
		for (Product product : pcm.getProducts()) {
			//Create an JSONObject to get content of cells for each product
			//JSONObject objJsonCell_ = new JSONObject();
			//if product exist or is not empty
			if( product.getKeyContent() != null && !product.getKeyContent().equals("")){
				//for each cells
				for (Cell cell : product.getCells()) {
					//Adding cells value
					//objJsonCell_.put(cell.getFeature().getName(), cell.getContent());
					
					/*Filters*/
					//If we don't have already define filter, we register features in objfilterJSON_
					if (filters_) {
						//Determine type of filter
						strTtypeFilter_ = cell.getInterpretation() + ""; //get type
						//Delete useless data
						strTtypeFilter_ = strTtypeFilter_.split("@")[0];
						strTtypeFilter_ = strTtypeFilter_.split("\\.")[6];
						strTtypeFilter_ = strTtypeFilter_.substring(0, strTtypeFilter_.length() - 4);
						
						//If type not know we put string type
						if (strTtypeFilter_.equals("NotAvailable")) {
							strTtypeFilter_ = "StringValue";
						}
	
						//Check with regExp if some string value can be number
						Pattern p = Pattern.compile("\\d.*"); //if string start wtih numbers, we considers feature type like number (ex: 52 lb)
						Matcher m = p.matcher(cell.getContent());
						//if regexp valid
						if (m.matches()) {
							strTtypeFilter_ = "RealValue"; //we change type of filter
						}
						//register filter in objfilterJSON_ (name,type)
						//objfilterJSON_.put(cell.getFeature().getName(), strTtypeFilter_);
					}
	
				}
				//We indicate we generate filter
				filters_ = false;
				
				//We add new object/product in JSON (nameproduct, objectJSON with all features value)
				//_json.put(product.getName(), objJsonCell_);		
			}

		}
		//Add objfilterJSON_ in JSON
		//_json.put("FILTERS", objfilterJSON_);
		//Call method to select dimension
		//choixDimension(_json.getJSONObject("FILTERS"));
		//Call method to register json in real file
		//this.afficherJSON(_json);

	}

	public void afficherJSON(JsonNode json) {
		// TODO Auto-generated method stub

	}

	public void choixDimension(JsonNode json) {
		// TODO Auto-generated method stub

	}

	public JsonNode lireJSONParametres(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verifJSONgenere(JsonNode obj, PCM pcm) {
		// TODO Auto-generated method stub
		return false;
	}

}
