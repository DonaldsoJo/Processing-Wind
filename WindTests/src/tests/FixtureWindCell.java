package tests;

import junit.framework.Assert;


import org.junit.Test;

import wind.WindCell;
import wind.WindMatrix;

public class FixtureWindCell {

	@Test
	public void neighboursAreZero_centreUpdatesToZero() {
		WindMatrix matrix = new MatrixMaker().centre(2, 3).matrix();
		WindCell centre = matrix.getCell(1,1);
		
		centre.update();
		
		Assert.assertEquals(0.0f, centre.get_wind().x);
	}

	@Test
	public void leftNeighboursIsOne_centreUpdatesToOne() {
		WindMatrix matrix = new MatrixMaker().centre(2, 3).setCell(0, 1, 1, 0).matrix();
		WindCell centre = matrix.getCell(1, 1);
		
		centre.update();
		
		Assert.assertEquals(1.0f, centre.get_wind().x);
	}

	@Test
	public void getLeftNeighbour() {
		WindMatrix matrix = new MatrixMaker().centre(2, 3).setCell(0, 1, 1, 0).matrix();
		WindCell centre = matrix.getCell(1, 1);
		
		WindCell left = centre.getLeft();
		
		Assert.assertEquals(1.0f, left.get_wind().x);
	}

	@Test
	public void getRightNeighbour() {
		WindMatrix matrix = new MatrixMaker().centre(2, 3).setCell(2, 1, 1, 0).matrix();
		WindCell centre = matrix.getCell(1, 1);
		
		WindCell right = centre.getRight();
		
		Assert.assertEquals(1.0f, right.get_wind().x);
	}

}
