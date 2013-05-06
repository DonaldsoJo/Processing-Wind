package tests;

import junit.framework.Assert;

import main.WindCell;

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


}
