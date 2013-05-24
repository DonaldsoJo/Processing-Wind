package tests;

import static org.junit.Assert.*;

import javax.xml.ws.ServiceMode;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import wind.AlgorithmBase;
import wind.AlgorithmFractionalFlowAndSideSpills;
import wind.AlgorithmFractionalFlowAndSideSpills.EDirection;
import wind.ENeighbour;
import wind.NeighbourhoodMatrices;
import wind.AlgorithmBase.AlgorithmType;
import wind.NeighbourhoodMatrix;

public class FixtureAlgorithms {
	
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
		_ms.currentMatrix().setCell(0, 0, 1, 1);		
//		_alg.topLeftHandling((NeighbourhoodMatrix)_ms.currentMatrix(), (NeighbourhoodMatrix)_ms.otherMatrix());
		_alg.handleAny(ENeighbour.TOPLEFT, (NeighbourhoodMatrix)_ms.currentMatrix(), (NeighbourhoodMatrix)_ms.otherMatrix());

		Assert.assertEquals(0.8f, _ms.otherMatrix().getCell(1, 1).get_wind().x);
		Assert.assertEquals(0.1f, _ms.otherMatrix().getCell(0, 1).get_wind().y);
		Assert.assertEquals(0.1f, _ms.otherMatrix().getCell(1, 0).get_wind().x);
	}
	
	@Test
	public void bottomLeftHandling() {
		_ms.currentMatrix().setCell(0, 2, 1, -1);		
		
//		Utils.printMatrix(ms.currentMatrix(), "current");
//		Utils.printMatrix(ms.otherMatrix(), "other");
		
		_alg.bottomLeftHandling((NeighbourhoodMatrix)_ms.currentMatrix(), (NeighbourhoodMatrix)_ms.otherMatrix());

//		Utils.printMatrix(ms.currentMatrix(), "current");
//		Utils.printMatrix(ms.otherMatrix(), "other");
		
		Assert.assertEquals(0.8f, _ms.otherMatrix().getCell(1, 1).get_wind().x);
		Assert.assertEquals(-0.1f, _ms.otherMatrix().getCell(0, 1).get_wind().y);
		Assert.assertEquals(0.1f, _ms.otherMatrix().getCell(1, 2).get_wind().x);
	}
	
	@Test
	public void getNeighbour() {
		Assert.assertEquals(ENeighbour.TOP, AlgorithmFractionalFlowAndSideSpills.getNeighbour(ENeighbour.TOPLEFT, EDirection.clockwise));
		Assert.assertEquals(ENeighbour.BOTRIGHT, AlgorithmFractionalFlowAndSideSpills.getNeighbour(ENeighbour.RIGHT, EDirection.clockwise));
		Assert.assertEquals(ENeighbour.LEFT, AlgorithmFractionalFlowAndSideSpills.getNeighbour(ENeighbour.TOPLEFT, EDirection.anticlockwise));
		Assert.assertEquals(ENeighbour.TOPRIGHT, AlgorithmFractionalFlowAndSideSpills.getNeighbour(ENeighbour.RIGHT, EDirection.anticlockwise));
	}

}
