package org.opencompare;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.FeatureGroup;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMElement;
import org.opencompare.api.java.PCMFactory;
import org.opencompare.api.java.Product;
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

	private static PCMFactory factory = null;
	private static PCM clone = null;
	private boolean print = false;

	// Clear le pcm pour avoir des cellules claires
	public PCM clear(PCM pcm) {
		System.out.println("DEBUT TRAITEMENT");
		clone = pcm;
		pcm.accept(this);
		return clone;
	}

	public void printLocal() {
		System.out.println("Print pcm local");
		print = true;
		clone.accept(this);
		print = false;
	}

	@Override
	public void visit(PCM pcm) {
		//No need si on parcourt deja toutes les features
		for (Product prod : pcm.getProducts()) {
			prod.accept(this);
		}

		for (Feature feat : pcm.getConcreteFeatures()) {
			feat.accept(this);
		}

	}

	@Override
	public void visit(Feature feature) {
		if (!print) {
			feature.setName(feature.getName().trim().toLowerCase());
			clone.addFeature(feature);
		} else {
			System.out.println(feature.getName());
		}

	}

	@Override
	public void visit(FeatureGroup featureGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Product product) {
		if (!print) {
			for (Cell cell : product.getCells()){
				cell.accept(this);
			}
		} else {
			System.out.println(product.getKeyContent());
		}
	}

	@Override
	public void visit(Cell cell) {

	}

	@Override
	public void visit(BooleanValue booleanValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Conditional conditional) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(DateValue dateValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Dimension dimension) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IntegerValue integerValue) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StringValue stringValue) {
		// TODO Auto-generated method stub

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
