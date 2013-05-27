package tests;

import junit.framework.Assert;
import org.junit.Test;

import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;



public class FixtureWindMatrix {
	
	@Test
	public void noWind() {
		WindMatrix matrix = new MatrixMaker().matrices().currentGenMatrix();
		
		Assert.assertEquals(0.0f, matrix.getCell(1, 1).get_wind().x);
		Assert.assertEquals(0.0f, matrix.getCell(1, 1).get_wind().y);
	}

	@Test
	public void someWind() throws Exception {
		WindMatrix matrix = new MatrixMaker().centre(1,1).matrices().currentGenMatrix();
		
		Assert.assertNotSame(0.0f, matrix.getCell(1, 1).get_wind().x);
		Assert.assertNotSame(0.0f, matrix.getCell(1, 1).get_wind().y);
	}

	@Test
	public void getCellFromMatrix() {
		WindMatrix matrix = new MatrixMaker().setCell(0,1,1,1).matrices().currentGenMatrix();
		WindCell left = matrix.getCell(0,1);
		Assert.assertEquals(1.0f, left.get_wind().x);
	}

	@Test
	public void getLeft() {
		WindMatrix matrix = new MatrixMaker().setCell(0,1,1,1).matrices().currentGenMatrix();
		WindCell left = matrix.getLeft(1,1);
		Assert.assertEquals(1.0f, left.get_wind().x);
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
		Assert.assertEquals(1, matrix.otherMatrixIndex());
		matrix.flipMatrices();
		Assert.assertEquals(1, matrix.currentMatrixIndex());
		Assert.assertEquals(0, matrix.otherMatrixIndex());
	}

	@Test
	public void flipCurrentMatrix()
	{
		WindMatrices matrices = new MatrixMaker().setCell(0,1,1,1).matrices();
		
		Assert.assertEquals(1.0f, matrices.currentGenMatrix().getLeft(1,1).get_wind().x);
		Assert.assertEquals(0.0f, matrices.nextGenMatrix().getLeft(1,1).get_wind().x);
		matrices.flipMatrices();
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getLeft(1,1).get_wind().x);
		Assert.assertEquals(1.0f, matrices.nextGenMatrix().getLeft(1,1).get_wind().x);
		matrices.flipMatrices();
		Assert.assertEquals(1.0f, matrices.currentGenMatrix().getLeft(1,1).get_wind().x);
		Assert.assertEquals(0.0f, matrices.nextGenMatrix().getLeft(1,1).get_wind().x);

	}

	@Test
	public void clearNext()
	{
		WindMatrices matrices = new MatrixMaker().setOtherCell(1,1,1,1).matrices();
		WindCell[][] otherCells = matrices.nextGenMatrix().getCells();
		
		Assert.assertEquals(1.0f, otherCells[1][1].get_wind().x);
		matrices.clearOtherCell(1,1);
		Assert.assertEquals(0.0f, otherCells[1][1].get_wind().x);
		
	}

}
