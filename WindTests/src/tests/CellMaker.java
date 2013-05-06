package tests;

import main.WindCell;


public class CellMaker
{
	public CellMaker()
	{
		_cells = getWindCells();
	}
	
	private WindCell[][] _cells;
	
	private WindCell[][] getWindCells() {
		WindCell[][] cells = new WindCell[3][3];
		for(WindCell[] row: cells)
		{
			for(int i=0; i<row.length; i++)
			{
				row[i] = new WindCell();
			}
		}
		return cells;
	}
	
	public CellMaker setCell(int x, int y, float xval, float yval)
	{
		_cells[x][y].get_wind().x = xval;
		_cells[x][y].get_wind().y = yval;
		return this;
	}
	
	public WindCell[][] cells()
	{
		return _cells;
	}
}