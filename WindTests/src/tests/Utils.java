package tests;

import wind.BaseMatrices;
import wind.WindCell;
import wind.WindMatrix;

public class Utils {

	public static void printMatrices(BaseMatrices ms) {
		Utils.printMatrix(ms.currentMatrix(), "Current");
		Utils.printMatrix(ms.otherMatrix(), "Other");
	}
	
	public static void printMatrix(WindMatrix matrix, String title) {
		System.out.println();
		System.out.println(title);
		for(WindCell[] cols : matrix.getCells())
		{
			for(WindCell cell : cols)
			{
				System.out.println( "Col " + cell.getCol() + ", Row " + cell.getRow() + ", xval " + cell.get_wind().x + ", yval " + cell.get_wind().y);
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
				if (cell.get_wind().mag() > 0)
					System.out.println( "Col " + cell.getCol() + ", Row " + cell.getRow() + ", xval " + cell.get_wind().x + ", yval " + cell.get_wind().y);
			}
		}
	}
}
