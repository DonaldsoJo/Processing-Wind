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
		
		Assert.assertEquals(10.0f, nms.nextGenMatrix().getCell(1, 1).get_wind().x, 0.0f);
	}

}
