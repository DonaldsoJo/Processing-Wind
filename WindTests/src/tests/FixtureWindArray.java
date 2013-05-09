package tests;

import junit.framework.Assert;

import main.WindCell;
import main.WindMatrix;

import org.junit.Test;



public class FixtureWindArray {
	
	@Test
	public void noWind() {
		
		WindMatrix matrix = new MatrixMaker().matrix();
		
		Assert.assertEquals(0.0f, matrix.getCell(1, 1).get_wind().x);
		Assert.assertEquals(0.0f, matrix.getCell(1, 1).get_wind().y);
		
	}

	@Test
	public void someWind() {
		
		WindMatrix matrix = new MatrixMaker().setCell(1,1,1,1).matrix();
		
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


}
