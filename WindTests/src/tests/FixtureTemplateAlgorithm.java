package tests;

import org.junit.Assert;
import org.junit.Test;

import wind.AlgorithmApplyTemplate;
import wind.NeighbourhoodMatrix;
import wind.WindCell;
import wind.WindMatrices;

public class FixtureTemplateAlgorithm {
	
	NeighbourhoodMatrix x;

	@Test
	public void applyEmptyTemplate() {
		WindMatrices nms = new MatrixMaker(3, 3).setAlgorithm(new AlgorithmApplyTemplate()).setCell(1, 1, 10, 0).matrices();
		NeighbourhoodMatrix template = new NeighbourhoodMatrix(3, 3);
		AlgorithmApplyTemplate a = new AlgorithmApplyTemplate();
		a.set_template(template);
		
		a.calculateResultVector(nms.getNeighbours(new WindCell(1, 1)));
		
		Assert.assertEquals(0.0f, nms.nextGenMatrix().getCell(1, 1).get_wind().x, 0.0f);
	}

	@Test
	public void applyForwardOnlyTemplate() {
		WindMatrices nms = new MatrixMaker(3, 3).setAlgorithm(new AlgorithmApplyTemplate()).setCell(1, 1, 10, 0).matrices();
		NeighbourhoodMatrix template = new NeighbourhoodMatrix(3, 3);
		template.setCell(2, 1, 1, 0);
		AlgorithmApplyTemplate a = new AlgorithmApplyTemplate();
		a.set_template(template);
		
		a.calculateResultVector(nms.getNeighbours(new WindCell(1, 1)));
		
		Assert.assertEquals(10.0f, nms.nextGenMatrix().getCell(2, 1).get_wind().x, 0.0f);
	}

	@Test
	public void applyForwardWithSpillTemplate() {
		WindMatrices nms = new MatrixMaker(3, 3).setAlgorithm(new AlgorithmApplyTemplate()).setCell(1, 1, 10, 0).matrices();
		NeighbourhoodMatrix template = new NeighbourhoodMatrix(3, 3);
		template.setCell(2, 0, 1, 1, 0.1f);
		template.setCell(2, 1, 0.8f, 0);
		template.setCell(2, 2, 1, 1, 0.2f);
		AlgorithmApplyTemplate a = new AlgorithmApplyTemplate();
		a.set_template(template);
		
		a.calculateResultVector(nms.getNeighbours(new WindCell(1, 1)));
		
		Assert.assertEquals(8.0f, nms.nextGenMatrix().getCell(2, 1).get_wind().mag(), 0.0f);
		Assert.assertEquals(1.0f, nms.nextGenMatrix().getCell(2, 0).get_wind().mag(), 0.0f);
		Assert.assertEquals(2.0f, nms.nextGenMatrix().getCell(2, 2).get_wind().mag(), 0.0f);
	}

}
