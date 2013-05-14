package tests;

import java.io.Console;

import junit.framework.Assert;


import org.junit.Test;

import wind.WindCell;
import wind.WindMatrix;



public class FixtureWindMatrix {
	
	@Test
	public void noWind() {
		WindMatrix matrix = new MatrixMaker().matrix();
		
		Assert.assertEquals(0.0f, matrix.getCell(1, 1).get_wind().x);
		Assert.assertEquals(0.0f, matrix.getCell(1, 1).get_wind().y);
	}

	@Test
	public void someWind() {
		WindMatrix matrix = new MatrixMaker().centre(1,1).matrix();
		
		Assert.assertNotSame(0.0f, matrix.getCell(1, 1).get_wind().x);
		Assert.assertNotSame(0.0f, matrix.getCell(1, 1).get_wind().y);
	}

	@Test
	public void getCellFromMatrix() {
		WindMatrix matrix = new MatrixMaker().setCell(0,1,1,1).matrix();
		WindCell left = matrix.getCell(0,1);
		Assert.assertEquals(1.0f, left.get_wind().x);
	}

	@Test
	public void getLeft() {
		WindMatrix matrix = new MatrixMaker().setCell(0,1,1,1).matrix();
		WindCell left = matrix.getLeft(1,1);
		Assert.assertEquals(1.0f, left.get_wind().x);
	}

	@Test
	public void outOfBounds() {
		WindMatrix matrix = new MatrixMaker().matrix();
		int colMax = matrix.cells().length;
		int rowMax = matrix.cells()[0].length;
		Assert.assertFalse(matrix.indexInBounds(-1, 0));
		Assert.assertFalse(matrix.indexInBounds(0, rowMax));
		Assert.assertFalse(matrix.indexInBounds(0, -1));
		Assert.assertFalse(matrix.indexInBounds(colMax, 0));
	}

	@Test
	public void inBounds() {
		WindMatrix matrix = new MatrixMaker().matrix();
		int colMax = matrix.cells().length;
		int rowMax = matrix.cells()[0].length;
		Assert.assertTrue(matrix.indexInBounds(0, 1));
		Assert.assertTrue(matrix.indexInBounds(1, 0));
		Assert.assertTrue(matrix.indexInBounds(0, rowMax-1));
		Assert.assertTrue(matrix.indexInBounds(colMax-1, 0));
	}
	
	@Test
	public void currentAndOtherMatrix()
	{
		WindMatrix matrix = new MatrixMaker().matrix();
		Assert.assertEquals(0, matrix.currentMatrixIndex());
		Assert.assertEquals(1, matrix.otherMatrixIndex());
		matrix.flipMatrix();
		Assert.assertEquals(1, matrix.currentMatrixIndex());
		Assert.assertEquals(0, matrix.otherMatrixIndex());
	}

	@Test
	public void flipCurrentMatrix()
	{
		WindMatrix current = new MatrixMaker().setCell(0,1,1,1).matrix();
		Assert.assertEquals(1.0f, current.getLeft(1,1).get_wind().x);
		current.flipMatrix();
		Assert.assertEquals(0.0f, current.getLeft(1,1).get_wind().x);
		current.flipMatrix();
		Assert.assertEquals(1.0f, current.getLeft(1,1).get_wind().x);

	}

	@Test
	public void clearNext()
	{
		WindMatrix current = new MatrixMaker().setOtherCell(1,1,1,1).matrix();
		WindCell[][] otherCells = current.otherCells();
		
		Assert.assertEquals(1.0f, otherCells[1][1].get_wind().x);
		current.clearNext(1, 1);
		Assert.assertEquals(0.0f, otherCells[1][1].get_wind().x);
		
	}

}
