package tests;

import junit.framework.Assert;

import main.WindCell;
import main.WindMatrix;

import org.junit.Test;

public class FixtureWindCell {

	@Test
	public void neighboursAreZero_centreUpdatesToZero() {
		WindMatrix matrix = new MatrixMaker().setCell(1, 1, 2, 3).matrix();
		WindCell centre = matrix.getCell(1,1);
		
		centre.update();
		
		Assert.assertEquals(0.0f, centre.get_wind().x);
	}

	@Test
	public void leftNeighboursIsOne_centreUpdatesToOne() {
		WindMatrix matrix = new MatrixMaker().setCell(1, 1, 2, 3).setCell(0, 1, 1, 0).matrix();
		WindCell centre = matrix.getCell(1, 1);
		
		centre.update();
		
		Assert.assertEquals(1.0f, centre.get_wind().x);
	}

	@Test
	public void getLeftNeighbour() {
		WindMatrix matrix = new MatrixMaker().setCell(1, 1, 2, 3).setCell(0, 1, 1, 0).matrix();
		WindCell centre = matrix.getCell(1, 1);
		
		WindCell left = centre.getLeft();
		
		Assert.assertEquals(1.0f, left.get_wind().x);
	}

}
