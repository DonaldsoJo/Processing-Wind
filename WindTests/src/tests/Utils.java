package tests;

import wind.BaseMatrices;
import wind.WindCell;
import wind.WindMatrix;

public class Utils {

	public static void printMatrices(BaseMatrices ms) {
		Utils.printMatrix(ms.currentGenMatrix(), "Current");
		Utils.printMatrix(ms.nextGenMatrix(), "Other");
	}
	
	public static void printMatrix(WindMatrix matrix, String title) {
		System.out.println();
		System.out.println(title);
		for(WindCell[] cols : matrix.getCells())
		{
			for(WindCell cell : cols)
			{
				System.out.println( "Col " + cell.getCol() + ", Row " + cell.getRow() + ", xval " + cell.getWind().x + ", yval " + cell.getWind().y);
			}
		}
	}

	public static void printMatrixNonZeroValues(WindMatrix matrix, String title) {
		System.out.println();
		System.out.println(title);
		for(WindCell[] cols : matrix.getCells())
		{
			for(WindCell cell : cols)
			{
				if (cell.getWind().mag() > 0)
					System.out.println( 
							"col=" + cell.getCol() + 
							", row=" + cell.getRow() + 
							", xval=" + cell.getWind().x + 
							", yval=" + cell.getWind().y +
							", mag=" + cell.getWind().mag() +
							", head=" + cell.getWind().heading());
			}
		}
	}
}
