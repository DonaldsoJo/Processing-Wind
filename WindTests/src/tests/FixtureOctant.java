package tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import processing.core.PVector;

import wind.ColAndRow;
import wind.Octant;

public class FixtureOctant {

	@Test
	public void headingOctantZero() {
		Octant o = new Octant(0.0f);
		Assert.assertEquals(0, o.octant());
		PVector p = new PVector(0,0);
		o = new Octant(p.heading());
		Assert.assertEquals(0, o.octant());
	}

	@Test
	public void headingOctantOne() {
		PVector p = new PVector(1,1);
		Octant o = new Octant(p.heading());
		Assert.assertEquals(1, o.octant());
	}

	@Test
	public void headingOctants() {
		PVector p = new PVector(0,1);
		Octant o = new Octant(p.heading());
		Assert.assertEquals(2, o.octant());
		
		p = new PVector(-1,1);
		o = new Octant(p.heading());
		Assert.assertEquals(3, o.octant());

		p = new PVector(-1,0);
		o = new Octant(p.heading());
		Assert.assertEquals((float)Math.PI, p.heading());
		Assert.assertEquals(3, o.octant());

		p = new PVector(-1,-0.01f);
		o = new Octant(p.heading());
		Assert.assertEquals(4, o.octant());

		p = new PVector(-1,-1);
		o = new Octant(p.heading());
		Assert.assertEquals(4, o.octant());

		p = new PVector(0,-1);
		o = new Octant(p.heading());
		Assert.assertEquals(5, o.octant());

		p = new PVector(1,-1);
		o = new Octant(p.heading());
		Assert.assertEquals(6, o.octant());

		p = new PVector(1,-0.01f);
		o = new Octant(p.heading());
		Assert.assertEquals(7, o.octant());

	}

	@Test
	public void resultantOctant() {
		Octant o = new Octant(0.0f);
		Assert.assertEquals(0, o.octant());
		ColAndRow cr = o.rotatedColAndRow(2, 1);
		Assert.assertEquals(2, cr.c);
		Assert.assertEquals(1, cr.r);
}

}
