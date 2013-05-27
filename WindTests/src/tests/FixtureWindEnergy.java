package tests;

import junit.framework.Assert;

import org.junit.Test;

import wind.WindMatrix;

public class FixtureWindEnergy {

	@Test
	public void noWindReturnsZeroEnergy() {
		WindMatrix m = new WindMatrix(5, 6);
		Assert.assertEquals(0.0f, m.energy());
	}

	@Test
	public void individualWindEnergySumsToTotalEnergy() {
		WindMatrix m = new WindMatrix(5, 6);
		m.setCell(1, 1, 10, 0);
		m.setCell(2, 2, 0, 10);
		
		Assert.assertEquals(20.0f, m.energy());
	}

}
