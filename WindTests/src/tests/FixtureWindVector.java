package tests;

import junit.framework.Assert;


import org.junit.Test;

import processing.core.PVector;
import wind.AlgorithmBase;

public class FixtureWindVector {

	@Test
	public void headingIsPositiveThenNegative() {
		Assert.assertTrue(new PVector(1,0).heading() == 0);
		Assert.assertTrue(new PVector(1,1).heading() == (float)(Math.PI/4));
		Assert.assertTrue(new PVector(0,1).heading() == (float)(Math.PI/2));
		Assert.assertTrue(new PVector(-1,1).heading() == (float)(3*Math.PI/4));
		Assert.assertTrue(new PVector(-1,0).heading() == (float)(Math.PI));
		Assert.assertTrue(new PVector(-1,-1).heading() == (float)(-3*Math.PI/4));
		Assert.assertTrue(new PVector(0,-1).heading() == (float)(-Math.PI/2));
		Assert.assertTrue(new PVector(1,-1).heading() == (float)(-Math.PI/4));
	}

	@Test
	public void differentTopMiddleVectors() {
		PVector v = new PVector(1,1);
		PVector w = AlgorithmBase.resolveTopMiddle(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(1.0f, w.y);
		
		v = new PVector(1,0);
		w = AlgorithmBase.resolveTopMiddle(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(0,1);
		w = AlgorithmBase.resolveTopMiddle(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(1.0f, w.y);
	}

	@Test
	public void differentLeftVectors() {
		PVector v = new PVector(1,1);
		PVector w = AlgorithmBase.resolveLeft(v);
		Assert.assertEquals(1.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(1,0);
		w = AlgorithmBase.resolveLeft(v);
		Assert.assertEquals(1.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(0,1);
		w = AlgorithmBase.resolveLeft(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
	}

	@Test
	public void differentRightVectors() {
		PVector v = new PVector(-1,-1);
		PVector w = AlgorithmBase.resolveRight(v);
		Assert.assertEquals(-1.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(-1,0);
		w = AlgorithmBase.resolveRight(v);
		Assert.assertEquals(-1.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(1,1);
		w = AlgorithmBase.resolveRight(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
	}

	@Test
	public void differentBottomMiddleVectors() {
		PVector v = new PVector(1,1);
		PVector w = AlgorithmBase.resolveBottomMiddle(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(-1,-1);
		w = AlgorithmBase.resolveBottomMiddle(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(-1.0f, w.y);
		
		v = new PVector(-1,0);
		w = AlgorithmBase.resolveBottomMiddle(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(0,-1);
		w = AlgorithmBase.resolveBottomMiddle(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(-1.0f, w.y);
	}

	@Test
	public void differentTopLeftVectors() {
		PVector v = new PVector(1,1);
		PVector w = AlgorithmBase.resolveTopLeft(v);
		Assert.assertEquals(1.0f, w.x);
		Assert.assertEquals(1.0f, w.y);
		
		v = new PVector(1,0);
		w = AlgorithmBase.resolveTopLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(0,1);
		w = AlgorithmBase.resolveTopLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,1);
		w = AlgorithmBase.resolveTopLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,0);
		w = AlgorithmBase.resolveTopLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,-1);
		w = AlgorithmBase.resolveTopLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(0,-1);
		w = AlgorithmBase.resolveTopLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(1,-1);
		w = AlgorithmBase.resolveTopLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
	}

	@Test
	public void differentTopRightVectors() {
		PVector v = new PVector(1,1);
		PVector w = AlgorithmBase.resolveTopRight(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(1,0);
		w = AlgorithmBase.resolveTopRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(0,1);
		w = AlgorithmBase.resolveTopRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,1);
		w = AlgorithmBase.resolveTopRight(v);
		Assert.assertEquals( -1.0f, w.x);
		Assert.assertEquals( 1.0f, w.y);
		
		v = new PVector(-1,0);
		w = AlgorithmBase.resolveTopRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,-1);
		w = AlgorithmBase.resolveTopRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(0,-1);
		w = AlgorithmBase.resolveTopRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(1,-1);
		w = AlgorithmBase.resolveTopRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
	}

	@Test
	public void differentBottomLeftVectors() {
		PVector v = new PVector(1,1);
		PVector w = AlgorithmBase.resolveBottomLeft(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(1,0);
		w = AlgorithmBase.resolveBottomLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(0,1);
		w = AlgorithmBase.resolveBottomLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,1);
		w = AlgorithmBase.resolveBottomLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,0);
		w = AlgorithmBase.resolveBottomLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,-1);
		w = AlgorithmBase.resolveBottomLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(0,-1);
		w = AlgorithmBase.resolveBottomLeft(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(1,-1);
		w = AlgorithmBase.resolveBottomLeft(v);
		Assert.assertEquals( 1.0f, w.x);
		Assert.assertEquals( -1.0f, w.y);
		
	}
	
	@Test
	public void differentBottomRightVectors() {
		PVector v = new PVector(1,1);
		PVector w = AlgorithmBase.resolveBottomRight(v);
		Assert.assertEquals(0.0f, w.x);
		Assert.assertEquals(0.0f, w.y);
		
		v = new PVector(1,0);
		w = AlgorithmBase.resolveBottomRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(0,1);
		w = AlgorithmBase.resolveBottomRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,1);
		w = AlgorithmBase.resolveBottomRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,0);
		w = AlgorithmBase.resolveBottomRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(-1,-1);
		w = AlgorithmBase.resolveBottomRight(v);
		Assert.assertEquals( -1.0f, w.x);
		Assert.assertEquals( -1.0f, w.y);
		
		v = new PVector(0,-1);
		w = AlgorithmBase.resolveBottomRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
		v = new PVector(1,-1);
		w = AlgorithmBase.resolveBottomRight(v);
		Assert.assertEquals( 0.0f, w.x);
		Assert.assertEquals( 0.0f, w.y);
		
	}
}
