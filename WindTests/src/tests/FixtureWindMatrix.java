package tests;

import org.junit.Assert;
import org.junit.Test;

import processing.core.PVector;

import wind.CellBase;
import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;

public class FixtureWindMatrix {
	
	@Test
	public void noWind() {
		WindMatrix matrix = new MatrixMaker().matrices().currentGenMatrix();
		
		Assert.assertEquals(0.0f, matrix.getCell(1, 1).getWind().x, 0);
		Assert.assertEquals(0.0f, matrix.getCell(1, 1).getWind().y, 0);
	}

	@Test
	public void someWind() throws Exception {
		WindMatrix matrix = new MatrixMaker().centre(1,1).matrices().currentGenMatrix();
		
		Assert.assertNotSame(0.0f, matrix.getCell(1, 1).getWind().x);
		Assert.assertNotSame(0.0f, matrix.getCell(1, 1).getWind().y);
	}

	@Test
	public void getCellFromMatrix() {
		WindMatrix matrix = new MatrixMaker().setCell(0,1,1,1).matrices().currentGenMatrix();
		CellBase left = matrix.getCell(0,1);
		Assert.assertEquals(1.0f, left.getWind().x, 0);
	}

	@Test
	public void getLeft() {
		WindMatrix matrix = new MatrixMaker().setCell(0,1,1,1).matrices().currentGenMatrix();
		CellBase left = matrix.getLeft(1,1);
		Assert.assertEquals(1.0f, left.getWind().x, 0);
	}

	@Test
	public void outOfBounds() {
		WindMatrices matrices = new MatrixMaker().matrices();
		WindMatrix current = matrices.currentGenMatrix();
		int colMax = current.getCells().length;
		int rowMax = current.getCells()[0].length;
		Assert.assertFalse(current.indexInBounds(-1, 0));
		Assert.assertFalse(current.indexInBounds(0, rowMax));
		Assert.assertFalse(current.indexInBounds(0, -1));
		Assert.assertFalse(current.indexInBounds(colMax, 0));
	}

	@Test
	public void inBounds() {
		WindMatrices matrices = new MatrixMaker().matrices();
		WindMatrix current = matrices.currentGenMatrix();
		int colMax = current.getCells().length;
		int rowMax = current.getCells()[0].length;
		Assert.assertTrue(current.indexInBounds(0, 1));
		Assert.assertTrue(current.indexInBounds(1, 0));
		Assert.assertTrue(current.indexInBounds(0, rowMax-1));
		Assert.assertTrue(current.indexInBounds(colMax-1, 0));
	}
	
	@Test
	public void currentAndOtherMatrix()
	{
		WindMatrices matrix = new MatrixMaker().matrices();
		Assert.assertEquals(0, matrix.currentMatrixIndex());
		Assert.assertEquals(1, matrix.nextGenMatrixIndex());
		matrix.flipMatrices();
		Assert.assertEquals(1, matrix.currentMatrixIndex());
		Assert.assertEquals(0, matrix.nextGenMatrixIndex());
	}

	@Test
	public void flipCurrentMatrix()
	{
		WindMatrices matrices = new MatrixMaker().setCell(0,1,1,1).matrices();
		
		Assert.assertEquals(1.0f, matrices.currentGenMatrix().getLeft(1,1).getWind().x, 0);
		Assert.assertEquals(0.0f, matrices.nextGenMatrix().getLeft(1,1).getWind().x, 0);
		matrices.flipMatrices();
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getLeft(1,1).getWind().x, 0);
		Assert.assertEquals(1.0f, matrices.nextGenMatrix().getLeft(1,1).getWind().x, 0);
		matrices.flipMatrices();
		Assert.assertEquals(1.0f, matrices.currentGenMatrix().getLeft(1,1).getWind().x, 0);
		Assert.assertEquals(0.0f, matrices.nextGenMatrix().getLeft(1,1).getWind().x, 0);

	}

	@Test
	public void clearNext()
	{
		WindMatrices matrices = new MatrixMaker().setOtherCell(1,1,1,1).matrices();
		CellBase[][] otherCells = matrices.nextGenMatrix().getCells();
		
		Assert.assertEquals(1.0f, otherCells[1][1].getWind().x, 0);
		matrices.clearNextGenCell(1,1);
		Assert.assertEquals(0.0f, otherCells[1][1].getWind().x, 0);
	}
	
	@Test
	public void canChangeHeading(){
		PVector v = new PVector(1,0);
		Assert.assertEquals(0, v.heading(),0);
		v.rotate(1);
		Assert.assertEquals(1, v.heading(),0);
		v.rotate(1);
		Assert.assertEquals(2, v.heading(),0);
		v.rotate(1);
		Assert.assertEquals(3, v.heading(),0);
		v.rotate(1);
		Assert.assertEquals((float)(4-2*Math.PI), v.heading(),0);
		
		WindCell c = new WindCell(1, 0);
		c.getWind().x = 1;
		Assert.assertEquals(0.0f, c.getWind().heading(), 0);
		c.changeHeading( 1.0f);
		Assert.assertEquals(1.0f, c.getWind().heading(), 0);
		c.changeHeading( 2.0f);
		Assert.assertEquals(3.0f, c.getWind().heading(), 0);
		c.changeHeading( -2.0f);
		Assert.assertEquals(1.0f, c.getWind().heading(), 0);
		
	}

}
