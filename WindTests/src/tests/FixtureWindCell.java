package tests;

import junit.framework.Assert;


import org.junit.Test;

import wind.WindCell;
import wind.WindMatrix;

public class FixtureWindCell {

	@Test
	public void neighboursAreZero_centreUpdatesToZero() {
		WindMatrix matrix = new MatrixMaker().centre(2, 3).left(4, 5).matrix();
		
		WindCell centre = matrix.getCell(1,1);
		Assert.assertEquals(2.0f, centre.get_wind().x);
		WindCell otherCell = matrix.otherCells()[1][1];
		Assert.assertEquals(0.0f, otherCell.get_wind().x);

		centre.update();
		
		Assert.assertEquals(2.0f, centre.get_wind().x);
		Assert.assertEquals(4.0f, otherCell.get_wind().x);
	}

	@Test
	public void leftNeighboursIsOne_centreUpdatesToOne() {
		WindMatrix matrix = new MatrixMaker().centre(2, 3).setCell(0, 1, 1, 0).matrix();
		WindCell centre = matrix.getCell(1, 1);
		
		centre.update();
		
		WindCell otherCell = matrix.otherCells()[1][1];
		Assert.assertEquals(1.0f, otherCell.get_wind().x);
	}

	@Test
	public void getLeftNeighbour() {
		WindMatrix matrix = new MatrixMaker().centre(2, 3).setCell(0, 1, 1, 0).matrix();
		WindCell centre = matrix.getCell(1, 1);
		
		WindCell left = centre.getLeft();
		Assert.assertEquals(1.0f, left.get_wind().x);
	}

	@Test
	public void getRightNeighbour() {
		WindMatrix matrix = new MatrixMaker().centre(4, 3).setCell(2, 1, 1, 3).matrix();
		WindCell centre = matrix.getCell(1, 1);
		
		WindCell right = centre.getRight();
		Assert.assertEquals(1.0f, right.get_wind().x);
	}

	private void printMatrix(WindMatrix matrix, String title) {
		System.out.println(title);
		for(WindCell[] cols : matrix.cells())
		{
			for(WindCell cell : cols)
			{
				System.out.println( "Col " + cell.getCol() + ", Row " + cell.getRow() + ", xval " + cell.get_wind().x + ", yval " + cell.get_wind().y);
			}
		}
	}

}
