package tests;

import org.junit.Assert;

import org.junit.Test;

import processing.core.PVector;

import wind.AlgorithmApplyTemplate;
import wind.BaseMatrices;
import wind.NeighbourhoodMatrices;
import wind.TemplateMatrix;
import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;

public class FixtureNeighbours {

	public WindMatrix updateCurrent(WindMatrices matrices) {
		WindMatrix current = matrices.currentGenMatrix();
		for( int col=0; col<current.getCells().length; col++)
		{
			WindCell[] cells = current.getCells()[col];
			for (int row=0; row<cells.length; row++)
			{
				PVector v = cells[row].getWind();
				v.x = col;
				v.y = row;
			}
		}
		return current;
	}

	@Test
	public void neighboursAreZero_centreUpdatesToZero() throws Exception {
		NeighbourhoodMatrices neighbours = new MatrixMaker().centre(2, 3).neighbours(1,1);
		neighbours.update();
		Assert.assertEquals(0.0f, neighbours.nextGenMatrix().getCell(1, 1).getWind().x, 0);
	}

	@Test
	public void cetreIsOne_rightUpdatesToOne() throws Exception {
		NeighbourhoodMatrices neighbours = new MatrixMaker().setCell(1, 1, 1, 0).neighbours(1,1);
		Assert.assertEquals(0.0f, neighbours.nextGenMatrix().getCell(1, 1).getWind().x, 0);

		neighbours.update();

		Assert.assertEquals(1, neighbours.nextGenMatrix().getCell(2, 1).getWind().x, 0);
	}

	@Test
	public void updateNeightboursMatrices_updatesAssociatedWindMatrix() throws Exception {
		
		WindMatrices matrices = new MatrixMaker(5,5).setCell(2, 2, 11, 0).matrices();
		AlgorithmApplyTemplate alg = new AlgorithmApplyTemplate();
		TemplateMatrix template = new TemplateMatrix();
		template.setCell(2, 1, 1, 0);
		alg.set_template(template);
		matrices.setAlgorithm(alg);
		NeighbourhoodMatrices neighbours = matrices.getNeighbours(new WindCell(2,2));
		Assert.assertEquals(0.0f, neighbours.nextGenMatrix().getCell(1, 1).getWind().x, 0);
		
//		Utils.printMatrix(neighbours.currentGenMatrix(), "neighbours current");
//		Utils.printMatrix(neighbours.nextGenMatrix(), "neighbours other");
		neighbours.update();
//		Utils.printMatrix(neighbours.currentGenMatrix(), "neighbours current after");
//		Utils.printMatrix(neighbours.nextGenMatrix(), "neighbours other after");
		
		Assert.assertEquals(11, neighbours.nextGenMatrix().getCell(2, 1).getWind().x, 0);
		Assert.assertEquals(11, matrices.nextGenMatrix().getCell(3, 2).getWind().x, 0);
	}

	@Test
	public void writeToOtherNeighbours_getSameBack() throws Exception {
		
		WindMatrices matrices = new MatrixMaker(5,5).setCell(1, 1, 11, 0).matrices();
		BaseMatrices neighbours = matrices.getNeighbours(new WindCell(1,1));
		
		Assert.assertEquals(0.0f, neighbours.nextGenMatrix().getCell(1, 1).getWind().x, 0);
		
		neighbours.nextGenMatrix().setCell(1, 1, 12, 6);
		
		Assert.assertEquals(12.0f, neighbours.nextGenMatrix().getCell(1, 1).getWind().x, 0);
		Assert.assertEquals(12.0f, matrices.nextGenMatrix().getCell(1, 1).getWind().x, 0);		
	}
	
	@Test
	public void cloneNeighbours() {
		TemplateMatrix m = new TemplateMatrix();
		TemplateMatrix n = m.cloneMatrix();
		Assert.assertNotSame(m,n);
	}
	
	@Test
	public void clonedNeighbourCopiesVectorValues() {
		TemplateMatrix m = new TemplateMatrix();
		m.setCell(1, 1, 11, 12);
		TemplateMatrix n = m.cloneMatrix();
		Assert.assertEquals(m.getCell(1, 1).getWind().x, n.getCell(1, 1).getWind().x, 0);
	}
	
	@Test
	public void clonedNeighbourCopiesCellCoordinates() {
		TemplateMatrix m = new TemplateMatrix();
		TemplateMatrix n = m.cloneMatrix();
		Assert.assertEquals(m.getCell(1, 1).getCol(), n.getCell(1, 1).getCol());
	}
	
	@Test
	public void clonedNeighbourHasDifferentVectors() {
		TemplateMatrix m = new TemplateMatrix();
		TemplateMatrix n = m.cloneMatrix();
		Assert.assertNotSame(m.getCell(1, 1).getWind(), n.getCell(1, 1).getWind());
	}
	

}
