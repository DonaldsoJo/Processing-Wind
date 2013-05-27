package tests;

import junit.framework.Assert;

import org.junit.Test;

import wind.AlgorithmFractionalFlowAndSideSpills;
import wind.WindMatrices;

public class FixtureIntegration {

	@Test
	public void moveVectorAlong() {
		// set up a matrix with a vector
		WindMatrices matrices = new MatrixMaker(10,5).setCell(2, 2, 11, 0).matrices();
//		Utils.printMatrix(matrices.currentMatrix(), "after setup");
		
//		System.out.println("Energy " + matrices.currentMatrix().energy());
		
		// check where the vector is
		Assert.assertEquals(11.0f, matrices.currentGenMatrix().getCell(2, 2).get_wind().x);
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getCell(3, 2).get_wind().x);
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getCell(4, 2).get_wind().x);

		// update and flip
		matrices.updateCells();
		matrices.flipMatrices();
//		System.out.println("Energy " + matrices.currentMatrix().energy());
		
		// check the vector has moved
//		Assert.assertEquals(0.0f, matrices.currentMatrix().getCell(2, 2).get_wind().x);
//		Assert.assertEquals(11.0f, matrices.currentMatrix().getCell(3, 2).get_wind().x);
//		Assert.assertEquals(0.0f, matrices.currentMatrix().getCell(4, 2).get_wind().x);
		
		// update and flip
		matrices.updateCells();
		matrices.flipMatrices();
//		System.out.println("Energy " + matrices.currentMatrix().energy());
		
	}

	@Test
	public void conservationOfEnergy() {
		// set up a matrix with a vector
		WindMatrices matrices = new MatrixMaker(5,5)
			.setAlgorithm(new AlgorithmFractionalFlowAndSideSpills())
			.setCell(2, 2, 10, 0).matrices();
		
//		System.out.println("Alogirthm=" + matrices.getAlgorithm().toString());
		
//		Utils.printMatrixNonZeroValues(matrices.currentMatrix(), "after setup");
		
//		System.out.println("Energy " + matrices.currentMatrix().energy());
		Assert.assertEquals(10.0f, matrices.currentGenMatrix().energy());
		
		// update and flip
		matrices.updateCells();
		matrices.flipMatrices();
//		System.out.println("Energy " + matrices.currentMatrix().energy());
//		Assert.assertEquals(10.0f, matrices.currentMatrix().energy());
//		Utils.printMatrixNonZeroValues(matrices.currentMatrix(), "after first update");
		
		// update and flip
		matrices.updateCells();
		matrices.flipMatrices();
//		System.out.println("Energy " + matrices.currentMatrix().energy());
//		Assert.assertEquals(10.0f, matrices.currentMatrix().energy());
		
	}
	
	@Test
	public void afterFlipCurrentIsUpdated(){
		WindMatrices ms = new MatrixMaker(3,3).setAlgorithm(new AlgorithmFractionalFlowAndSideSpills()).setCell(0, 0, 10, 10).matrices();
//		Utils.printMatrixNonZeroValues(ms.currentMatrix(), "Current - before update");
//		Utils.printMatrixNonZeroValues(ms.otherMatrix(), "Other - before update");
		ms.updateCells();
//		Utils.printMatrixNonZeroValues(ms.currentMatrix(), "Current - after update");
//		Utils.printMatrixNonZeroValues(ms.otherMatrix(), "Other - after update");
		ms.flipMatrices();
//		Assert.assertEquals(8.0f, ms.currentMatrix().getCell(1, 0).get_wind().x);
//		Assert.assertEquals(8.0f, ms.currentMatrix().getCell(1, 1).get_wind().x);
//		Assert.assertEquals(8.0f, ms.currentMatrix().getCell(1, 1).get_wind().mag());
	}

}
