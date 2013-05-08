package tests;

import junit.framework.Assert;

import main.WindCell;
import main.WindMatrix;

import org.junit.Test;



public class FixtureWindArray {
	
	@Test
	public void noWind() {
		
		WindCell[][] cells = new CellMaker().cells();
		
		Assert.assertEquals(0.0f, cells[1][1].get_wind().x);
		Assert.assertEquals(0.0f, cells[1][1].get_wind().y);
		
	}

	@Test
	public void someWind() {
		
		WindCell[][] cells = new CellMaker().setCell(1,1,1,1).cells();
		
		Assert.assertNotSame(0.0f, cells[1][1].get_wind().x);
		Assert.assertNotSame(0.0f, cells[1][1].get_wind().y);
		
	}

	@Test
	public void getCellFromMatrix() {
		
		WindCell[][] cells = new CellMaker().setCell(0,1,1,1).cells();
		
		WindMatrix matrix = new WindMatrix(cells);
		WindCell left = matrix.getCell(0,1);
		
		Assert.assertEquals(1.0f, left.get_wind().x);
	}

	@Test
	public void getLeft() {
		
		WindCell[][] cells = new CellMaker().setCell(0,1,1,1).cells();
		
		WindMatrix matrix = new WindMatrix(cells);
		WindCell left = matrix.getLeft(1,1);
		
		Assert.assertEquals(1.0f, left.get_wind().x);
	}


}
