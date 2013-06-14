package tests;

import junit.framework.Assert;

import org.junit.Test;

import wind.AlgorithmTemplateAndObstacles;
import wind.AlgorithmTemplateAndObstacles.Rotation;
import wind.CellBase;
import wind.Coords;
import wind.NeighbourhoodMatrices;
import wind.NeighbourhoodMatrix;
import wind.ObstacleCell;
import wind.Octant;

public class FixtureObstacles {

	@Test
	public void setObstacleUpdatesBothMatrices() {
		NeighbourhoodMatrices ms = new MatrixMaker().neighbours(1, 1);
		ms.setObstacle(2,1);
		
		Assert.assertTrue(ms.currentGenMatrix().getCell(2, 1) instanceof CellBase);
		Assert.assertTrue(ms.currentGenMatrix().getCell(2, 1) instanceof ObstacleCell);

	}
	
/*
handleObstacleCellTarget
assignSplitWindToNextWindCell
findFirstCell
 */
	
	@Test
	public void findMatchingCellInNeighbours(){
		NeighbourhoodMatrices nms = new MatrixMaker(5,5).neighbours(1, 2);
		NeighbourhoodMatrix nextgen = (NeighbourhoodMatrix) nms.nextGenMatrix();
		CellBase targetCell = nextgen.getCell(2, 3);
		Coords result = AlgorithmTemplateAndObstacles.findMatchingCellInNeighbours(targetCell, nextgen);
		Assert.assertNotNull(result);
		// bottom right of neighbours
		Assert.assertEquals(2, result.col);
		Assert.assertEquals(2, result.row);
	}

	@Test
	public void octantClockwiseFromTopLeft(){
		Coords index = new Coords(0, 0);
		Octant o = new Octant(index).clockwise();
		Coords newIndex = o.getIndex();
		Assert.assertEquals(1, newIndex.col);
		Assert.assertEquals(0, newIndex.row);
	}

	@Test
	public void octantAntiClockwiseFromTopLeft(){
		Coords index = new Coords(0, 0);
		Octant o = new Octant(index).anticlockwise();
		Coords newIndex = o.getIndex();
		Assert.assertEquals(0, newIndex.col);
		Assert.assertEquals(1, newIndex.row);
	}

	@Test
	public void octantClockwiseFromBottomRight(){
		Coords index = new Coords(2, 2);
		Octant o = new Octant(index).clockwise();
		Coords newIndex = o.getIndex();
		Assert.assertEquals(1, newIndex.col);
		Assert.assertEquals(2, newIndex.row);
	}

	@Test
	public void octantAntiClockwiseFromBottomRight(){
		Coords index = new Coords(2, 2);
		Octant o = new Octant(index).anticlockwise();
		Coords newIndex = o.getIndex();
		Assert.assertEquals(2, newIndex.col);
		Assert.assertEquals(1, newIndex.row);
	}

	@Test
	public void nextCellClockwiseIsWind(){
		NeighbourhoodMatrices nms = new MatrixMaker(5,5).neighbours(1, 2);
		NeighbourhoodMatrix nextgen = (NeighbourhoodMatrix) nms.nextGenMatrix();
		CellBase targetCell = nextgen.getCell(2, 3);
		Coords result = AlgorithmTemplateAndObstacles.findFirstCell(targetCell, Rotation.clockwise, nextgen);
		Assert.assertNotNull(result);
		
		// get the cell in the underlying matrix
		CellBase c = nextgen.getCell(result.col, result.row);
		Assert.assertEquals(1, c.getCol());
		Assert.assertEquals(3, c.getRow());
	}

	@Test
	public void nextCellAntiClockwiseIsWind(){
		NeighbourhoodMatrices nms = new MatrixMaker(5,5).neighbours(1, 2);
		NeighbourhoodMatrix nextgen = (NeighbourhoodMatrix) nms.nextGenMatrix();
		CellBase targetCell = nextgen.getCell(2, 3);
		Coords result = AlgorithmTemplateAndObstacles.findFirstCell(targetCell, Rotation.antiClockwise, nextgen);
		Assert.assertNotNull(result);
		
		// get the cell in the underlying matrix
		CellBase c = nextgen.getCell(result.col, result.row);
		Assert.assertEquals(2, c.getCol());
		Assert.assertEquals(2, c.getRow());
	}

}
