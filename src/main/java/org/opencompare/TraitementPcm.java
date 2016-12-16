package org.opencompare;

import java.util.ArrayList;
import java.util.List;

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

	private String nomFeatureProduit ="";

	private static PCMFactory factory = null;
	private static PCM clone = null;
	private boolean print = false;
	private boolean clearAndSet = false;
	
	private boolean radar = false;
	private String product ="";
	private List<String>valueProduct;

	private static String typeCell = "";

	private static List<String> produits = new ArrayList<String>();
	private static List<String> features = new ArrayList<String>();

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
	
	public List<String> getProduitForRadar(String prod){
		radar = true;
		product = prod;
		valueProduct=new ArrayList<String>();
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
	
	public static List<String> getProduits() {
		return produits;
	}

	public static List<String> getFeatures() {
		return features;
	}

	@Override
	public void visit(PCM pcm) {
		if (clearAndSet || print){
			for (Product prod : pcm.getProducts()) {
				prod.accept(this);
			}
	
			for (Feature feat : pcm.getConcreteFeatures()) {
				feat.accept(this);
			}
		}
		else if (radar){
			for (Product prod : pcm.getProducts()){
				prod.accept(this);
			}
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
		} else {

		}

	}

	@Override
	public void visit(FeatureGroup featureGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Product product) {
		if (clearAndSet) {
			nomFeatureProduit=product.getKey().getName().trim().toLowerCase();
			produits.add(product.getKeyContent());
		} else if (print) {
			System.out.println(product.getKeyContent());
		} else if (radar) {
			for (Cell cell : product.getCells()){
				cell.accept(this);
			}
		}
		else {
			
		}
	}

	@Override
	public void visit(Cell cell) {
		if (clearAndSet) {
			Value val = cell.getInterpretation();
			typeCell ="";
			val.accept(this);
			if (cell.getContent()==null){
				if (typeCell.equals("boolean")){
					cell.setContent("empty");
				}
				else if (typeCell.equals("integer")){
					cell.setContent("empty");
				}
				else if (typeCell.equals("real")){
					cell.setContent("empty");
				}
				else if (typeCell.equals("date")){
					cell.setContent("empty");
				}
				else if (typeCell.equals("string")){
					cell.setContent("empty");
				}
			}
			else {
				cell.setContent(cell.getContent().trim().toLowerCase());
			}
		}
		else if (radar){
			valueProduct.add(cell.getContent());
		}
		else {
			
		}
	}

	@Override
	public void visit(BooleanValue booleanValue) {
		if (clearAndSet){
			typeCell="boolean";
		}

	}

	@Override
	public void visit(Conditional conditional) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(DateValue dateValue) {
		if (clearAndSet){
			typeCell="date";
		}

	}

	@Override
	public void visit(Dimension dimension) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IntegerValue integerValue) {
		if (clearAndSet){
			typeCell="integer";
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
		if (clearAndSet){
			typeCell="real";
		}
	}

	@Override
	public void visit(StringValue stringValue) {
		if (clearAndSet){
			typeCell="string";
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
