package wind;


public class WindMatrix
{
	public WindMatrix(WindCell[][] cells)
	{
		_cells[_currentMatrix] = cells;
	}
	
	public WindMatrix(int cols, int rows)
	{
		_cells = new WindCell[2][cols][rows];
		_cells[0] = new WindCell[cols][rows];
		_cells[1] = new WindCell[cols][rows];
		for(int col=0; col<cols; col++)
		{
			for(int row=0; row<rows; row++)
			{
				_cells[_currentMatrix][col][row] = new WindCell( this, col, row);;
				_cells[otherMatrix()][col][row] = new WindCell( this, col, row);;
			}
		}
	}
	
	private int _currentMatrix = 0;
	public int currentMatrix() {
		return _currentMatrix;
	}
	public int otherMatrix() {
		return 1-_currentMatrix;
	}

	private WindCell[][][] _cells;

	public WindCell[][] cells() {
		return _cells[_currentMatrix];
	}

	public WindCell getCell(int col, int row) {
		if (indexInBounds(col, row)) return cells()[col][row];
		return new WindCell(this, col, row);
	}

	public boolean indexInBounds(int col, int row) {
		if ((col < 0) || (col >= cells().length) || (row < 0) || (row >= cells().length)) return false;
		return true;
	}

	public WindCell getLeft(int col, int row) {
		return getCell(col-1,row);
	}

	public WindCell getRight(int col, int row) {
		return getCell(col+1,row);
	}

	public void updateCells() {
		for(WindCell[] cols : cells())
			for(WindCell cell: cols)
				cell.update();
	}

	public void setCell(int col, int row, int xval, int yval) {
		cells()[col][row].get_wind().x = xval;
		cells()[col][row].get_wind().y = yval;
	}

	public void flipMatrix() {
		_currentMatrix = otherMatrix();
	}
}