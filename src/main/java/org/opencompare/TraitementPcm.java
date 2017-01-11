package org.opencompare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.FeatureGroup;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMElement;
import org.opencompare.api.java.PCMFactory;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.util.PCMVisitor;
import org.opencompare.api.java.value.BooleanValue;
import org.opencompare.api.java.value.Conditional;
import org.opencompare.api.java.value.DateValue;
import org.opencompare.api.java.value.Dimension;
import org.opencompare.api.java.value.IntegerValue;
import org.opencompare.api.java.value.Multiple;
import org.opencompare.api.java.value.NotApplicable;
import org.opencompare.api.java.value.NotAvailable;
import org.opencompare.api.java.value.Partial;
import org.opencompare.api.java.value.RealValue;
import org.opencompare.api.java.value.StringValue;
import org.opencompare.api.java.value.Unit;
import org.opencompare.api.java.value.Version;

public class TraitementPcm implements PCMVisitor {

	private String nomFeatureProduit = "";

	private static PCMFactory factory = null;
	private static PCM clone = null;
	private boolean print = false;
	private boolean clearAndSet = false;

	private boolean radar = false;
	private String product = "";
	private List<String[]> valueProduct;
	private static String typeCell = "";
	
	private List<String>typeGraphe;
	private boolean type = false;
	private String featureType ="";

	private List<String> produits = new ArrayList<String>();
	private List<String> features = new ArrayList<String>();

	private Map<String, Integer> max = new HashMap<String, Integer>();

	// clearAndSet le pcm pour avoir des cellules claires
	// Selon le type, met les cellules du bon type
	// Si int -> 10g = 10
	// ET initialise les variables static nombre produit nombre feature ...
	public PCM clearAndSetVar(PCM pcm) {
		System.out.println("DEBUT TRAITEMENT");
		clearAndSet = true;

		pcm.accept(this);
		clearAndSet = false;
		clone = pcm;
		return clone;
	}

	public List<String[]> getProduitForRadar(String prod) {
		radar = true;
		product = prod;
		valueProduct = new ArrayList<String[]>();
		clone.accept(this);
		product = "";
		radar = false;
		return valueProduct;
	}

	// Fonction de test qui print
	public void printLocal() {
		System.out.println("Print pcm local");
		print = true;
		clone.accept(this);
		print = false;
	}

	public List<String> getProduits() {
		return produits;
	}

	public List<String> getFeatures() {
		return features;
	}
	public List<String> getTypeGraph(String nomFeature){
		typeGraphe=new ArrayList<String>();
		featureType = nomFeature;
		type = true;
		clone.accept(this);
		type = false;
		return typeGraphe;
	}

	@Override
	public void visit(PCM pcm) {
		if (clearAndSet || print) {
			for (Product prod : pcm.getProducts()) {
				prod.accept(this);
			}

			for (Feature feat : pcm.getConcreteFeatures()) {
				feat.accept(this);
			}
		} else if (radar) {
			for (Product prod : pcm.getProducts()) {
				System.out.println("prod key content : "+prod.getKeyContent());
				if(prod.getKeyContent().equals(product.trim().toLowerCase())){
				prod.accept(this);
				}
			}
		} else if (type) {
			pcm.getOrCreateFeature(featureType, factory).accept(this);;
		}
		else {
			
		}

	}

	@Override
	public void visit(Feature feature) {
		if (clearAndSet) {
			feature.setName(feature.getName().trim().toLowerCase());

			if (feature.getName() != null && !feature.getName().equals(nomFeatureProduit)) {
				features.add(feature.getName());
			}

			for (Cell cell : feature.getCells()) {
				cell.accept(this);
			}

		} else if (print) {
			System.out.println(feature.getName());
		} else if (type){
			Iterator<Cell>it = feature.getCells().iterator();
			Cell c = it.next();
			c.accept(this);
		}

	}

	@Override
	public void visit(FeatureGroup featureGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Product product) {
		if (clearAndSet) {
			nomFeatureProduit = product.getKey().getName().trim().toLowerCase();
			produits.add(product.getKeyContent());
		} else if (print) {
			System.out.println(product.getKeyContent());
		} else if (radar) {
			for (Cell cell : product.getCells()) {
				cell.accept(this);
			}
		} else {

		}
	}

