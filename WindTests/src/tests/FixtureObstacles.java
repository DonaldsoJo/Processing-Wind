package tests;

import junit.framework.Assert;

import org.junit.Test;

import wind.CellBase;
import wind.NeighbourhoodMatrices;
import wind.ObstacleCell;

public class FixtureObstacles {

	@Test
	public void setObstacleUpdatesBothMatrices() {
		NeighbourhoodMatrices ms = new MatrixMaker().neighbours(1, 1);
		ms.setObstacle(2,1);
		
		Assert.assertTrue(ms.currentGenMatrix().getCell(2, 1) instanceof CellBase);
		Assert.assertTrue(ms.currentGenMatrix().getCell(2, 1) instanceof ObstacleCell);

	}

}
