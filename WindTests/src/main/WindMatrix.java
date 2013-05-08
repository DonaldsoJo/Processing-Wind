package main;


public class WindMatrix
{
	public WindMatrix(WindCell[][] cells)
	{
		_cells = cells;
	}
	
	private WindCell[][] _cells;

	public WindCell getCell(int col, int row) {
		return _cells[col][row];
	}

	public WindCell getLeft(int col, int row) {
		return getCell(col-1,row);
	}
}