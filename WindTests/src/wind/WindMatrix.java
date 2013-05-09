package wind;


public class WindMatrix
{
	public WindMatrix(WindCell[][] cells)
	{
		_cells = cells;
	}
	
	public WindMatrix(int cols, int rows)
	{
		_cells = new WindCell[3][3];
		for(int col=0; col<cols; col++)
		{
			for(int row=0; row<rows; row++)
			{
				WindCell cell = new WindCell( this, col, row);
				_cells[col][row] = cell;
			}
		}
	}
	
	private WindCell[][] _cells;

	public WindCell[][] cells() {
		return _cells;
	}

	public WindCell getCell(int col, int row) {
		if (indexInBounds(col, row)) return _cells[col][row];
		return new WindCell(this, col, row);
	}

	public boolean indexInBounds(int col, int row) {
		if ((col < 0) || (col >= _cells.length) || (row < 0) || (row >= _cells[0].length)) return false;
		return true;
	}

	public WindCell getLeft(int col, int row) {
		return getCell(col-1,row);
	}

	public WindCell getRight(int col, int row) {
		return getCell(col+1,row);
	}

	public void updateCells() {
		for(WindCell[] cols : _cells)
			for(WindCell cell: cols)
				cell.update();
	}

	public void setCell(int col, int row, int xval, int yval) {
		_cells[col][row].get_wind().x = xval;
		_cells[col][row].get_wind().y = yval;
	}
}