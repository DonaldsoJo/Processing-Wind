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
		return _cells[col][row];
	}

	public WindCell getLeft(int col, int row) {
		return getCell(col-1,row);
	}

	public WindCell getRight(int col, int row) {
		return getCell(col+1,row);
	}

	public void updateCells() {
		// TODO Auto-generated method stub
		
	}

	public void setCell(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		
	}
}