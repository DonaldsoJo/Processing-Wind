package tests;

import org.junit.Assert;

import org.junit.Test;

import processing.core.PVector;

import wind.BaseMatrices;
import wind.NeighbourhoodMatrices;
import wind.NeighbourhoodMatrix;
import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;

public class FixtureNeighbours {

	@Test
	public void getNeighboursFromMiddle() {
		WindMatrices matrices = new MatrixMaker(5, 8).matrices();
		WindMatrix current = updateCurrent(matrices);
//		Utils.printMatrix(current, "current");
		NeighbourhoodMatrix neighbours = (NeighbourhoodMatrix) matrices.getNeighbours( new WindCell(2, 5)).currentGenMatrix();
//		Utils.printMatrix(neighbours.currentMatrix(), "neighbours");
		
		Assert.assertEquals(1.0f, neighbours.topLeft().get_wind().x, 0);
		Assert.assertEquals(4.0f, neighbours.topLeft().get_wind().y, 0);
		/*
		Assert.assertEquals(2.0f, neighbours.topMiddle().get_wind().x);
		Assert.assertEquals(4.0f, neighbours.topMiddle().get_wind().y);
		
		Assert.assertEquals(3.0f, neighbours.topRight().get_wind().x);
		Assert.assertEquals(4.0f, neighbours.topRight().get_wind().y);
		
		Assert.assertEquals(1.0f, neighbours.left().get_wind().x);
		Assert.assertEquals(5.0f, neighbours.left().get_wind().y);

		Assert.assertEquals(2.0f, neighbours.centre().get_wind().x);
		Assert.assertEquals(5.0f, neighbours.centre().get_wind().y);

		Assert.assertEquals(3.0f, neighbours.right().get_wind().x);
		Assert.assertEquals(5.0f, neighbours.right().get_wind().y);

		Assert.assertEquals(1.0f, neighbours.bottomLeft().get_wind().x);
		Assert.assertEquals(6.0f, neighbours.bottomLeft().get_wind().y);
		
		Assert.assertEquals(2.0f, neighbours.bottomMiddle().get_wind().x);
		Assert.assertEquals(6.0f, neighbours.bottomMiddle().get_wind().y);
		
		Assert.assertEquals(3.0f, neighbours.bottomRight().get_wind().x);
		Assert.assertEquals(6.0f, neighbours.bottomRight().get_wind().y);
		*/
	}

	public WindMatrix updateCurrent(WindMatrices matrices) {
		WindMatrix current = matrices.currentGenMatrix();
		for( int col=0; col<current.getCells().length; col++)
		{
			WindCell[] cells = current.getCells()[col];
			for (int row=0; row<cells.length; row++)
			{
				PVector v = cells[row].get_wind();
				v.x = col;
				v.y = row;
			}
		}
		return current;
	}

	@Test
	public void neighboursAreZero_centreUpdatesToZero() throws Exception {
		NeighbourhoodMatrices neighbours = new MatrixMaker().centre(2, 3).neighbours(1,1);
		neighbours.update();
		Assert.assertEquals(0.0f, neighbours.nextGenMatrix().getCell(1, 1).get_wind().x, 0);
	}

	@Test
	public void leftNeighboursIsOne_centreUpdatesToOne() throws Exception {
		NeighbourhoodMatrices neighbours = new MatrixMaker().centre(2, 3).setCell(0, 1, 1, 0).neighbours(1,1);
		Assert.assertEquals(0.0f, neighbours.nextGenMatrix().getCell(1, 1).get_wind().x, 0);
		neighbours.update();
		Assert.assertEquals(0.8f, neighbours.nextGenMatrix().getCell(1, 1).get_wind().x, 0);
	}

	@Test
	public void updateNeightboursMatrices_updatesAssociatedWindMatrix() throws Exception {
		
		WindMatrices matrices = new MatrixMaker(5,5).setCell(1, 2, 11, 0).matrices();
		NeighbourhoodMatrices neighbours = matrices.getNeighbours(new WindCell(2,2));
		Assert.assertEquals(0.0f, neighbours.nextGenMatrix().getCell(1, 1).get_wind().x, 0);
		
		neighbours.update();
		
//		Utils.printMatrix(neighbours.currentMatrix(), "neighbours current");
//		Utils.printMatrix(neighbours.otherMatrix(), "neighbours other");
//		Utils.printMatrix(matrices.currentMatrix(), "matrices current");
//		Utils.printMatrix(matrices.otherMatrix(), "matrices other");

		Assert.assertEquals(8.8f, neighbours.nextGenMatrix().getCell(1, 1).get_wind().x, 0);
		Assert.assertEquals(8.8f, matrices.nextGenMatrix().getCell(2, 2).get_wind().x, 0);
	}

	@Test
	public void writeToOtherNeighbours_getSameBack() throws Exception {
		
		WindMatrices matrices = new MatrixMaker(5,5).setCell(1, 1, 11, 0).matrices();
		BaseMatrices neighbours = matrices.getNeighbours(new WindCell(1,1));
		
//		Utils.printMatrix(neighbours.currentMatrix(), "neighbours current");
//		Utils.printMatrix(neighbours.otherMatrix(), "neighbours other");
//		Utils.printMatrix(matrices.currentMatrix(), "matrices current");
//		Utils.printMatrix(matrices.otherMatrix(), "matrices other");
		
		Assert.assertEquals(0.0f, neighbours.nextGenMatrix().getCell(1, 1).get_wind().x, 0);
		
		neighbours.nextGenMatrix().setCell(1, 1, 12, 6);
		
//		Utils.printMatrix(neighbours.currentMatrix(), "neighbours current");
//		Utils.printMatrix(neighbours.otherMatrix(), "neighbours other");
//		Utils.printMatrix(matrices.currentMatrix(), "matrices current");
//		Utils.printMatrix(matrices.otherMatrix(), "matrices other");

		Assert.assertEquals(12.0f, neighbours.nextGenMatrix().getCell(1, 1).get_wind().x, 0);
		Assert.assertEquals(12.0f, matrices.nextGenMatrix().getCell(1, 1).get_wind().x, 0);		
	}
	
	@Test
	public void cloneNeighbours() {
		NeighbourhoodMatrix m = new NeighbourhoodMatrix();
		NeighbourhoodMatrix n = m.cloneMatrix();
		Assert.assertNotSame(m,n);
	}
	
	@Test
	public void clonedNeighbourCopiesVectorValues() {
		NeighbourhoodMatrix m = new NeighbourhoodMatrix();
		m.setCell(1, 1, 11, 12);
		NeighbourhoodMatrix n = m.cloneMatrix();
		Assert.assertEquals(m.getCell(1, 1).get_wind().x, n.getCell(1, 1).get_wind().x, 0);
	}
	
	@Test
	public void clonedNeighbourCopiesCellCoordinates() {
		NeighbourhoodMatrix m = new NeighbourhoodMatrix();
		NeighbourhoodMatrix n = m.cloneMatrix();
		Assert.assertEquals(m.getCell(1, 1).getCol(), n.getCell(1, 1).getCol());
	}
	
	@Test
	public void clonedNeighbourHasDifferentVectors() {
		NeighbourhoodMatrix m = new NeighbourhoodMatrix();
		NeighbourhoodMatrix n = m.cloneMatrix();
		Assert.assertNotSame(m.getCell(1, 1).get_wind(), n.getCell(1, 1).get_wind());
	}
	

}
