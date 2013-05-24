package tests;

import junit.framework.Assert;

import org.junit.Test;

import wind.WindMatrices;

public class FixtureIntegration {

	@Test
	public void moveVectorAlong() {
		// set up a matrix with a vector
		WindMatrices matrices = new MatrixMaker(10,5).setCell(2, 2, 11, 0).matrices();
//		Utils.printMatrix(matrices.currentMatrix(), "after setup");
		
		// check where the vector is
		Assert.assertEquals(11.0f, matrices.currentMatrix().getCell(2, 2).get_wind().x);
		Assert.assertEquals(0.0f, matrices.currentMatrix().getCell(3, 2).get_wind().x);
		Assert.assertEquals(0.0f, matrices.currentMatrix().getCell(4, 2).get_wind().x);

		// update and flip
		matrices.updateCells();
		matrices.flipMatrices();
		
		// check the vector has moved
//		Utils.printMatrix(matrices.currentMatrix(), "after first flip - current");
//		Utils.printMatrix(matrices.otherMatrix(), "after first flip - other");
		Assert.assertEquals(0.0f, matrices.currentMatrix().getCell(2, 2).get_wind().x);
		Assert.assertEquals(11.0f, matrices.currentMatrix().getCell(3, 2).get_wind().x);
		Assert.assertEquals(0.0f, matrices.currentMatrix().getCell(4, 2).get_wind().x);
		
		// update and flip
		matrices.updateCells();
		matrices.flipMatrices();
		
		// check the vector has moved
//		Utils.printMatrix(matrices.currentMatrix(), "after second flip");
//		Assert.assertEquals(0.0f, matrices.currentMatrix().getCell(2, 2).get_wind().x);
//		Assert.assertEquals(0.0f, matrices.currentMatrix().getCell(3, 2).get_wind().x);
//		Assert.assertEquals(11.0f, matrices.currentMatrix().getCell(4, 2).get_wind().x);
	}

}
