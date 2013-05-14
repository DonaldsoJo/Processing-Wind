package tests;

import wind.WindCell;
import wind.WindMatrix;

public class Utils {

	public static void printMatrix(WindMatrix matrix, String title) {
		System.out.println(title);
		for(WindCell[] cols : matrix.getCells())
		{
			for(WindCell cell : cols)
			{
				System.out.println( "Col " + cell.getCol() + ", Row " + cell.getRow() + ", xval " + cell.get_wind().x + ", yval " + cell.get_wind().y);
			}
		}
	}
}
