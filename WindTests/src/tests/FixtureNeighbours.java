package tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import processing.core.PVector;

import wind.NeighbourhoodMatrices;
import wind.WindCell;
import wind.WindMatrices;
import wind.WindMatrix;

public class FixtureNeighbours {

	@Test
	public void getNeighboursFromMiddle() {
		WindMatrices matrices = new MatrixMaker(5, 8).matrices();
		WindMatrix current = updateCurrent(matrices);
//		Utils.printMatrix(current, "current");
		NeighbourhoodMatrices neighbours = current.getNeighbours( new WindCell(current, 2, 5));
//		Utils.printMatrix(neighbours.currentMatrix(), "neighbours");
		
		Assert.assertEquals(1.0f, neighbours.topLeft().get_wind().x);
		Assert.assertEquals(4.0f, neighbours.topLeft().get_wind().y);
		
		Assert.assertEquals(2.0f, neighbours.topMiddle().get_wind().x);
		Assert.assertEquals(4.0f, neighbours.topMiddle().get_wind().y);
		
		Assert.assertEquals(3.0f, neighbours.topRight().get_wind().x);
		Assert.assertEquals(4.0f, neighbours.topRight().get_wind().y);
		
		Assert.assertEquals(1.0f, neighbours.left().get_wind().x);
		Assert.assertEquals(5.0f, neighbours.left().get_wind().y);

		Assert.assertEquals(2.0f, neighbours.centre().get_wind().x);
		Assert.assertEquals(5.0f, neighbours.centre().get_wind().y);

		Assert.assertEquals(3.0f, neighbours.right().get_wind().x);
		Assert.assertEquals(5.0f, neighbours.right().get_wind().y);

		Assert.assertEquals(1.0f, neighbours.bottomLeft().get_wind().x);
		Assert.assertEquals(6.0f, neighbours.bottomLeft().get_wind().y);
		
		Assert.assertEquals(2.0f, neighbours.bottomMiddle().get_wind().x);
		Assert.assertEquals(6.0f, neighbours.bottomMiddle().get_wind().y);
		
		Assert.assertEquals(3.0f, neighbours.bottomRight().get_wind().x);
		Assert.assertEquals(6.0f, neighbours.bottomRight().get_wind().y);
	}

	public WindMatrix updateCurrent(WindMatrices matrices) {
		WindMatrix current = matrices.currentMatrix();
		for( int col=0; col<current.getCells().length; col++)
		{
			WindCell[] cells = current.getCells()[col];
			for (int row=0; row<cells.length; row++)
			{
				PVector v = cells[row].get_wind();
				v.x = col;
				v.y = row;
			}
		}
		return current;
	}

}
