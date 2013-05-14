package tests;

import junit.framework.Assert;


import org.junit.Test;

import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;

public class FixtureWindCell {

	@Test
	public void neighboursAreZero_centreUpdatesToZero() throws Exception {
		WindMatrices matrices = new MatrixMaker().centre(2, 3).left(4, 5).matrices();
		
		WindCell centre = matrices.currentMatrix().getCell(1,1);
		Assert.assertEquals(2.0f, centre.get_wind().x);
		WindCell otherCell = matrices.otherMatrix().getCell(1,1);
		Assert.assertEquals(0.0f, otherCell.get_wind().x);

		centre.update();
		
		Assert.assertEquals(2.0f, centre.get_wind().x);
		Assert.assertEquals(4.0f, otherCell.get_wind().x);
	}

	@Test
	public void leftNeighboursIsOne_centreUpdatesToOne() throws Exception {
		WindMatrices matrices = new MatrixMaker().centre(2, 3).setCell(0, 1, 1, 0).matrices();
		WindCell centre = matrices.currentMatrix().getCell(1, 1);
		
		centre.update();
		
		WindCell otherCell = matrices.otherMatrix().getCell(1, 1);
		Assert.assertEquals(1.0f, otherCell.get_wind().x);
	}

	@Test
	public void getLeftNeighbour() throws Exception {
		WindMatrices matrices = new MatrixMaker().centre(2, 3).setCell(0, 1, 1, 0).matrices();
		WindCell centre = matrices.currentMatrix().getCell(1, 1);
		
		WindCell left = centre.getLeft();
		Assert.assertEquals(1.0f, left.get_wind().x);
	}

	@Test
	public void getRightNeighbour() throws Exception {
		WindMatrices matrices = new MatrixMaker().centre(4, 3).setCell(2, 1, 1, 3).matrices();
		WindCell centre = matrices.currentMatrix().getCell(1, 1);
		
		WindCell right = centre.getRight();
		Assert.assertEquals(1.0f, right.get_wind().x);
	}


}
