package wind;

import processing.core.PVector;

public class WindMatrix {
	private WindCell[][] _cells;
	private BaseMatrices _parent;

	public WindMatrix(int cols, int rows, BaseMatrices baseMatrices) {
		_cells = new WindCell[cols][rows];
		_parent = baseMatrices;
	}
	
	public WindCell[][] getCells() {
		return _cells;
	}

	public void setCells(WindCell[][] _cells) {
		this._cells = _cells;
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
			{
				NeighbourhoodMatrices neighbours = getNeighbours( cell);
				cell.update();
			}
	}

	public NeighbourhoodMatrices getNeighbours( WindCell cell) {
		NeighbourhoodMatrices neighbours = new NeighbourhoodMatrices(3, 3);
		for(int col=0; col<3; col++)
			for (int row=0; row<3; row++)
			{
				WindCell c = getCell(col + cell.getCol() - 1, row + cell.getRow() - 1);
				neighbours.currentMatrix().replaceCell(col,row,c);
			}
		return neighbours;
	}

	private void replaceCell(int col, int row, WindCell cell) {
		_cells[col][row] = cell;
	}

	public void setCell(int col, int row, float x, float y) {
		_cells[col][row].get_wind().x = x;
		_cells[col][row].get_wind().y = y;
	}

	public void clearOther(int col, int row) {
		_parent.clearOther(col, row);
	}

	public void updateOther( int col, int row, PVector newWind) {
		_parent.updateOther( col, row, newWind);
	}
}