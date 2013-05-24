package wind;

public class Utils {
	
	private static int _columnsAndRows[][] = {
		{0,0}, // top left
		{0,1}, // top
		{0,2}, // top right
		{1,0}, // left
		{1,1}, // centre
		{1,2}, // right
		{2,0}, // bottom left
		{2,1}, // bottom middle
		{2,2}  // bottom right
	};

	public static int col(ENeighbour neighbourCell) {
		return _columnsAndRows[neighbourCell.getIndex()][1];
	}

	public static int row(ENeighbour neighbourCell) {
		return _columnsAndRows[neighbourCell.getIndex()][0];
	}

}
