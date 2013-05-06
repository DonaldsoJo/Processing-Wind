package tests;

import junit.framework.Assert;

import main.WindCell;

import org.junit.Test;

public class FixtureWindCell {

	@Test
	public void neighboursAreZero_centreUpdatesToZero() {
		WindCell[][] cells = new CellMaker().setCell(1, 1, 2, 3).cells();
		WindCell centre = cells[1][1];
		
		centre.update();
		
		Assert.assertEquals(0.0f, centre.get_wind().x);
	}

	@Test
	public void leftNeighboursIsOne_centreUpdatesToOne() {
		WindCell[][] cells = new CellMaker().setCell(1, 1, 2, 3).setCell(0, 1, 1, 0).cells();
		WindCell centre = cells[1][1];
		
		centre.update();
		
		Assert.assertEquals(1.0f, centre.get_wind().x);
	}

}
