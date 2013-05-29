package tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import wind.AlgorithmFractionalFlowAndSideSpills;
import wind.AlgorithmFractionalFlowAndSideSpills.EDirection;
import wind.ENeighbour;
import wind.NeighbourhoodMatrices;
import wind.NeighbourhoodMatrix;

public class FixtureSpillAlgorithm {
	
	private static final float SINPIOVER4 = (float)(0.1*Math.sin(Math.PI/4));
	private AlgorithmFractionalFlowAndSideSpills _alg;
	private NeighbourhoodMatrices _ms;
	
	@Before
	public void SetUp()
	{
		_alg = new AlgorithmFractionalFlowAndSideSpills();
		_ms = new NeighbourhoodMatrices(3, 3, _alg);
	}

	@Test
	public void topLeftHandling() {
		_ms.currentGenMatrix().setCell(0, 0, 1, 1);		
		_alg.handleAny(ENeighbour.TOPLEFT, (NeighbourhoodMatrix)_ms.currentGenMatrix(), (NeighbourhoodMatrix)_ms.nextGenMatrix());
		
		Assert.assertEquals(0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().x);
		Assert.assertEquals(0.1f, _ms.nextGenMatrix().getCell(1, 0).getWind().x);
		Assert.assertEquals(0.1f, _ms.nextGenMatrix().getCell(0, 1).getWind().y);
	}
	
	@Test
	public void bottomLeftHandling() {
		_ms.currentGenMatrix().setCell(0, 2, 1, -1);		
		_alg.handleAny(ENeighbour.BOTLEFT, (NeighbourhoodMatrix)_ms.currentGenMatrix(), (NeighbourhoodMatrix)_ms.nextGenMatrix());

		Assert.assertEquals(0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().x);
		Assert.assertEquals(-0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().y);
		Assert.assertEquals(-0.1f, _ms.nextGenMatrix().getCell(0, 1).getWind().y);
		Assert.assertEquals(0.1f, _ms.nextGenMatrix().getCell(1, 2).getWind().x);
	}
	
	@Test
	public void topRightHandling() {
		_ms.currentGenMatrix().setCell(2, 0, -1, 1);		
		_alg.handleAny(ENeighbour.TOPRIGHT, (NeighbourhoodMatrix)_ms.currentGenMatrix(), (NeighbourhoodMatrix)_ms.nextGenMatrix());

		Assert.assertEquals(-0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().x);
		Assert.assertEquals(0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().y);
		Assert.assertEquals(0.1f, _ms.nextGenMatrix().getCell(2, 1).getWind().y);
		Assert.assertEquals(-0.1f, _ms.nextGenMatrix().getCell(1, 0).getWind().x);
	}
	
	@Test
	public void bottomRightHandling() {
		_ms.currentGenMatrix().setCell(2, 2, -1, -1);		
		
		_alg.handleAny(ENeighbour.BOTRIGHT, (NeighbourhoodMatrix)_ms.currentGenMatrix(), (NeighbourhoodMatrix)_ms.nextGenMatrix());

		Assert.assertEquals(-0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().x);
		Assert.assertEquals(-0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().y);
		Assert.assertEquals(-0.1f, _ms.nextGenMatrix().getCell(2, 1).getWind().y);
		Assert.assertEquals(-0.1f, _ms.nextGenMatrix().getCell(1, 2).getWind().x);
	}

	@Test
	public void topMiddleHandling() {
		_ms.currentGenMatrix().setCell(1, 0, 0, 1);		
		
		_alg.handleAny(ENeighbour.TOP, (NeighbourhoodMatrix)_ms.currentGenMatrix(), (NeighbourhoodMatrix)_ms.nextGenMatrix());

		Assert.assertEquals(0.0f, _ms.nextGenMatrix().getCell(1, 1).getWind().x);
		Assert.assertEquals(0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().y);
		Assert.assertEquals(SINPIOVER4, _ms.nextGenMatrix().getCell(2, 1).getWind().y);
		Assert.assertEquals(SINPIOVER4, _ms.nextGenMatrix().getCell(2, 1).getWind().x);
		Assert.assertEquals(SINPIOVER4, _ms.nextGenMatrix().getCell(0, 1).getWind().y);
		Assert.assertEquals(-SINPIOVER4, _ms.nextGenMatrix().getCell(0, 1).getWind().x);
	}
	