	@Override
	public void visit(Cell cell) {
		//Set le type de la cell
		Value val = cell.getInterpretation();
		typeCell = "";
		val.accept(this);
		
		if (clearAndSet) {

			String content = cell.getContent();
			// Set content si vide
			if (content == null) {
				if (typeCell.equals("boolean")) {
					cell.setContent("empty");
				} else if (typeCell.equals("integer")) {
					cell.setContent("empty");
				} else if (typeCell.equals("real")) {
					cell.setContent("empty");
				} else if (typeCell.equals("date")) {
					cell.setContent("empty");
				} else if (typeCell.equals("string")) {
					cell.setContent("empty");
				}
			}
			// Si content non vide le corrige si besoin
			else {
				String nouvContent = "";
				// Minuscule et sans espace
				nouvContent = (content.trim().toLowerCase());

				if (typeCell.equals("boolean")) {
					if (!nouvContent.equals("true") && !nouvContent.equals("false")) {
						// Si non boolean
						nouvContent = "nodef";
					}
				} else if (typeCell.equals("integer") || typeCell.equals("real")) {
					// Prendre que les integer du string
					String newContent = "";
					for (int i = 0; i < nouvContent.length(); i++) {
						if (Character.isDigit(content.charAt(i))) {
							newContent += nouvContent.charAt(i);
						}
					}
					if (newContent.equals("")) {
						newContent = "0";
					}
					nouvContent = newContent;

					if (typeCell.equals("integer")) {
						// Set le max pour les integer
						String featName = cell.getFeature().getName();
						int ii = Integer.parseInt(nouvContent);
						if (max.containsKey(featName)) {

							if (ii > (max.get(featName))) {
								max.put(featName, ii);
							}
						} else {
							max.put(featName, ii);
						}
					}
					
				}
				cell.setContent(nouvContent);
			}
			
		} else if (radar) {
			//Radar = true , product = "produit voulu"

			if (typeCell.equals("boolean") || typeCell.equals("integer") ) {
				// nouvContent -> pourcentage en fction du max ou 100 si true
				String nouvContent = "";
				if (typeCell.equals("boolean")) {
					if (cell.getContent().equals("true")) {
						nouvContent = "100";
					} else if (cell.getContent().equals("false")) {
						nouvContent = "0";
					} else {
						nouvContent = "50";
					}
				} else {
					double pourc = (Integer.parseInt(cell.getContent()));
					int maxx = max.get(cell.getFeature().getName());
					pourc = pourc / maxx;
					pourc = pourc * 100;
					nouvContent = (int)pourc + "";
				}
				String[] tab = { cell.getFeature().getName(), nouvContent };
				valueProduct.add(tab);
			}
			else {
				System.out.println(typeCell);
			}
		} else if (type) {
			
			if (typeCell.equals("boolean")) {
				typeGraphe.add("camembert");
			} else if (typeCell.equals("integer")) {
				typeGraphe.add("baton");
				typeGraphe.add("ligne");
			} else if (typeCell.equals("real")) {
				typeGraphe.add("baton");
				typeGraphe.add("ligne");
			} else if (typeCell.equals("date")) {
				typeGraphe.add("baton");
			} else if (typeCell.equals("string")) {
				//camembert et baton
				typeGraphe.add("camembert");
				typeGraphe.add("baton");
			}
		}
	}

	@Override
	public void visit(BooleanValue booleanValue) {
		if (clearAndSet || radar) {
			typeCell = "boolean";
		}

	}

	@Override
	public void visit(Conditional conditional) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(DateValue dateValue) {
		if (clearAndSet || radar) {
			typeCell = "date";
		}

	}

	@Override
	public void visit(Dimension dimension) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IntegerValue integerValue) {
		if (clearAndSet || radar) {
			typeCell = "integer";
		}
	}

	@Override
	public void visit(Multiple multiple) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NotApplicable notApplicable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NotAvailable notAvailable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Partial partial) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RealValue realValue) {
		if (clearAndSet || radar) {
			typeCell = "real";
		}
	}

	@Override
	public void visit(StringValue stringValue) {
		if (clearAndSet || radar) {
			typeCell = "string";
		}
	}

	@Override
	public void visit(Unit unit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Version version) {
		// TODO Auto-generated method stub

	}

}
