package tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import wind.AlgorithmApplyTemplate;
import wind.CellBase;
import wind.NeighbourhoodMatrices;
import wind.ObstacleCell;
import wind.WindCell;

public class FixtureObstacles {

	@Test
	public void setObstacleUpdatesBothMatrices() {
		NeighbourhoodMatrices ms = new MatrixMaker().neighbours(1, 1);
		ms.setObstacle(2,1);
		
		Assert.assertTrue(ms.currentGenMatrix().getCell(2, 1) instanceof CellBase);
		Assert.assertTrue(ms.currentGenMatrix().getCell(2, 1) instanceof ObstacleCell);

	}

}