	@Test
	public void rightHandling() {
		_ms.currentGenMatrix().setCell(2, 1, -1, 0);		
		
		_alg.handleAny(ENeighbour.RIGHT, (NeighbourhoodMatrix)_ms.currentGenMatrix(), (NeighbourhoodMatrix)_ms.nextGenMatrix());

		Assert.assertEquals(-0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().x);
		Assert.assertEquals(0.0f, _ms.nextGenMatrix().getCell(1, 1).getWind().y);
		Assert.assertEquals(-SINPIOVER4, _ms.nextGenMatrix().getCell(1, 0).getWind().y);
		Assert.assertEquals(-SINPIOVER4, _ms.nextGenMatrix().getCell(1, 0).getWind().x);
		Assert.assertEquals(SINPIOVER4, _ms.nextGenMatrix().getCell(1, 2).getWind().y);
		Assert.assertEquals(-SINPIOVER4, _ms.nextGenMatrix().getCell(1, 2).getWind().x);
	}
	
	@Test
	public void bottomHandling() {
		_ms.currentGenMatrix().setCell(1, 2, 0, -1);		
		
		_alg.handleAny(ENeighbour.BOTTOM, (NeighbourhoodMatrix)_ms.currentGenMatrix(), (NeighbourhoodMatrix)_ms.nextGenMatrix());

		Assert.assertEquals(0.0f, _ms.nextGenMatrix().getCell(1, 1).getWind().x);
		Assert.assertEquals(-0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().y);
		Assert.assertEquals(-SINPIOVER4, _ms.nextGenMatrix().getCell(0, 1).getWind().y);
		Assert.assertEquals(-SINPIOVER4, _ms.nextGenMatrix().getCell(0, 1).getWind().x);
		Assert.assertEquals(-SINPIOVER4, _ms.nextGenMatrix().getCell(2, 1).getWind().y);
		Assert.assertEquals(SINPIOVER4, _ms.nextGenMatrix().getCell(2, 1).getWind().x);
	}
	
	@Test
	public void leftHandling() {
		_ms.currentGenMatrix().setCell(0, 1, 1, 0);		
		
		_alg.handleAny(ENeighbour.LEFT, (NeighbourhoodMatrix)_ms.currentGenMatrix(), (NeighbourhoodMatrix)_ms.nextGenMatrix());

		Assert.assertEquals(0.8f, _ms.nextGenMatrix().getCell(1, 1).getWind().x);
		Assert.assertEquals(0.0f, _ms.nextGenMatrix().getCell(1, 1).getWind().y);
		Assert.assertEquals(-SINPIOVER4, _ms.nextGenMatrix().getCell(1, 0).getWind().y);
		Assert.assertEquals(SINPIOVER4, _ms.nextGenMatrix().getCell(1, 0).getWind().x);
		Assert.assertEquals(SINPIOVER4, _ms.nextGenMatrix().getCell(1, 2).getWind().y);
		Assert.assertEquals(SINPIOVER4, _ms.nextGenMatrix().getCell(1, 2).getWind().x);
	}
	
	@Test
	public void getNeighbour() {
		Assert.assertEquals(ENeighbour.TOP, AlgorithmFractionalFlowAndSideSpills.getNeighbour(ENeighbour.TOPLEFT, EDirection.clockwise));
		Assert.assertEquals(ENeighbour.BOTRIGHT, AlgorithmFractionalFlowAndSideSpills.getNeighbour(ENeighbour.RIGHT, EDirection.clockwise));
		Assert.assertEquals(ENeighbour.LEFT, AlgorithmFractionalFlowAndSideSpills.getNeighbour(ENeighbour.TOPLEFT, EDirection.anticlockwise));
		Assert.assertEquals(ENeighbour.TOPRIGHT, AlgorithmFractionalFlowAndSideSpills.getNeighbour(ENeighbour.RIGHT, EDirection.anticlockwise));
	}

	@Test
	public void getColAndRow() {
		Assert.assertEquals(0, wind.Utils.col(ENeighbour.TOPLEFT));
		Assert.assertEquals(0, wind.Utils.row(ENeighbour.TOPLEFT));
		Assert.assertEquals(0, wind.Utils.col(ENeighbour.BOTLEFT));
		Assert.assertEquals(2, wind.Utils.row(ENeighbour.BOTLEFT));
	}

}
