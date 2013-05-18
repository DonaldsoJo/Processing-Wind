package tests;

import junit.framework.Assert;


import org.junit.Test;

import processing.core.PVector;

public class FixtureWindVector {

	@Test
	public void differentTopLeftVectors() {
		PVector v = new PVector(1,1);
		PVector w = resolveTopLeft(v);
		Assert.assertEquals(1.0f, w.x);
		Assert.assertEquals(1.0f, w.y);
		
		v = new PVector(1,0);
		w = resolveTopLeft(v);
		Assert.assertEquals((float)Math.cos(Math.PI/4), w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(0,1);
		w = resolveTopLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals((float)Math.sin(Math.PI/4), w.y);
	}

	private PVector resolveTopLeft(PVector v) {
		PVector result = new PVector();
		if (v.x > 0) result.x = (float) (v.mag() * Math.cos(Math.PI/4));
		if (v.y > 0) result.y = (float) (v.mag() * Math.sin(Math.PI/4));
		return result;
	}


}
