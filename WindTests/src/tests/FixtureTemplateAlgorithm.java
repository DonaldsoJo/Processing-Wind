package tests;

import org.junit.Assert;
import org.junit.Test;

import processing.core.PVector;

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
		
		Assert.assertEquals(0.0f, nms.nextGenMatrix().getCell(1, 1).getWind().x, 0.0f);
	}

	@Test
	public void applyForwardOnlyTemplate() {
		WindMatrices nms = new MatrixMaker(3, 3).setAlgorithm(new AlgorithmApplyTemplate()).setCell(1, 1, 10, 0).matrices();
		NeighbourhoodMatrix template = new NeighbourhoodMatrix(3, 3);
		template.setCell(2, 1, 1, 0);
		AlgorithmApplyTemplate a = new AlgorithmApplyTemplate();
		a.set_template(template);
		
		a.calculateResultVector(nms.getNeighbours(new WindCell(1, 1)));
		
		Assert.assertEquals(10.0f, nms.nextGenMatrix().getCell(2, 1).getWind().x, 0.0f);
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
		
		Assert.assertEquals(8.0f, nms.nextGenMatrix().getCell(2, 1).getWind().mag(), 0.0f);
		Assert.assertEquals(1.0f, nms.nextGenMatrix().getCell(2, 0).getWind().mag(), 0.001f);
		Assert.assertEquals(2.0f, nms.nextGenMatrix().getCell(2, 2).getWind().mag(), 0.001f);
	}

	@Test
	public void applySpillTemplateToAngledWind() {
		WindMatrices wms = new MatrixMaker(3, 3).setAlgorithm(new AlgorithmApplyTemplate()).setCell(1, 1, -10, 0).matrices();
		
//		float heading = wms.currentGenMatrix().getCell(1, 1).getWind().heading();
//		System.out.println("source heading:" + heading + " degrees:" + Math.toDegrees(heading));
		
		NeighbourhoodMatrix template = new NeighbourhoodMatrix(3, 3);
		template.setCell(2, 0, 1, 1, 0.1f);
		template.setCell(2, 1, 0.8f, 0);
		template.setCell(2, 2, 1, 1, 0.2f);
		AlgorithmApplyTemplate a = new AlgorithmApplyTemplate();
		a.set_template(template);
		
		a.calculateResultVector(wms.getNeighbours(new WindCell(1, 1)));
		
//		Utils.printMatrixNonZeroValues(wms.currentGenMatrix(), "Current");
//		Utils.printMatrixNonZeroValues(wms.nextGenMatrix(), "Next");
		
		Assert.assertEquals(8.0f, wms.nextGenMatrix().getCell(0, 1).getWind().mag(), 0.0f);
		Assert.assertEquals(2.0f, wms.nextGenMatrix().getCell(0, 0).getWind().mag(), 0.0001f);
		Assert.assertEquals(1.0f, wms.nextGenMatrix().getCell(0, 2).getWind().mag(), 0.0001f);
	}
	
	@Test
	public void rectifiedVectorHasExpectedMagnitudeAndHeading() {
		NeighbourhoodMatrix template = new NeighbourhoodMatrix(3, 3);
		template.setCell(2, 1, 0.8f, 0);
		AlgorithmApplyTemplate a = new AlgorithmApplyTemplate();
		a.set_template(template);

		PVector source = new PVector(3, 4);
		float heading = source.heading();
		Assert.assertEquals(5.0f, source.mag(), 0);
		
		PVector result = a.getRectifiedTarget(source, template, 2, 1);
		
		Assert.assertEquals((float)(5*0.8), result.mag(), 0);
		Assert.assertEquals(heading, result.heading(), 0);
	}
	
	@Test
	public void noDeviationOfVectors(){
		WindMatrices wms = new MatrixMaker(5, 3).setAlgorithm(new AlgorithmApplyTemplate()).setCell(0, 1, 10, 0).matrices();

//		float heading = wms.currentGenMatrix().getCell(0, 1).getWind().heading();
//		System.out.println("source heading:" + heading + " degrees:" + Math.toDegrees(heading));
		
		NeighbourhoodMatrix template = new NeighbourhoodMatrix(3, 3);
		template.setCell(2, 0, 1, -1, 0.1f);
		template.setCell(2, 1, 0.8f, 0);
		template.setCell(2, 2, 1, 1, 0.1f);
		AlgorithmApplyTemplate a = new AlgorithmApplyTemplate();
		a.set_template(template);
		
		wms.setAlgorithm(a);
		
		for(int col=0; col<5; col++) {
//			Utils.printMatrixNonZeroValues(wms.currentGenMatrix(), "Current, col=" + col);
			wms.process();	
		}
		
		Assert.assertEquals(0.0f, wms.currentGenMatrix().getCell(4, 1).getWind().heading(), 0);
		
	}	

}
