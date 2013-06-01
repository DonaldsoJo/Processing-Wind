package tests;

import org.junit.Assert;
import org.junit.Test;

import wind.AlgorithmApplyTemplate;
import wind.TemplateMatrix;
import wind.WindMatrices;

public class FixtureIntegration {

	@Test
	public void moveVectorAlong() {
		// set up a matrix with a vector
		WindMatrices matrices = new MatrixMaker(10,5).setCell(2, 2, 11, 0).matrices();
//		Utils.printMatrix(matrices.currentMatrix(), "after setup");
		
//		System.out.println("Energy " + matrices.currentMatrix().energy());
		
		// check where the vector is
		Assert.assertEquals(11.0f, matrices.currentGenMatrix().getCell(2, 2).getWind().x, 0);
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getCell(3, 2).getWind().x, 0);
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getCell(4, 2).getWind().x, 0);

		// update and flip
		matrices.process();
		
		// check the vector has moved
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getCell(2, 2).getWind().x, 0);
		Assert.assertEquals(11.0f, matrices.currentGenMatrix().getCell(3, 2).getWind().x, 0);
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getCell(4, 2).getWind().x, 0);
		
		// update and flip
		matrices.process();
		
		// check the vector has moved
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getCell(2, 2).getWind().x, 0);
		Assert.assertEquals(0.0f, matrices.currentGenMatrix().getCell(3, 2).getWind().x, 0);
		Assert.assertEquals(11.0f, matrices.currentGenMatrix().getCell(4, 2).getWind().x, 0);
		
	}

	@Test
	public void conservationOfEnergy() {
		// set up a matrix with a vector
		// TODO: create a template matrix different to a neighbour matrix
		TemplateMatrix template = new TemplateMatrix();
		template.setCell(2, 0, 1, -1, 0.1f);
		template.setCell(2, 1, 1, 0, 0.8f);
		template.setCell(2, 2, 1, 1, 0.1f);
		AlgorithmApplyTemplate alg = new AlgorithmApplyTemplate();
		alg.set_template(template);
		WindMatrices matrices = new MatrixMaker(5,5)
			.setAlgorithm(alg)
			.setCell(2, 2, 10, 0).matrices();
		
		Assert.assertEquals(10.0f, matrices.currentGenMatrix().energy(), 0);
		
		matrices.process();
		System.out.println("Energy " + matrices.currentGenMatrix().energy());
		Utils.printMatrixNonZeroValues(matrices.currentGenMatrix(), "after first update");
		Assert.assertEquals(10.0f, matrices.currentGenMatrix().energy(), 0);
		
		matrices.process();
		System.out.println("Energy " + matrices.currentGenMatrix().energy());
		Utils.printMatrixNonZeroValues(matrices.currentGenMatrix(), "after second update");
		Assert.assertEquals(10.0f, matrices.currentGenMatrix().energy(), 0.1); // leaked some energy
		
	}
	
}
