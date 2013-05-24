package wind;

public class Utils {
	
	private static int _columnsAndRows[][] = {
		{0,0},
		{0,1},
		{0,2},
		{1,0},
		{1,1},
		{1,2},
		{2,0},
		{2,1},
		{2,2}
	};

	public static int col(ENeighbour neighbourCell) {
		return _columnsAndRows[0][neighbourCell.getIndex()];
	}

	public static int row(ENeighbour neighbourCell) {
		return _columnsAndRows[1][neighbourCell.getIndex()];
	}

}
