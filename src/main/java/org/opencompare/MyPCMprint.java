package org.opencompare;
import org.opencompare.api.java.*;
import org.opencompare.api.java.util.PCMVisitor;
import org.opencompare.api.java.value.*;

public class MyPCMprint implements PCMVisitor{
	
	private boolean isBooleanCell;

    /**
     * Print some information contained in a PCM
     * @param pcm: PCM to print
     */
    public void print(PCM pcm) {

        // We start by listing the names of the products
        System.out.println("--- Products ---");
        for (Product product : pcm.getProducts()) {
            System.out.println(product.getKeyContent());
        }

        // Then, we use a visitor to print the content of the cells that represent a boolean value
        System.out.println("--- Boolean values ---");
        pcm.accept(this);

    }

	public void visit(PCM pcm) {
        for (Product product : pcm.getProducts()) {
            product.accept(this);
        }
		
	}

	public void visit(Feature feature) {
		// TODO Auto-generated method stub
		
	}

	public void visit(FeatureGroup featureGroup) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Product product) {
        for (Cell cell : product.getCells()) {
            cell.accept(this);
        }
		
	}

	public void visit(Cell cell) {
		Value interpretation = cell.getInterpretation();

        // Visit the interpretation of the cell to check if it is a boolean
        isBooleanCell = false;
        if (interpretation != null) {
            interpretation.accept(this);
        }

        // Print content of the cell if it is a boolean
        if (isBooleanCell) {
            System.out.println(cell.getContent());
        }
		
	}

	public void visit(BooleanValue booleanValue) {
		isBooleanCell = true;
		
	}

	public void visit(Conditional conditional) {
		// TODO Auto-generated method stub
		
	}

	public void visit(DateValue dateValue) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Dimension dimension) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IntegerValue integerValue) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Multiple multiple) {
		// TODO Auto-generated method stub
		
	}

	public void visit(NotApplicable notApplicable) {
		// TODO Auto-generated method stub
		
	}

	public void visit(NotAvailable notAvailable) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Partial partial) {
		// TODO Auto-generated method stub
		
	}

	public void visit(RealValue realValue) {
		// TODO Auto-generated method stub
		
	}

	public void visit(StringValue stringValue) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Version version) {
		// TODO Auto-generated method stub
		
	}

}
